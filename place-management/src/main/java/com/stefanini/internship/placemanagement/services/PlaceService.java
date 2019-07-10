package com.stefanini.internship.placemanagement.services;

import com.stefanini.internship.placemanagement.data.entities.Office;
import com.stefanini.internship.placemanagement.data.entities.Place;
import com.stefanini.internship.placemanagement.data.repositories.OfficeRepository;
import com.stefanini.internship.placemanagement.data.repositories.PlaceRepository;
import com.stefanini.internship.placemanagement.exception.NotAvailableException;
import com.stefanini.internship.placemanagement.exception.OutOfBoundsException;
import com.stefanini.internship.placemanagement.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private PlaceRepository placeRepository;
    private OfficeRepository officeRepository;

    public PlaceService(PlaceRepository placeRepository, OfficeRepository officeRepository) {
        this.placeRepository = placeRepository;
        this.officeRepository = officeRepository;
    }

    public List<Place> getPlacesByOfficeNumber(Integer officeNumber) {
        List<Place> places = placeRepository.getPlacesByOfficeNumber(officeNumber);
        return places;
    }

    public Place getPlaceById(Long id) {
        Place place = placeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Place with id " + id + " not found"));
        return place;
    }

    public Place addPlace(Place place) {
        Long officeId = place.getOffice().getId();
        Office office = officeRepository.getOfficeById(officeId);
        if (office.getSizeX() < place.getCoordinateX() || office.getSizeY() < place.getCoordinateY()) {
            throw new OutOfBoundsException("Can't add place with following coordinates");
        }
        placeRepository.save(place);
        return place;
    }

    public Place moveUserPlace(Integer office, Integer coordinateX, Integer coordinateY, Place place) {
        Place oldPlace = placeRepository.getPlaceByUsername(place.getUsername());
        Place newPlace = placeRepository.getPlaceByOfficeNumberAndCoordinateXAndCoordinateY(office, coordinateX, coordinateY);
        if (oldPlace == null) {
            throw new ResourceNotFoundException("Old place can't be found");
        }
        if (newPlace == null) {
            throw new ResourceNotFoundException("New place can't be found");
        } else if (newPlace.getUsername() != null) {
            throw new NotAvailableException("New place is busy");
        } else {
            oldPlace.setUsername(null);
            newPlace.setUsername(place.getUsername());
            placeRepository.save(oldPlace);
            placeRepository.save(newPlace);
            return newPlace;
        }
    }
}
