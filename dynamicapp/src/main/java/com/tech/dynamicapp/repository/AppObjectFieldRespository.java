package com.tech.dynamicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.dynamicapp.entity.AppObjectField;

public interface AppObjectFieldRespository extends JpaRepository<AppObjectField, Long>{
    
}