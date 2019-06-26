package com.stefanini.internship.authorizationserver.dao.repositories;

import com.stefanini.internship.authorizationserver.dao.DataType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTypeRepository extends JpaRepository<DataType,Long> {
    DataType findByName (String name);
}
