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
        if (officeRepository.findAll().isEmpty()){
            return ResponseEntity.notFound().build();
        } else
        return ResponseEntity.status(HttpStatus.OK).body(officeRepository.findAll());
    }

    @RequestMapping(value = "/offices", params = "id", method = RequestMethod.GET)
    public ResponseEntity getOfficeById(@RequestParam Long id) {
        if (officeRepository.getOfficeById(id) == null) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(officeRepository.getOfficeById(id));
    }

    @RequestMapping(value = "/offices", params = "floor", method = RequestMethod.GET)
    public ResponseEntity getOfficesByFloor(@RequestParam int floor) {
        if (officeRepository.getOfficesByFloor(floor).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(officeRepository.getOfficesByFloor(floor));
    }

    @GetMapping("/floors")
    public ResponseEntity getAllFloors() {
        List<Office> offices = officeRepository.findAll();
        List<Integer> floors = new ArrayList<>();
        for (Office office : offices) {
            if (!floors.contains(office.getFloor())) {
                floors.add(office.getFloor());
            }
        }
        if (floors.isEmpty()){
            return ResponseEntity.notFound().build();
        } else
        Collections.sort(floors);
        return ResponseEntity.status(HttpStatus.OK).body(floors);
    }
}
