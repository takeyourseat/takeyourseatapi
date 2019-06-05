package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.repositories.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class OfficeController {

    @Autowired
    OfficeRepository officeRepository;

    @GetMapping("/offices")
    public ResponseEntity getOffices() {
        if (officeRepository.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(officeRepository.findAll());
    }

    @GetMapping("/offices/{id}")
    public ResponseEntity getOfficeById(@PathVariable Long id) {
        if (officeRepository.getOfficeById(id) == null) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(officeRepository.getOfficeById(id));
    }

    @GetMapping("floors/{floorNumber}/offices")
    public ResponseEntity getOfficesByFloor(@PathVariable int floorNumber) {
        if (officeRepository.getOfficesByFloor(floorNumber).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(officeRepository.getOfficesByFloor(floorNumber));
    }
}
