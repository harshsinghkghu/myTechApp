package com.tech.service.repository;

import com.tech.service.entity.Profile;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    
}
