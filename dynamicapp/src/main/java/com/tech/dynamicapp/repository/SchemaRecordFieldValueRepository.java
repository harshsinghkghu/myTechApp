package com.tech.dynamicapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.dynamicapp.entity.SchemaRecordFieldValue;

public interface SchemaRecordFieldValueRepository extends JpaRepository<SchemaRecordFieldValue, Long>{
    
}