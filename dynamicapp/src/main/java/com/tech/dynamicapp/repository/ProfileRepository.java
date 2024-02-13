package com.tech.dynamicapp.repository;

import com.tech.dynamicapp.entity.Profile;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    
}
