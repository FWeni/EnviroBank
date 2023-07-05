package com.enviro.assessment.grad001.FlorahWeni;

import java.io.File;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.Map.Entry;

import com.enviro.assessment.grad001.FlorahWeni.model.TransformationMagic;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main ( String[] args ) throws Exception
    {
        // Map<String, String> newMap = new HashMap<>();
        TransformationMagic magic = new TransformationMagic();
        String filename =
        "data-centre-division/src/main/java/com/enviro/assessment/grad001/FlorahWeni/assets/testData/1672815113084-GraduateDev_AssessmentCsv_Ref003.csv";

        String toDecode = magic.imgBase;
        // System.out.println("2 de\n"+toDecode);


        magic.parseCSV(new File(filename));
        magic.convertCSVDataToImage(toDecode);


        // for (Entry<String, Map> dataImg :
        // magic.imgsDataMap.entrySet()) {
        //     magic.imgName = dataImg.getKey();
        //     newMap = dataImg.getValue();
        //     for (Entry<String, String> b64toImg :
        //         newMap.entrySet()) {
        //             System.out.println(b64toImg.getKey());
        //             switch(b64toImg.getKey()) {
        //                 case "jpeg":
        //                     magic.imgExt = "jpeg";
        //                     magic.imgBase = b64toImg.getValue();
        //                     break;
        //                 case "png":
        //                     magic.imgExt = "png";
        //                     magic.imgBase = b64toImg.getValue();
        //                     break;
        //                 default:
        //                     magic.imgExt = "jpg";
        //                     magic.imgBase = b64toImg.getValue();
        //                     break;

        //             }
        //         }
        // }

        // if(magic.getEncodedImgList().size() > 0) {
            // System.out.println(magic.getEncodedImgList().size());
            // while (magic.getEncodedImgList().size() != position) {
            //     position++;
            //     System.out.println("pos: "+position);
            // }
            // magic.convertCSVDataToImage(magic.getEncodedImgList().forEach());
        // }
        // magic.convertCSVDataToImage(magic.getEncodedImgList().get(1));

        // for (String baseImg : magic.getEncodedImgList()) {
        //     System.out.println("1 :\n"+baseImg);
        //     magic.convertCSVDataToImage(baseImg);
            // break;
            // System.out.println("yes");
        // }


        // magic.convertCSVDataToImage(magic.getEncodedImgList());

        // for (Entry<String, Map> dataImg :
        // magic.imgsDataMap.entrySet()) {
        //     magic.imgName = dataImg.getKey();
        //     magic.convertCSVDataToImage(magic.imgBase);

        // }




        System.out.println( "Hello World!" );
    }
}
