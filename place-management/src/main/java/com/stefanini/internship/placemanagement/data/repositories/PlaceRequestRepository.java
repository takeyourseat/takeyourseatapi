package com.stefanini.internship.placemanagement.data.repositories;

import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRequestRepository extends JpaRepository<PlaceRequest, Long> {
    PlaceRequest getPlaceRequestById(Long id);
    List<PlaceRequest> getPlaceRequestsByUserId(Long userId);

    List<PlaceRequest> getPlaceRequestsByManagerId(Long managerId);
}
