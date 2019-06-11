package com.stefanini.internship.placemanagement.data.repositories;

import com.stefanini.internship.placemanagement.data.entities.ReviewedRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewedRequestRepository extends JpaRepository<ReviewedRequest, Long> {
    ReviewedRequest getReviewedRequestById(Long reviewedRequestId);

    List<ReviewedRequest> getReviewedRequestsByPlaceId(Long placeId);

    List<ReviewedRequest> getReviewedRequestsByUserId(Long userId);

    List<ReviewedRequest> getReviewedRequestsByManagerId(Long managerId);
}
