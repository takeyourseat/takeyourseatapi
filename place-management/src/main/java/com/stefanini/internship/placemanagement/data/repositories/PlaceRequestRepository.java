package com.stefanini.internship.placemanagement.data.repositories;

import com.stefanini.internship.placemanagement.data.entities.PlaceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PlaceRequestRepository extends JpaRepository<PlaceRequest, Long> {
    PlaceRequest getPlaceRequestById(Long id);

    PlaceRequest getPlaceRequestByPlaceIdAndUsernameAndReviewedAt(Long placeId, String username, Timestamp timestamp);

    List<PlaceRequest> getPlaceRequestsByUsername(String username);

    List<PlaceRequest> getPlaceRequestsByReviewerAndApprovedIsNull(String reviewer);

}
