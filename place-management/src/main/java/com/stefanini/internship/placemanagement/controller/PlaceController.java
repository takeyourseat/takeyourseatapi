package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;

    @RequestMapping(value = "offices/{officeId}/places", method = RequestMethod.GET)
    public ResponseEntity getPlacesByOfficeId(@PathVariable Long officeId) {
        if (placeRepository.getPlacesByOfficeId(officeId).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(placeRepository.getPlacesByOfficeId(officeId));
    }

    @RequestMapping(value = "/places/{id}", method = RequestMethod.GET)
    public ResponseEntity getPlaceById(@PathVariable Long id) {
        if (placeRepository.getPlaceById(id) == null) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(placeRepository.getPlaceById(id));
    }

    @RequestMapping(value = "/places", method = RequestMethod.POST)
    public ResponseEntity addPlace(@RequestBody Place place) {
        placeRepository.save(place);
        return ResponseEntity.status(HttpStatus.CREATED).body(place);
    }
}
