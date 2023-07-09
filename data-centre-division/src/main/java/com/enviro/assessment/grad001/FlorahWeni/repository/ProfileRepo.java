package com.enviro.assessment.grad001.FlorahWeni.repository;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.enviro.assessment.grad001.FlorahWeni.model.Profile;

public interface ProfileRepo extends  CrudRepository<Profile, Integer>{
    Profile findByNameAndSurname(String name, String surname);

}
