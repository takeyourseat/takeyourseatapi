package com.stefanini.internship.placemanagement.data.repositories;

import com.stefanini.internship.placemanagement.data.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> getPlacesByOfficeNumber(int officeNumber);

    Place getPlaceById(Long placeId);

    Place getPlaceByUsername(String username);

    Place getPlaceByOfficeNumberAndCoordinateXAndCoordinateY(int officeNumber, int coordinateX, int coordinateY);

    List<Place> getPlacesByUsernameIsNull();

    List<Place> getPlacesByOfficeNumberAndUsernameIsNull(int officeNumber);
}
