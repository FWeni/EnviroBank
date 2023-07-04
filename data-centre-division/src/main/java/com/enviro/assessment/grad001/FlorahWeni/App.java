package com.enviro.assessment.grad001.FlorahWeni;

import java.io.File;

import com.enviro.assessment.grad001.FlorahWeni.model.ReadCSV;
import com.enviro.assessment.grad001.FlorahWeni.model.ReadCSVFile;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main ( String[] args ) throws Exception
    {
        ReadCSVFile csvv = new ReadCSVFile();
        String filename =
        "data-centre-division/src/main/java/com/enviro/assessment/grad001/FlorahWeni/assets/testData/1672815113084-GraduateDev_AssessmentCsv_Ref003.csv";

        csvv.parseCSV(new File(filename));

        System.out.println( "Hello World!" );
    }
}
