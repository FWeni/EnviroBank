package com.enviro.assessment.grad001.FlorahWeni.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enviro.assessment.grad001.FlorahWeni.model.Profile;
import com.enviro.assessment.grad001.FlorahWeni.repository.ProfileRepo;

@Service
public class ProfileService
{
@Autowired
ProfileRepo profileRepository;

public List<Profile> getAllProfiles()
{
List<Profile> profiles = new ArrayList<Profile>();
profileRepository.findAll().forEach(profile -> profiles.add(profile));
return profiles;
}
public Profile getProfileById(int id)
{
return profileRepository.findById(id).get();
}
public URL getImgProfileLink(String name, String surname)
{
return profileRepository.findByNameAndSurname(name,surname).getHttpImgLink();
}
public void createImgProfile(Profile profile)
{
profileRepository.save(profile);
}
public void deleteImgProfile(int id)
{
profileRepository.deleteById(id);
}
}