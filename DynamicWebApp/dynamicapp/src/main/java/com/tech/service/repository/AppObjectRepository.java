package com.tech.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.service.entity.AppObject;

public interface AppObjectRepository extends JpaRepository<AppObject, Long>{
    
}
