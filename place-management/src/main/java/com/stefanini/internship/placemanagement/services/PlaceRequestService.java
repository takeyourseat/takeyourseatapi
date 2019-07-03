package com.stefanini.internship.placemanagement.services;

import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import com.stefanini.internship.placemanagement.data.dto.User;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRepository;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRequestRepository;
import com.stefanini.internship.placemanagement.exception.DuplicateResourceException;
import com.stefanini.internship.placemanagement.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class PlaceRequestService {

    private PlaceRequestRepository placeRequestRepository;
    private PlaceRepository placeRepository;
    private UserService userService;

    public PlaceRequestService(PlaceRequestRepository placeRequestRepository, PlaceRepository placeRepository, UserService userService) {
        this.placeRequestRepository = placeRequestRepository;
        this.placeRepository = placeRepository;
        this.userService = userService;
    }

    @PostFilter("@AuthorizationService.hasPermissionForPlaceRequest(filterObject, 'read')")
    public List<PlaceRequest> getPlaceRequestsByUser(String user) {
        List<PlaceRequest> placeRequests = placeRequestRepository.getPlaceRequestsByUsername(user);
        if (placeRequests.isEmpty()) {
            throw new ResourceNotFoundException("The user with username = " + user + " has no place requests");
        }
        return placeRequests;
    }

    @PostFilter("@AuthorizationService.hasPermissionForPlaceRequest(filterObject, 'approve')")
    public List<PlaceRequest> getPlaceRequestsByManager(String reviewer) {
        List<PlaceRequest> placeRequests = placeRequestRepository.getPlaceRequestsByReviewerAndApprovedIsNull(reviewer);
        if (placeRequests.isEmpty()) {
            RuntimeException exception = new ResourceNotFoundException("There are no pending place requests");
            log.info("There are no pending place requests");
            throw exception;
        }
        return placeRequests;
    }

    //Todo Authorize for creating a place request
    public PlaceRequest createPlaceRequest(Long placeId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        PlaceRequest placeRequest = new PlaceRequest();
        User user = userService.getUserByUsername(username);
        log.info("User created request");
        if (user == null) {
            RuntimeException exception = new ResourceNotFoundException("User with username = " + username + " doesn't exists");
            log.info("User not found");
            throw exception;
        }
        Place place = placeRepository.getPlaceById(placeId);
        if (place == null) {
            RuntimeException exception = new ResourceNotFoundException("Place with id = " + placeId + " doesn't exists");
            log.info("Place not found");
            throw exception;
        }
        placeRequest.setUsername(username);
        placeRequest.setPlace(place);
        placeRequest.setDateOf(new Timestamp(System.currentTimeMillis()));
        if (user.getManager() == null) {
            throw new ResourceNotFoundException("This user has no manager");
        }
        placeRequest.setReviewer(user.getManager().getUsername());
        if (placeRequest.getUsername().equals(place.getUsername())) {
            throw new DuplicateResourceException("The user with username = " + username + " is already on the place with id = " + placeId);
        }
        if (place.getUsername() != null) {
            throw new DuplicateResourceException("The place with id = " + placeId + " is occupied by another user");
        }
        PlaceRequest identicalPlaceRequest = placeRequestRepository.getPlaceRequestByPlaceIdAndUsernameAndReviewedAt(placeId, placeRequest.getUsername(), placeRequest.getReviewedAt());
        if (identicalPlaceRequest != null) {
            if (identicalPlaceRequest.getApproved() == null) {
                throw new DuplicateResourceException("such place request is pending");
            } else if (!identicalPlaceRequest.getApproved()) {
                placeRequestRepository.save(placeRequest);
                return placeRequest;
            }
        }
        placeRequestRepository.save(placeRequest);
        return placeRequest;
    }

    @Transactional
    @PreAuthorize("@AuthorizationService.hasPermissionForPlaceRequest(@placeRequestRepository.getPlaceRequestById(#id),'approve')")
    public PlaceRequest declinePlaceRequest(Long id) {
        log.info(String.format("Manager tries to decline place request with id %d", id));
        PlaceRequest newPlaceRequest = placeRequestRepository.getPlaceRequestById(id);
        PlaceRequestErrorHandler(newPlaceRequest);
        newPlaceRequest.setApproved(false);
        newPlaceRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
        return newPlaceRequest;
    }

    @Transactional
    @PreAuthorize("@AuthorizationService.hasPermissionForPlaceRequest(@placeRequestRepository.getPlaceRequestById(#id),'approve')")
    public PlaceRequest acceptPlaceRequest(Long id) {
        log.info(String.format("Manager accepted place request with id %d", id));
        PlaceRequest updatedPlaceRequest = placeRequestRepository.getPlaceRequestById(id);
        PlaceRequestErrorHandler(updatedPlaceRequest);
        Place placeById = placeRepository.getPlaceById(updatedPlaceRequest.getPlace().getId());
        Place oldPlace = placeRepository.getPlaceByUsername(updatedPlaceRequest.getUsername());
        if (oldPlace != null)
            oldPlace.setUsername(null);
        updatedPlaceRequest.setApproved(true);
        updatedPlaceRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
        placeById.setUsername(updatedPlaceRequest.getUsername());
        declineAllPlaceRequestsIfPlaceAccepted(updatedPlaceRequest.getPlace().getId());
        declineAllPlaceRequestsIfUserWasAcceptedOnPlace(updatedPlaceRequest.getUsername());
        placeRepository.save(placeById);
        return updatedPlaceRequest;
    }

    private void PlaceRequestErrorHandler(PlaceRequest placeRequest) {
        if (placeRequest == null) {
            throw new ResourceNotFoundException("Place request doesn't exists");
        }
        if (placeRequest.getApproved() != null) {
            throw new DuplicateResourceException("Place request already has a response");
        }
    }

    private void declineAllPlaceRequestsIfPlaceAccepted(Long placeId) {
        List<PlaceRequest> placeRequests = placeRequestRepository.findAll();
        for (PlaceRequest placeRequest : placeRequests) {
            if (placeRequest.getPlace().getId().equals(placeId) && placeRequest.getApproved() == null) {
                placeRequest.setApproved(false);
                placeRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
                placeRequestRepository.save(placeRequest);
            }
        }
    }

    private void declineAllPlaceRequestsIfUserWasAcceptedOnPlace(String Username) {
        List<PlaceRequest> placeRequests = placeRequestRepository.findAll();
        for (PlaceRequest placeRequest : placeRequests) {
            if (placeRequest.getUsername().equals(Username) && placeRequest.getApproved() == null) {
                placeRequest.setApproved(false);
                placeRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
                placeRequestRepository.save(placeRequest);
            }
        }
    }
}
