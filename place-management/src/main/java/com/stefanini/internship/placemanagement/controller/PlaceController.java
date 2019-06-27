package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.entities.Office;
import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.repositories.OfficeRepository;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRepository;
import com.stefanini.internship.placemanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/api")
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;

    @Autowired
    OfficeRepository officeRepository;

    @RequestMapping(value = "/offices/{officeId}/places", method = RequestMethod.GET)
    public ResponseEntity getPlacesByOfficeId(@PathVariable Long officeId) {
        List<Place> places = placeRepository.getPlacesByOfficeId(officeId);
        return ResponseEntity.status(HttpStatus.OK).body(places);
    }

    @RequestMapping(value = "/places/{id}", method = RequestMethod.GET)
    public ResponseEntity getPlaceById(@PathVariable Long id) {
        Place place = placeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Place with id " + id + " not found"));
        return ResponseEntity.ok().body(place);
    }

    @RequestMapping(value = "/places", method = RequestMethod.POST)
    public ResponseEntity addPlace(@RequestBody Place place) {
        Long officeId = place.getOffice().getId();
        Office office = officeRepository.getOfficeById(officeId);
        if (office.getSizeX() < place.getCoordinateX() || office.getSizeY() < place.getCoordinateY()) {
            return new ResponseEntity<>("Can't add place with following coordinates", HttpStatus.CONFLICT);
        }
        placeRepository.save(place);
        return ResponseEntity.status(HttpStatus.CREATED).body(place);
    }

    @RequestMapping(value = "/places/{placeId}", method = RequestMethod.PUT)
    public HttpEntity<?> moveUserPlace(@PathVariable("placeId") Long id, @RequestBody Place place) {
        Place oldPlace = placeRepository.getPlacesByUsername(place.getUsername());
        Place newPlace = placeRepository.getPlaceById(id);
        if (oldPlace == null) {
            return new ResponseEntity<>("Old place can't be found", HttpStatus.BAD_REQUEST);
        }
        if (newPlace == null) {
            return new ResponseEntity<>("New place can't be found", HttpStatus.BAD_REQUEST);
        } else if (newPlace.getUsername() != null) {
            return new ResponseEntity<>("Place is busy", HttpStatus.CONFLICT);
        } else {
            oldPlace.setUsername(null);
            newPlace.setUsername(place.getUsername());
            placeRepository.save(oldPlace);
            placeRepository.saveAndFlush(newPlace);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }
}

