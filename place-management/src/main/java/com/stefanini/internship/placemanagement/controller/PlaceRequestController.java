package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import com.stefanini.internship.placemanagement.services.PlaceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/api")
public class PlaceRequestController {
    private final
    PlaceRequestService placeRequestService;

    public PlaceRequestController(PlaceRequestService placeRequestService) {
        this.placeRequestService = placeRequestService;
    }

    @RequestMapping(value = "/requests", params = "user", method = RequestMethod.GET)
    public ResponseEntity getPlaceRequestsByUser(@RequestParam String user) {
        List<PlaceRequest> placeRequests = placeRequestService.getPlaceRequestsByUser(user);
        return ResponseEntity.ok().body(placeRequests);
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public ResponseEntity getPendingPlaceRequests() {
        List<PlaceRequest> placeRequests = placeRequestService.getPlaceRequestsByManager();
        return ResponseEntity.ok().body(placeRequests);
    }

    @PostMapping("/requests/{placeId}")
    public ResponseEntity createPlaceRequest(@PathVariable Long placeId) {
        PlaceRequest placeRequest = placeRequestService.createPlaceRequest(placeId);
        return ResponseEntity.ok().body(placeRequest);
    }

    @PutMapping("/requests/{id}")
    public ResponseEntity declinePlaceRequest(@PathVariable Long id) {
        PlaceRequest placeRequest = placeRequestService.declinePlaceRequest(id);
        return ResponseEntity.ok().body(placeRequest);
    }

    @PatchMapping("/requests/{id}")
    public ResponseEntity acceptPlaceRequest(@PathVariable Long id) {
        PlaceRequest placeRequest = placeRequestService.acceptPlaceRequest(id);
        return ResponseEntity.ok().body(placeRequest);
    }
}
