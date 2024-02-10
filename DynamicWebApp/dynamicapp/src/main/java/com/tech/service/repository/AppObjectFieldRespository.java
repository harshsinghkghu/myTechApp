package com.tech.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.service.entity.AppObjectField;

public interface AppObjectFieldRespository extends JpaRepository<AppObjectField, Long>{
    
}