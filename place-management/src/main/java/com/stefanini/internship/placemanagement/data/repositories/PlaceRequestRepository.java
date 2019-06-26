package com.stefanini.internship.placemanagement.data.repositories;

import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PlaceRequestRepository extends JpaRepository<PlaceRequest, Long> {
    PlaceRequest getPlaceRequestById(Long id);

    PlaceRequest getPlaceRequestByPlaceIdAndUserIdAndReviewedAt(Long placeId, Long userId, Timestamp timestamp);

    List<PlaceRequest> getPlaceRequestsByUserId(Long userId);

    List<PlaceRequest> getPlaceRequestsByApprovedIsNull();

}
