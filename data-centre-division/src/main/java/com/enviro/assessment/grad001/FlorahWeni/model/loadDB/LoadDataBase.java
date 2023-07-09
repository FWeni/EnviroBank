package com.enviro.assessment.grad001.FlorahWeni.model.loadDB;

import com.enviro.assessment.grad001.FlorahWeni.imgFile.Paths;
import com.enviro.assessment.grad001.FlorahWeni.imgFile.TransformationMagic;
import com.enviro.assessment.grad001.FlorahWeni.model.Profile;
import com.enviro.assessment.grad001.FlorahWeni.repository.ProfileRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadDataBase {
    public LoadDataBase() throws MalformedURLException {
        this.merger();
    }
    private List<String[]> initDataToCreate = new ArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);
    String name = getName();
    String surname = getSurname();
    URL imgLink = getImgLink();
    @Bean
    CommandLineRunner initDatabase(ProfileRepo repo) {

        return args -> {
            int id = 1;
            for (String[] data: initDataToCreate ) {
                log.info("Preloading " + repo.save(new Profile(data[0], data[1], new URL(data[2]),id)));
            }
        };
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public URL getImgLink() {
        return imgLink;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setImgLink(URL imgLink) {
        this.imgLink = imgLink;
    }

    private URL getUrlFromDir(TransformationMagic magic,String imgPath, String fileName) throws MalformedURLException {
        File directory = new File(imgPath);
        URL imgLink = null;
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File dirItem : files) {
                File filePath = new File(imgPath+"/"+fileName);
                if(dirItem.equals(filePath)){
                    imgLink = magic.createImageLink(dirItem).toURL();;
                }
            }
        }
        return imgLink;
    }
    public void merger() throws MalformedURLException {
        TransformationMagic magic = new TransformationMagic();
        Paths p = new Paths();

        String csvFileName = p.getSpecificDir()+
                "/src/main/java/com/enviro/assessment/grad001/FlorahWeni/assets/testData/1672815113084-GraduateDev_AssessmentCsv_Ref003.csv";
        String imgDir = p.getSpecificDir()+
                "/src/main/java/com/enviro/assessment/grad001/FlorahWeni/assets/convertedImgs";

        String accName ="";
        String accSurname ="";
        URL accImgLink = null;

        String metaData = "";

        magic.parseCSV(new File(csvFileName));
        for( int ind = 0; ind < magic.getCsvData().size(); ind++){
            for (int index = 0; index < magic.getCsvData().get(ind).length; index++) {
                if(index == 0) {
                    magic.imgName = magic.getCsvData().get(ind)[index];
                    accName = magic.imgName;
                    setName(accName);

                } else if(index == 1) {
                    accSurname = magic.getCsvData().get(ind)[index];
                    setSurname(accSurname);

                } else if(index == 2) {
                    magic.imgExt = magic.getCsvData().get(ind)[index].substring(6);

                } else {
                    magic.imgBase = magic.getCsvData().get(ind)[index];
                }
                metaData = accName+"#"+accSurname+"#"+accImgLink;

                if(!accName.isEmpty()){
                    accImgLink = getUrlFromDir(magic,imgDir,accName+"."+magic.imgExt);
                    setImgLink(accImgLink);
                }

            }
            magic.convertCSVDataToImage(magic.imgBase);
            initDataToCreate.add(metaData.split("#"));

        }
    }
}
