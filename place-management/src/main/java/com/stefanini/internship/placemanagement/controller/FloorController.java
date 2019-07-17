package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.entities.Office;
import com.stefanini.internship.placemanagement.data.repositories.OfficeRepository;
import com.stefanini.internship.placemanagement.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/api")
public class FloorController {

    @Autowired
    OfficeRepository officeRepository;

    @GetMapping("/floors")
    public ResponseEntity getAllFloors() {
        List<Office> offices = officeRepository.findAll();
        List<Integer> floors = new ArrayList<>();
        for (Office office : offices) {
            if (!floors.contains(office.getFloor())) {
                floors.add(office.getFloor());
            }
        }
        if (floors.isEmpty()) {
            RuntimeException exception = new ResourceNotFoundException("There is no floors");
            throw exception;

        } else
            Collections.sort(floors);
        return ResponseEntity.status(HttpStatus.OK).body(floors);
    }
}
