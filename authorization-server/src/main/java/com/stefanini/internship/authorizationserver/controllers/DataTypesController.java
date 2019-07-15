package com.stefanini.internship.authorizationserver.controllers;

import com.stefanini.internship.authorizationserver.dao.DataType;
import com.stefanini.internship.authorizationserver.services.DataTypesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.stefanini.internship.authorizationserver.utils.AppConstants.API_ROOT_VERSION;

@RestController
@RequestMapping(API_ROOT_VERSION+"datatypes")
public class DataTypesController {

    private final DataTypesService dataTypesService;

    public DataTypesController(DataTypesService dataTypesService) {
        this.dataTypesService = dataTypesService;
    }

    @GetMapping
    public ResponseEntity<List<DataType>> getDataTypes(){
        List<DataType> allDataTypes = dataTypesService.getAllDataTypes();
        return ResponseEntity.ok(allDataTypes);
    }
}
