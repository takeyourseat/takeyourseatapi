package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import com.stefanini.internship.placemanagement.data.entities.User;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRepository;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRequestRepository;
import com.stefanini.internship.placemanagement.data.repositories.UserRepository;
import com.stefanini.internship.placemanagement.exception.DuplicateResourceException;
import com.stefanini.internship.placemanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/api")
public class PlaceRequestController {

    @Autowired
    PlaceRequestRepository placeRequestRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/requests")
    public ResponseEntity getAllPlaceRequests() {
        if (placeRequestRepository.findAll().isEmpty()) {
            RuntimeException exception = new ResourceNotFoundException("There are no place requests");
            throw exception;
        } else
            return ResponseEntity.status(HttpStatus.OK).body(placeRequestRepository.findAll());
    }

    @GetMapping("/requests/{id}")
    public ResponseEntity getPlaceRequestById(@PathVariable Long id) {
        if (placeRequestRepository.getPlaceRequestById(id) == null) {
            RuntimeException exception = new ResourceNotFoundException("There is no place request with id = " + id);
            throw exception;
        } else
            return ResponseEntity.status(HttpStatus.OK).body(placeRequestRepository.getPlaceRequestById(id));
    }

    @RequestMapping(value = "/requests", params = "user", method = RequestMethod.GET)
    public ResponseEntity getPlaceRequestsByUser(@RequestParam Long user) {
        List<PlaceRequest> placeRequests = placeRequestRepository.getPlaceRequestsByUserId(user);
        if (placeRequests.isEmpty()){
            RuntimeException exception = new ResourceNotFoundException("The user with id = " + user + " has no place requests");
            throw exception;
        }
        return ResponseEntity.ok().body(placeRequests);
    }

    @RequestMapping(value = "/requests", params = "manager", method = RequestMethod.GET)
    public ResponseEntity getPlaceRequestsByManager(@RequestParam Long manager) {
        List<PlaceRequest> placeRequests = placeRequestRepository.getPlaceRequestsByManagerId(manager);
        if (placeRequests.isEmpty()){
            RuntimeException exception = new ResourceNotFoundException("The manager with id = " + manager + " has no place requests");
            throw exception;
        }
        List<PlaceRequest> nonApprovedPlaceRequests = new ArrayList<>();
        for (PlaceRequest placeRequest : placeRequests) {
            if (placeRequest.getApproved() == null)
                nonApprovedPlaceRequests.add(placeRequest);
        }
        if (nonApprovedPlaceRequests.isEmpty()){
            RuntimeException exception = new ResourceNotFoundException("The manager with id = " + manager + " has no pending place requests");
            throw exception;
        }
        return ResponseEntity.ok().body(nonApprovedPlaceRequests);
    }

    @PostMapping("/requests/{placeId}")
    public ResponseEntity createPlaceRequest(@PathVariable Long placeId) {
        Long userId = 5L;
        PlaceRequest placeRequest = new PlaceRequest();
        User user = userRepository.getUserById(userId);
        if (user == null){
            RuntimeException exception = new ResourceNotFoundException("User with id = " + userId + " doesn't exists");
            throw exception;
        }
        Place place = placeRepository.getPlaceById(placeId);
        if (place == null) {
            RuntimeException exception = new ResourceNotFoundException("Place with id = " + placeId + " doesn't exists");
            throw exception;
        }
        placeRequest.setUserId(user.getId());
        placeRequest.setPlace(place);
        placeRequest.setDateOf(new Timestamp(System.currentTimeMillis()));
        placeRequest.setManagerId(user.getManagerId());
        if (placeRequest.getUserId().equals(place.getUserId())) {
            RuntimeException exception = new DuplicateResourceException("The user with id = " + userId + "is already on the place with id = "+ placeId);
            throw exception;
        }
        if (place.getUserId() != null) {
            RuntimeException exception = new DuplicateResourceException("The place with id = " + placeId +" is occupied by another user");
            throw exception;
        }
        if (placeRequestRepository.getPlaceRequestByPlaceIdAndUserIdAndReviewedAt(placeId, placeRequest.getUserId(), placeRequest.getReviewedAt()) != null) {
            if (placeRequestRepository.getPlaceRequestByPlaceIdAndUserIdAndReviewedAt(placeId, placeRequest.getUserId(), placeRequest.getReviewedAt()).getApproved() == null) {
                RuntimeException exception = new DuplicateResourceException("such place request is pending");
                throw exception;
            } else if (!placeRequestRepository.getPlaceRequestByPlaceIdAndUserIdAndReviewedAt(placeId, placeRequest.getUserId(), placeRequest.getReviewedAt()).getApproved())
                return ResponseEntity.ok().body(placeRequestRepository.save(placeRequest));
        }
        return ResponseEntity.ok().body(placeRequestRepository.save(placeRequest));
    }

    @Transactional
    @PutMapping(value = "/requests/{id}")
    public ResponseEntity declinePlaceRequest(@PathVariable Long id) {
        PlaceRequestErrorHandler(id);
        PlaceRequest newPlaceRequest = placeRequestRepository.getPlaceRequestById(id);
        newPlaceRequest.setApproved(false);
        newPlaceRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok().body(newPlaceRequest);
    }

    @Transactional
    @PatchMapping("/requests/{id}")
    public ResponseEntity acceptPlaceRequest(@PathVariable Long id) {
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
        return ResponseEntity.ok().body(updatedPlaceRequest);
    }

    private void PlaceRequestErrorHandler(@PathVariable Long id) {
        if (placeRequestRepository.getPlaceRequestById(id) == null) {
            RuntimeException exception = new ResourceNotFoundException("Place request with id = " + id + " doesn't exists");
            throw exception;
        }
        if (placeRequestRepository.getPlaceRequestById(id).getApproved() != null) {
            RuntimeException exception = new DuplicateResourceException("Place request with id = " + id + " already has a response");
            throw exception;
        }
    }

    @Transactional
    public void declineAllPlaceRequestsIfPlaceAccepted(Long placeId) {
        List<PlaceRequest> placeRequests = placeRequestRepository.findAll();
        for (PlaceRequest placeRequest : placeRequests) {
            if (placeRequest.getPlace().getId().equals(placeId) && placeRequest.getApproved() == null) {
                placeRequest.setApproved(false);
                placeRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
                placeRequestRepository.save(placeRequest);
            }
        }
    }

    @Transactional
    public void declineAllPlaceRequestsIfUserWasAcceptedOnPlace(Long userId) {
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
