package com.enviro.assessment.grad001.FlorahWeni.imgFile;

import org.junit.Before;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.net.URI;


import static org.junit.jupiter.api.Assertions.*;

class TransformationMagicTest {
    List<String[]> list1 = new ArrayList<>();
    List<String[]> list2 = new ArrayList<>();

    String originalInput = "com/enviro/assessment/grad001/FlorahWeni/assets/IceSand.jpeg";
    String encodedUrl = Base64.getEncoder().encodeToString(originalInput.getBytes());

    String decodedUrl;

    String testImgPath ="";

    File testFile = new File(testImgPath);


    @Before
    public void init() {


        list1.add(new String[]{"Ginger", "Bread-Man", "image/jpg",encodedUrl});
        list1.add(new String[]{"Ice-cream", "Sandwich", "image/jpeg",encodedUrl});

        list2.add(new String[]{"Ginger", "Bread-Man", "image/jpg",encodedUrl});
        list2.add(new String[]{"Ice-cream", "Sandwich", "image/jpeg",encodedUrl});
    }

    @Test
    void parseCSV() {
        assertEquals(list1.size(), list2.size());
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list1.get(i).length; j++) {
                assertEquals(list1.get(i)[j], list2.get(i)[j]);
            }
        }
    }

    @Test
    void convertCSVDataToImage() {
        String content = encodedUrl;
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedUrl);
        decodedUrl = new String(decodedBytes);
        System.out.println(new File(decodedUrl));
        assertTrue(content.matches("^(([\\w+/]{4}){19}\r\n)*(([\\w+/]{4})*([\\w+/]{4}|[\\w+/]{3}=|[\\w+/]{2}==))$"
        ));
    }

    @Test
    void createImageLink() {
        testImgPath = encodedUrl;
        URI link = testFile.toURI();
        assertTrue(link.isAbsolute());
    }
}