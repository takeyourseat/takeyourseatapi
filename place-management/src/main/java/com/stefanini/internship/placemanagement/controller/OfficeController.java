package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OfficeController {

    @Autowired
    OfficeRepository officeRepository;

    @GetMapping
    public ResponseEntity getOffices() {
        return ResponseEntity.status(HttpStatus.OK).body(officeRepository.findAll());
    }

    @GetMapping("/offices/{id}")
    public ResponseEntity getOfficeById(@PathVariable(value = "id") Long officeId) {
        return ResponseEntity.status(HttpStatus.OK).body(officeId);
    }

    @GetMapping("/floors/offices/")
    public ResponseEntity getOfficesByFloor(@PathVariable int floor) {
        return ResponseEntity.status(HttpStatus.OK).body(officeRepository.getOfficesByFloor(floor));
    }
}
