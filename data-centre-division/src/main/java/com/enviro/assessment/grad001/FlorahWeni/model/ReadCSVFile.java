package com.enviro.assessment.grad001.FlorahWeni.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;

public class ReadCSVFile implements FileParser{
    public String accHolderName;
    public String accHolderSurname;
    public String accHolderImgFormat;
    public String accHolderBase64EncodedImg;


    @Override
    public void parseCSV(File csvFile) {
        String accProfile = "";

        // String filename =
        // "data-centre-division/src/main/java/com/enviro/assessment/grad001/FlorahWeni/assets/testData/1672815113084-GraduateDev_AssessmentCsv_Ref003.csv";

        // File f = new File(filename);
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
                        // System.out.println("AccountProfile" +counter + "[ Name = "+ accountProfiles[0] +
                        // " Surname = "+ accountProfiles[1] +
                        // " Image-format = " + accountProfiles[2] +
                        // " Base64 = " + accountProfiles[3]
                        // +"]");
                        // System.out.println(accHolderName +" "+ accHolderSurname +" "+ accHolderImgFormat+" "+accHolderBase64EncodedImg);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertCSVDataToImage'");
    }

    @Override
    public URI createImageLink(File fileImage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createImageLink'");
    }

}
