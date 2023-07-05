package com.enviro.assessment.grad001.FlorahWeni.model;

import java.io.*;
import java.net.URI;
import java.util.*;
// import java.util.Map.Entry;
// import java.util.ArrayList;
// import java.util.List;

import javax.xml.bind.DatatypeConverter;



public class TransformationMagic implements FileParser{
    private String accHolderName;
    private String accHolderSurname;
    private String accHolderImgFormat;
    private String accHolderBase64EncodedImg;
    private String imgName;
    private String imgExt;
    public String imgBase = "";

    private ArrayList<String> base64ImgsList = new ArrayList<>();
    // private int numOfAccProfiles = 0;
    // public Map<String, Map> imgsDataMap
    //         = new HashMap<String, Map>();
    // private Map<String, String> imgsInfoBrkDwn
    //         = new HashMap<String, String>();


    @Override
    public void parseCSV(File csvFile) {
        String accProfile = "";

        int counter = 0;

        if (csvFile.exists()) {
            try {
                BufferedReader bReader = new BufferedReader(new FileReader(csvFile));
                while((accProfile = bReader.readLine()) != null) {
                    String[] accountProfiles = accProfile.split(",");
                    if (counter != 0) {
                        accHolderName = accountProfiles[0];
                        accHolderSurname = accountProfiles[1];
                        accHolderImgFormat = accountProfiles[2];
                        accHolderBase64EncodedImg = accountProfiles[3];
                        base64ImgsList.add(accHolderBase64EncodedImg);


                        // imgsInfoBrkDwn.put(accHolderImgFormat.substring(6),
                        // accHolderBase64EncodedImg);
                        // imgsDataMap.put(accHolderName+"_"+accHolderSurname, imgsInfoBrkDwn);
                    }
                    counter ++;
                }
                bReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public File convertCSVDataToImage(String base64ImageData) {
        imgName = accHolderName+"_"+accHolderSurname;

        Paths findUserPath = new Paths();
        String fullPath = findUserPath.getSpecificDir()+
        "/data-centre-division/src/main/java/com/enviro/assessment/grad001/FlorahWeni/assets/convertedImgs/";

        System.out.println("len of base64 list0: "+getEncodedImgList().size());
        System.out.println("len of base64 list1: "+base64ImgsList.size());
        for (String bsImg : base64ImgsList) {
            // imgBase = bsImg;
            System.out.println("whose there??");
            switch(accHolderImgFormat.substring(6)) {
            case "jpeg":
                imgExt = "jpeg";
                imgBase = bsImg;
                System.out.println("i am jpeg\n" + imgBase);
                break;
            case "png":
                imgExt = "png";
                imgBase = bsImg;
                System.out.println("i am png\n" + imgBase);
                break;
            default:
                imgExt = "jpg";
                imgBase = bsImg;
                System.out.println("i am jpg\n" + imgBase);
                break;
            }
            // System.out.println(imgBase);

        }

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createImageLink'");
    }

    public List<String> getEncodedImgList(){ return this.base64ImgsList; }
    // public String getOneEncodedImg() { return this.imgBase; }

    // public int getNumOfProfiles() { return this.numOfAccProfiles; }
    // public void setNumOfProfiles() {
    //     this.numOfAccProfiles = this.profiles.size();
    // }


}
