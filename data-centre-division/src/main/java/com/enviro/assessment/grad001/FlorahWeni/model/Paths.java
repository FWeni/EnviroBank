package com.enviro.assessment.grad001.FlorahWeni.model;

public class Paths {
    private final String specificDir;

    public Paths() {
        specificDir = System.getProperty("user.dir");
    }

    public String getSpecificDir() { return this.specificDir; }
}
