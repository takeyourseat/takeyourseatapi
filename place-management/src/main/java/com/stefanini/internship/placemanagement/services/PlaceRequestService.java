package com.stefanini.internship.placemanagement.services;

import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import com.stefanini.internship.placemanagement.data.entities.User;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRepository;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRequestRepository;
import com.stefanini.internship.placemanagement.data.repositories.UserRepository;
import com.stefanini.internship.placemanagement.exception.DuplicateResourceException;
import com.stefanini.internship.placemanagement.exception.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PlaceRequestService {

    private final static Logger logger = Logger.getLogger(PlaceRequestService.class);


    private PlaceRequestRepository placeRequestRepository;
    private PlaceRepository placeRepository;
    private UserRepository userRepository;

    public PlaceRequestService(PlaceRequestRepository placeRequestRepository, PlaceRepository placeRepository, UserRepository userRepository) {
        this.placeRequestRepository = placeRequestRepository;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
    }

    public List<PlaceRequest> getPlaceRequestsByUser(Long user) {
        List<PlaceRequest> placeRequests = placeRequestRepository.getPlaceRequestsByUserId(user);
        if (placeRequests.isEmpty()) {
            throw new ResourceNotFoundException("The user with id = " + user + " has no place requests");
        }
        return placeRequests;
    }

    public List<PlaceRequest> getPlaceRequestsByManager() {
        List<PlaceRequest> placeRequests = placeRequestRepository.getPlaceRequestsByApprovedIsNull();
        if (placeRequests.isEmpty()) {
            RuntimeException exception = new ResourceNotFoundException("There are no pending place requests");
            logger.info("There are no pending place requests", exception);
            throw exception;
        }
        return placeRequests;
    }

    public PlaceRequest createPlaceRequest(Long placeId) {
        Long userId = 3L;
        PlaceRequest placeRequest = new PlaceRequest();
        User user = userRepository.getUserById(userId);
        logger.info("User created request");
        if (user == null) {
            RuntimeException exception = new ResourceNotFoundException("User with id = " + userId + " doesn't exists");
            logger.info("User not found", exception);
            throw exception;
        }
        Place place = placeRepository.getPlaceById(placeId);
        if (place == null) {
            RuntimeException exception = new ResourceNotFoundException("Place with id = " + placeId + " doesn't exists");
            logger.info("Place not found", exception);
            throw exception;
        }
        placeRequest.setUserId(user.getId());
        placeRequest.setPlace(place);
        placeRequest.setDateOf(new Timestamp(System.currentTimeMillis()));
        placeRequest.setManagerId(user.getManagerId());
        if (placeRequest.getUserId().equals(place.getUserId())) {
            throw new DuplicateResourceException("The user with id = " + userId + "is already on the place with id = " + placeId);
        }
        if (place.getUserId() != null) {
            throw new DuplicateResourceException("The place with id = " + placeId + " is occupied by another user");
        }
        if (placeRequestRepository.getPlaceRequestByPlaceIdAndUserIdAndReviewedAt(placeId, placeRequest.getUserId(), placeRequest.getReviewedAt()) != null) {
            if (placeRequestRepository.getPlaceRequestByPlaceIdAndUserIdAndReviewedAt(placeId, placeRequest.getUserId(), placeRequest.getReviewedAt()).getApproved() == null) {
                throw new DuplicateResourceException("such place request is pending");
            } else if (!placeRequestRepository.getPlaceRequestByPlaceIdAndUserIdAndReviewedAt(placeId, placeRequest.getUserId(), placeRequest.getReviewedAt()).getApproved()) {
                placeRequestRepository.save(placeRequest);
                return placeRequest;
            }
        }
        placeRequestRepository.save(placeRequest);
        return placeRequest;
    }

    @Transactional
    public PlaceRequest declinePlaceRequest(Long id) {
        logger.info(String.format("Manager tries to decline place request with id %d", id));
        PlaceRequestErrorHandler(id);
        PlaceRequest newPlaceRequest = placeRequestRepository.getPlaceRequestById(id);
        newPlaceRequest.setApproved(false);
        newPlaceRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
        return newPlaceRequest;
    }

    @Transactional
    public PlaceRequest acceptPlaceRequest(Long id) {
        logger.info(String.format("Manager accepted place request with id %d", id));
        PlaceRequestErrorHandler(id);
        PlaceRequest updatedPlaceRequest = placeRequestRepository.getPlaceRequestById(id);
        Place placeById = placeRepository.getPlaceById(updatedPlaceRequest.getPlace().getId());
        if (placeRepository.getPlaceByUserId(updatedPlaceRequest.getUserId()) != null)
            placeRepository.getPlaceByUserId(updatedPlaceRequest.getUserId()).setUserId(null);
        updatedPlaceRequest.setApproved(true);
        updatedPlaceRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
        placeById.setUserId(updatedPlaceRequest.getUserId());
        declineAllPlaceRequestsIfPlaceAccepted(updatedPlaceRequest.getPlace().getId());
        declineAllPlaceRequestsIfUserWasAcceptedOnPlace(updatedPlaceRequest.getUserId());
        placeRepository.save(placeById);
        return updatedPlaceRequest;
    }

    private void PlaceRequestErrorHandler(@PathVariable Long id) {
        if (placeRequestRepository.getPlaceRequestById(id) == null) {
            throw new ResourceNotFoundException("Place request with id = " + id + " doesn't exists");
        }
        if (placeRequestRepository.getPlaceRequestById(id).getApproved() != null) {
            throw new DuplicateResourceException("Place request with id = " + id + " already has a response");
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

    private void declineAllPlaceRequestsIfUserWasAcceptedOnPlace(Long userId) {
        List<PlaceRequest> placeRequests = placeRequestRepository.findAll();
        for (PlaceRequest placeRequest : placeRequests) {
            if (placeRequest.getUserId().equals(userId) && placeRequest.getApproved() == null) {
                placeRequest.setApproved(false);
                placeRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
                placeRequestRepository.save(placeRequest);
            }
        }
    }
}
