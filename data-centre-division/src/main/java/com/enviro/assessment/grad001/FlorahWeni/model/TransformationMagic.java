package com.enviro.assessment.grad001.FlorahWeni.model;

import java.io.*;
import java.net.URI;
import java.util.*;

import javax.xml.bind.DatatypeConverter;



public class TransformationMagic implements FileParser{
    public String imgName;
    public String imgExt;
    public String imgBase = "";

    private List<String[]> csvData = new ArrayList<>();


    @Override
    public void parseCSV(File csvFile) {
        String line = "";
        if (csvFile.exists()) {
            try {
                BufferedReader bReader = new BufferedReader(new FileReader(csvFile));
                bReader.readLine();
                while((line = bReader.readLine()) != null) {
                    if (!line.isEmpty()) {
                        String[] accountProfile = line.split(",");
                        csvData.add(accountProfile);
                    }

                }
                bReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public File convertCSVDataToImage(String base64ImageData) {

        Paths findUserPath = new Paths();
        String fullPath = findUserPath.getSpecificDir()+
        "/src/main/java/com/enviro/assessment/grad001/FlorahWeni/assets/convertedImgs/";

        byte[] data = DatatypeConverter.parseBase64Binary(base64ImageData);
        String path = fullPath+imgName+"."+imgExt;

        File imgFile = new File(path);
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(imgFile))) {
            out.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgFile;
    }

    @Override
    public URI createImageLink(File fileImage) {
        return fileImage.toURI();
    }

    public List<String[]> getCsvData() { return csvData; }
    public void setCsvData(List<String[]> csvData ) { this.csvData = csvData; }

}
