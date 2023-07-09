package com.enviro.assessment.grad001.FlorahWeni.controller;

import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

import com.enviro.assessment.grad001.FlorahWeni.model.Profile;
import com.enviro.assessment.grad001.FlorahWeni.service.ProfileService;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    @Autowired
    ProfileService profileService;

    @GetMapping(value = "/profiles")
    public List<Profile> getAllProfiles(){
        return profileService.getAllProfiles();
    }
    @GetMapping(value="/profile/{id}")
    public Profile getProfileById(@PathVariable("id") int id){
        return profileService.getProfileById(id);
    }

    @GetMapping(value = "/{name}/{surname}/{\\w\\.\\w}")
    public FileSystemResource getHttpImageLink(@PathVariable String name, @PathVariable String surname) {
        URL imageLink = profileService.getImgProfileLink(name, surname);
        return new FileSystemResource(String.valueOf(imageLink));
    }
    @PostMapping(value = "/profile")
    public int createProfile(@RequestBody Profile profile) {
        profileService.createImgProfile(profile);
        return profile.getId();
    }
     @DeleteMapping(value = "/{name}/{surname}/{id})")
     public void deleteProfile(@PathVariable("id") int id) {
         profileService.deleteImgProfile(id);
     }

}
