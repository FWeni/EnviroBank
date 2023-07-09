package com.enviro.assessment.grad001.FlorahWeni.controller;

import com.enviro.assessment.grad001.FlorahWeni.DataCentreDivisionApi;
import com.enviro.assessment.grad001.FlorahWeni.service.ProfileService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MvcResult;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.enviro.assessment.grad001.FlorahWeni.model.Profile;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataCentreDivisionApi.class)
@WebAppConfiguration
@AutoConfigureMockMvc
class ImageControllerTest {
    @Mock
    ProfileService profileService;
    @Autowired
    protected MockMvc mvc;

    @BeforeEach
    public void init() throws Exception {
        createProfile();
    }

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }


    @Test
    void getAllProfiles() throws Exception {
        String uri = "/v1/api/image/profiles";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Profile[] profileList = mapFromJson(content, Profile[].class);
        assertTrue(profileList.length > 0);
    }

    @Test
    void getProfileById() throws Exception {
        String uri = "/v1/api/image/profile/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Profile profile = mapFromJson(content, Profile.class);
        assertTrue(profile.toString().length() > 0);


    }

    @Test
    void getHttpImageLink() throws Exception {
//        String uri ="/v1/api/image/Momentum/Health/Momentum.png";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        String fileSystemResource = String.valueOf(mapFromJson(content, Profile.class));
//        assertTrue(fileSystemResource.length() > 0);
    }

    @Test
    void createProfile() throws Exception {
        String uri = "/v1/api/image/profile";
        URL link = new URL("file:/somewhere/over/de/rainbow.jpg");
        Profile profile = new Profile();
        profile.setId(3);
        profile.setName("Ginger");
        profile.setSurname("Bread-Man");
        profile.setHttpImgLink(link);

        String inputJson = mapToJson(profile);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, profile.getId()+": Profile is created successfully");
    }

    @Test
    void deleteProfile() throws Exception {
        String uri = "/v1/api/image/profile/3";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, ": Profile is deleted successfully");
        }
}