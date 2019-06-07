package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.repositories.ReviewedRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReviewedRequestController {
    @Autowired
    ReviewedRequestRepository reviewedRequestRepository;

    @GetMapping("/reviewed")
    public ResponseEntity getAllReviewedRequests() {
        if (reviewedRequestRepository.findAll().isEmpty())
            return new ResponseEntity<>("There is no reviewed requests", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(reviewedRequestRepository.findAll());
    }

    @GetMapping("/reviewed/{id}")
    public ResponseEntity getReviewedRequestById(@PathVariable Long id){
        if (reviewedRequestRepository.getReviewedRequestById(id) == null)
            return new ResponseEntity<>("there is no such reviewed request", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(reviewedRequestRepository.getReviewedRequestById(id));
    }

    @RequestMapping(value = "/reviewed", params = "place", method = RequestMethod.GET)
    public ResponseEntity getReviewedRequestsByPlace(@RequestParam Long place){
        if (reviewedRequestRepository.getReviewedRequestsByPlaceId(place).isEmpty())
            return new ResponseEntity<>("there is no reviewed requests on this place", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(reviewedRequestRepository.getReviewedRequestsByPlaceId(place));
    }

    @RequestMapping(value = "/reviewed", params = "user", method = RequestMethod.GET)
    public ResponseEntity getReviewedRequestsByUser(@RequestParam Long user){
        if (reviewedRequestRepository.getReviewedRequestsByUserId(user).isEmpty())
            return new ResponseEntity<>("this user hasn't any reviewed requests", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(reviewedRequestRepository.getReviewedRequestsByUserId(user));
    }

    @RequestMapping(value = "/reviewed", params = "manager", method = RequestMethod.GET)
    public ResponseEntity getReviewedRequestsByManager(@RequestParam Long manager){
        if (reviewedRequestRepository.getReviewedRequestsByManagerId(manager).isEmpty())
            return new ResponseEntity<>("this user hasn't any reviewed requests", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(reviewedRequestRepository.getReviewedRequestsByManagerId(manager));
    }
}
