package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.services.PlaceService;
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

    @RequestMapping(value = "/offices/{officeNumber}/places", method = RequestMethod.GET)
    public ResponseEntity getPlacesByOfficeNumber(@PathVariable int officeNumber) {
        List<Place> places = placeService.getPlacesByOfficeNumber(officeNumber);
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

    @RequestMapping(value = "/places", method = RequestMethod.PUT)
    public ResponseEntity moveUserPlace(@RequestParam("office") Integer office, @RequestParam("coordinateX") Integer coordinateX, @RequestParam("coordinateY") Integer coordinateY, @RequestBody Place place) {
        Place movePlace = placeService.moveUserPlace(office, coordinateX, coordinateY, place);
        return ResponseEntity.ok().body(movePlace);
    }
}

