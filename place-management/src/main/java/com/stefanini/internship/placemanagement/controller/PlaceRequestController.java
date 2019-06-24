package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import com.stefanini.internship.placemanagement.data.entities.User;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRepository;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRequestRepository;
import com.stefanini.internship.placemanagement.data.repositories.UserRepository;
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
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(placeRequestRepository.findAll());
    }

    @GetMapping("/requests/{id}")
    public ResponseEntity getPlaceRequestById(@PathVariable Long id) {
        if (placeRequestRepository.getPlaceRequestById(id) == null) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(placeRequestRepository.getPlaceRequestById(id));
    }

    @RequestMapping(value = "/requests", params = "user", method = RequestMethod.GET)
    public ResponseEntity getPlaceRequestsByUser(@RequestParam Long user) {
        List<PlaceRequest> placeRequests = placeRequestRepository.getPlaceRequestsByUserId(user);
        return ResponseEntity.ok().body(placeRequests);
    }

    @RequestMapping(value = "/requests", params = "manager", method = RequestMethod.GET)
    public ResponseEntity getPlaceRequestsByManager(@RequestParam Long manager) {
        List<PlaceRequest> placeRequests = placeRequestRepository.getPlaceRequestsByManagerId(manager);
        List<PlaceRequest> nonApprovedPlaceRequests = new ArrayList<>();
        for (PlaceRequest placeRequest: placeRequests){
            if (placeRequest.getApproved() == null)
                nonApprovedPlaceRequests.add(placeRequest);
        }
        return ResponseEntity.ok().body(nonApprovedPlaceRequests);
    }

    @PostMapping("/requests/{placeId}")
    public ResponseEntity createPlaceRequest(@PathVariable Long placeId) {
        Long userId = 5L;
        PlaceRequest placeRequest = new PlaceRequest();
        User user = userRepository.getUserById(userId);
        if (placeRepository.getPlaceById(placeId) == null) {
            return new ResponseEntity<>("Place not found", HttpStatus.NOT_FOUND);
        }
        Place place = placeRepository.getPlaceById(placeId);
        placeRequest.setUserId(user.getId());
        placeRequest.setPlaceId(place.getId());
        placeRequest.setDateOf(new Timestamp(System.currentTimeMillis()));
        placeRequest.setManagerId(user.getManagerId());
        if (placeRequest.getUserId().equals(place.getUserId())) {
            return new ResponseEntity<>("this user is already on this place", HttpStatus.CONFLICT);
        }
        if (place.getUserId() != null) {
            return new ResponseEntity<>("This place is occupied", HttpStatus.CONFLICT);
        }
        if (placeRequestRepository.getPlaceRequestByPlaceIdAndUserIdAndReviewedAt(placeId, placeRequest.getUserId(), placeRequest.getReviewedAt()) != null) {
            if (placeRequestRepository.getPlaceRequestByPlaceIdAndUserIdAndReviewedAt(placeId, placeRequest.getUserId(),placeRequest.getReviewedAt()).getApproved() == null) {
                return new ResponseEntity<>("such placeRequest is pending", HttpStatus.CONFLICT);
            } else if (!placeRequestRepository.getPlaceRequestByPlaceIdAndUserIdAndReviewedAt(placeId, placeRequest.getUserId(),placeRequest.getReviewedAt()).getApproved())
                return ResponseEntity.ok().body(placeRequestRepository.save(placeRequest));
        }
        return ResponseEntity.ok().body(placeRequestRepository.save(placeRequest));
    }

    @Transactional
    @PutMapping(value = "/requests/{id}")
    public ResponseEntity declinePlaceRequest(@PathVariable Long id) {
        if (placeRequestRepository.getPlaceRequestById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        if (placeRequestRepository.getPlaceRequestById(id).getApproved() != null) {
            return ResponseEntity.notFound().build();
        }
        PlaceRequest newPlaceRequest = placeRequestRepository.getPlaceRequestById(id);
        newPlaceRequest.setApproved(false);
        newPlaceRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok().body(newPlaceRequest);
    }

    @Transactional
    @PatchMapping("/requests/{id}")
    public ResponseEntity acceptPlaceRequest(@PathVariable Long id) {
        if (placeRequestRepository.getPlaceRequestById(id) == null)
            return new ResponseEntity<>("placeRequest not found", HttpStatus.NOT_FOUND);
        if (placeRequestRepository.getPlaceRequestById(id).getApproved() != null)
            return new ResponseEntity<>("placeRequest already has a response", HttpStatus.CONFLICT);
        PlaceRequest updatedPlaceRequest = placeRequestRepository.getPlaceRequestById(id);
        Place placeById = placeRepository.getPlaceById(updatedPlaceRequest.getPlaceId());
        if (placeRepository.getPlaceByUserId(updatedPlaceRequest.getUserId()) != null)
            placeRepository.getPlaceByUserId(updatedPlaceRequest.getUserId()).setUserId(null);
        updatedPlaceRequest.setApproved(true);
        updatedPlaceRequest.setReviewedAt(new Timestamp(System.currentTimeMillis()));
        placeById.setUserId(updatedPlaceRequest.getUserId());
        declineAllPlaceRequestsIfPlaceAccepted(updatedPlaceRequest.getPlaceId());
        declineAllPlaceRequestsIfUserWasAcceptedOnPlace(updatedPlaceRequest.getUserId());
        placeRepository.save(placeById);
        return ResponseEntity.ok().body(updatedPlaceRequest);
    }

    @Transactional
    public void declineAllPlaceRequestsIfPlaceAccepted(Long placeId) {
        List<PlaceRequest> placeRequests = placeRequestRepository.findAll();
        for (PlaceRequest placeRequest : placeRequests) {
            if (placeRequest.getPlaceId().equals(placeId) && placeRequest.getApproved() == null) {
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
