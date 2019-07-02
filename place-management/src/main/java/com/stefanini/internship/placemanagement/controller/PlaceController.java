package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.services.PlaceService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/api")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping(value = "/offices/{officeId}/places", method = RequestMethod.GET)
    public ResponseEntity getPlacesByOfficeId(@PathVariable Long officeId) {
        List<Place> places = placeService.getPlacesByOfficeId(officeId);
        return ResponseEntity.ok().body(places);
    }

    @RequestMapping(value = "/places/{id}", method = RequestMethod.GET)
    public ResponseEntity getPlaceById(@PathVariable Long id) {
        Place place = placeService.getPlaceById(id);
        return ResponseEntity.ok().body(place);
    }

    @RequestMapping(value = "/places", method = RequestMethod.POST)
    public ResponseEntity addPlace(@RequestBody Place place) {
        Place addPlace = placeService.addPlace(place);
        return ResponseEntity.ok().body(addPlace);
    }

    @RequestMapping(value = "/places/{placesId}", method = RequestMethod.PUT)
    public HttpEntity<?> moveUserPlace(@PathVariable("placeId") Long id, @RequestBody Place place) {
        Place movedPlace = placeService.moveUserPlace(id, place);
        return ResponseEntity.ok().body(movedPlace);
    }
}

