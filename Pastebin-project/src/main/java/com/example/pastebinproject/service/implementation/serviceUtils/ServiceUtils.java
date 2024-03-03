package com.example.pastebinproject.service.implementation.serviceUtils;

import com.example.pastebinproject.model.TextBin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ServiceUtils {
    private static ServiceHibernateUtils hibernateUtils;
    private static final File theDir = new File("Pastebin-project/src/main/resources/BinContainer (cloud simulation)");


    @Autowired
    public ServiceUtils(ServiceHibernateUtils hibernateUtils) {
        this.hibernateUtils = hibernateUtils;
    }

    public static TextBin mergeEntityAndTableValue(TextBin textBin) {
        TextBin savedEntity = hibernateUtils.saveOrUpdateEntity(textBin);
        return  savedEntity;
    }

    public static String storeBinIntoFile(TextBin textBin) throws IOException {
        if (!theDir.exists()) {
            theDir.mkdirs();

            System.out.println("dir created, path=" + theDir.getAbsolutePath());
        }

        String fileName = textBin.getId() + ".txt";

        File file = new File(theDir + "/" + fileName);

        if(!file.exists()) {
            file.createNewFile();
            System.out.println("file=[" + fileName + "] was created");
        }

        FileWriter fileWriter = new FileWriter(theDir + "/" + fileName);
        fileWriter.write(textBin.getTextOfBin());
        fileWriter.close();

        System.out.println("{textOfBin} of file=[" + fileName + "]: " + textBin.getTextOfBin());

        return fileName;
    }

    public static TextBin generateHashFromBin(TextBin textBin, String fileName) {
        int hashOfBin = Objects.hashCode(fileName);
        textBin.setHashOfBin(hashOfBin);

        System.out.println("{hashOfBin} of file=[" + fileName + "]: " + hashOfBin);

        return textBin;
    }

    public static String generateURLFromBin(TextBin textBin) {
        String URLofBin = "http://localhost:8080/textBin/getBin/" + String.valueOf(textBin.getHashOfBin());
        return  URLofBin;
    }

    public static List<String> getListOfHash(List<TextBin> listOfAllBins) {
        List<String> listOfHash = listOfAllBins.stream()
                .map(TextBin::getHashOfBin)
                .map(el -> String.valueOf(el))
                .collect(Collectors.toList());

        System.out.println("List of ALL Available {hashOfBins}=" + listOfHash + "");

        return listOfHash;
    }
}
