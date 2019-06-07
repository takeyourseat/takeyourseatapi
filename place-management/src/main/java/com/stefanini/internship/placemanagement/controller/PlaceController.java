package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;

    @RequestMapping(value = "/offices/{officeId}/places", method = RequestMethod.GET)
    public ResponseEntity getPlacesByOfficeId(@PathVariable Long officeId) {
        if (placeRepository.getPlacesByOfficeId(officeId).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(placeRepository.getPlacesByOfficeId(officeId));
    }

    @RequestMapping(value = "/places/{id}", method = RequestMethod.GET)
    public ResponseEntity getPlaceById(@PathVariable Long id) {
        if (placeRepository.getPlaceById(id) == null) {
            return new ResponseEntity<>("place not found", HttpStatus.NOT_FOUND);
        } else
            return ResponseEntity.status(HttpStatus.OK).body(placeRepository.getPlaceById(id));
    }

    @RequestMapping(value = "/places", method = RequestMethod.POST)
    public ResponseEntity addPlace(@RequestBody Place place) {
        placeRepository.save(place);
        return ResponseEntity.status(HttpStatus.CREATED).body(place);
    }

    @RequestMapping(value = "/places/{placeId}", method = RequestMethod.PATCH)
    public HttpEntity<?> moveUserPlace(@PathVariable("placeId") Long id, @RequestBody Place place) {
        Place oldPlace = placeRepository.getPlacesByUserId(place.getUserId());
        Place newPlace = placeRepository.getPlaceById(id);
        if (oldPlace == null) {
            return new ResponseEntity<>("Old place can't be found", HttpStatus.BAD_REQUEST);
        }
        if (newPlace == null) {
            return new ResponseEntity<>("New place can't be found", HttpStatus.BAD_REQUEST);
        } else if (newPlace.getUserId() != null) {
            return new ResponseEntity<>("Place is busy", HttpStatus.CONFLICT);
        } else {
            oldPlace.setUserId(null);
            newPlace.setUserId(place.getUserId());
            placeRepository.save(oldPlace);
            placeRepository.saveAndFlush(newPlace);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }
}

