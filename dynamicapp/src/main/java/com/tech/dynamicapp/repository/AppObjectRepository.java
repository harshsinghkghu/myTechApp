package com.tech.dynamicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tech.dynamicapp.entity.AppObject;

public interface AppObjectRepository extends JpaRepository<AppObject, Long>{
    
}
