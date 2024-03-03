package com.example.pastebinproject.service.implementation.serviceUtils;

import com.example.pastebinproject.model.TextBin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ServiceUtils {
    private static ServiceHibernateUtils hibernateUtils;
    private static final File theDir = new File("Pastebin-project/src/main/resources/BinContainer (cloud simulation)");


    @Autowired
    public ServiceUtils(ServiceHibernateUtils hibernateUtils) {
        this.hibernateUtils = hibernateUtils;
    }


    public static void storeBinIntoFile(TextBin textBin) throws IOException {
        if (!theDir.exists()) {
            theDir.mkdirs();

            System.out.println("dir created, path=" + theDir.getAbsolutePath());
        }

        String fileName = textBin.getId() + ".txt";

        File file = new File(theDir + "/" + fileName);

        if(!file.exists()) {
            file.createNewFile();
            System.out.println(fileName + " was created");
        }


    }

    public static TextBin mergeEntityAndTableValue(TextBin textBin) {
        TextBin savedEntity = hibernateUtils.saveOrUpdateEntity(textBin);
        return  savedEntity;
    }





//    public static String storeBinIntoFile(TextBin textBin) throws IOException {
//        if (!theDir.exists()){
//            theDir.mkdirs();
//            // System.out.println("dir created, path=" + theDir.getAbsolutePath());
//        }
//
//        System.out.println("(getId() start) - " + textBin.toString());
//
//        String fileName = textBin.getId() + ".txt";
//
//        System.out.println("(getId() end) - " + textBin.toString());
//
//        File file = new File(theDir + "/" + fileName);
//
//
//        file.createNewFile();
////        System.out.println(DevelopmentServices.consoleMessage() + "Successfully create new File: " + fileName);
////        if (!file.exists()) {
////            file.createNewFile();
////            System.out.println(DevelopmentServices.consoleMessage() + "Successfully create new File: " + fileName);
////        }
//
//        FileWriter fileWriter = new FileWriter(theDir + "/" + fileName);
//        fileWriter.write(textBin.getTextOfBin());
//        fileWriter.close();
//
////        System.out.println(DevelopmentServices.consoleMessage() + "Successfully wrote Text to File: " + fileName);
//
//        return fileName;
//    }

//    public static int generateHashforBin(String fileName, TextBin textBin) {
//        int hashOfBin = fileName.hashCode();
//        textBin.setHashOfBin(hashOfBin);
//
////        System.out.println(DevelopmentServices.consoleMessage() + "generated hashCode for this TextBin = " + hashOfBin);
//
//        return hashOfBin;
//    }

//    public static String generateURLforBin(int hashOfBin) {
//        String urlForBin = "http://localhost:8080/textBin/getBin/" + hashOfBin;
//        return urlForBin;
//    }

//    public static int getHashFromURL(int hashOfBin) {
////        System.out.println(DevelopmentServices.consoleMessage() + "hash of Bin from URL = " + hashOfBin);
//        return 0;
//    }
}
