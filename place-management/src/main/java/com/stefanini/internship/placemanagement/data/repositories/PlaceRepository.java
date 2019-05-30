package com.stefanini.internship.placemanagement.data.repositories;

import com.stefanini.internship.placemanagement.data.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
