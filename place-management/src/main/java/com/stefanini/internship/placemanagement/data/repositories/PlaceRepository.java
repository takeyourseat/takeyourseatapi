package com.stefanini.internship.placemanagement.data.repositories;

import com.stefanini.internship.placemanagement.data.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> getPlacesByOfficeId(Long officeId);

    Place getPlaceById(Long placeId);

    Place getPlaceByUsername(String username);

    Place getPlaceByOfficeNumberAndCoordinateXAndCoordinateY(Integer officeNumber, Integer coordinateX, Integer coordinateY);
}
