package com.stefanini.internship.authorizationserver.services;

import com.stefanini.internship.authorizationserver.dao.DataType;
import com.stefanini.internship.authorizationserver.dao.repositories.DataTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataTypesService {

    private final DataTypeRepository dataTypeRepository;


    public DataTypesService(DataTypeRepository dataTypeRepository) {
        this.dataTypeRepository = dataTypeRepository;
    }

    public List<DataType> getAllDataTypes(){
        return dataTypeRepository.findAll();
    }
}
