package com.enviro.assessment.grad001.FlorahWeni;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;

import com.enviro.assessment.grad001.FlorahWeni.model.Paths;
import com.enviro.assessment.grad001.FlorahWeni.model.TransformationMagic;

public class App {
    public static void main(String[] args) throws Exception {
        Paths p = new Paths();
        TransformationMagic magic = new TransformationMagic();

        String csvFileName = p.getSpecificDir()+
        "/src/main/java/com/enviro/assessment/grad001/FlorahWeni/assets/testData/1672815113084-GraduateDev_AssessmentCsv_Ref003.csv";
        String imgDir = p.getSpecificDir()+
        "/src/main/java/com/enviro/assessment/grad001/FlorahWeni/assets/convertedImgs";



        magic.parseCSV(new File(csvFileName));
        for( int ind = 0; ind < magic.getCsvData().size(); ind++){
            for (int index = 0; index < magic.getCsvData().get(ind).length; index++) {
                if(index == 0) {
                    magic.imgName = magic.getCsvData().get(ind)[index];

                } else if(index == 2) {
                    magic.imgExt = magic.getCsvData().get(ind)[index].substring(6);

                } else {
                    magic.imgBase = magic.getCsvData().get(ind)[index];
                }

            }
            magic.convertCSVDataToImage(magic.imgBase);

        }

        File directory = new File(imgDir);
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File dirItem : files) {
                magic.createImageLink(dirItem);
            }
        }
        System.out.println("Hello Motto...");
    }
}
