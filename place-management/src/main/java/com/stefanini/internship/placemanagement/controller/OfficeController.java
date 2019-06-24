package com.stefanini.internship.placemanagement.controller;

import com.stefanini.internship.placemanagement.data.entities.Office;
import com.stefanini.internship.placemanagement.data.repositories.OfficeRepository;
import com.stefanini.internship.placemanagement.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@CrossOrigin("http://localhost:4200")

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
    public ResponseEntity getOfficeById(@PathVariable Long id) throws ResourceNotFound {
        Office office = officeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Office with id " + id + " not found"));
        return ResponseEntity.status(HttpStatus.OK).body(office);
    }

    @RequestMapping(value = "/offices", params = "floor", method = RequestMethod.GET)
    public ResponseEntity getOfficesByFloor(@RequestParam int floor) {
        if (officeRepository.getOfficesByFloor(floor).isEmpty()) {
            return new ResponseEntity<>("Offices not found", HttpStatus.OK);
        } else
            return ResponseEntity.status(HttpStatus.OK).body(officeRepository.getOfficesByFloor(floor));
    }
}
