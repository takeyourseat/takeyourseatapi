package com.stefanini.internship.placemanagement.data.controller;

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
        return ResponseEntity.status(HttpStatus.OK).body(officeRepository.findAll());
    }

    @RequestMapping(value = "/offices", params = "id", method = RequestMethod.GET)
    public ResponseEntity getOfficeById(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(officeRepository.getOfficeById(id));
    }

    @RequestMapping(value = "/offices", params = "floor", method = RequestMethod.GET)
    public ResponseEntity getOfficesByFloor(@RequestParam int floor) {
        return ResponseEntity.status(HttpStatus.OK).body(officeRepository.getOfficesByFloor(floor));
    }
}
