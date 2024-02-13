package com.tech.dynamicapp.repository;

import com.tech.dynamicapp.entity.Company;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>{
}