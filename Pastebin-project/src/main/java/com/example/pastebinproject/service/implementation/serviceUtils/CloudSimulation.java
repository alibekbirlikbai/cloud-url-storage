package com.example.pastebinproject.service.implementation.serviceUtils;

import com.example.pastebinproject.Utils.DevelopmentServices;
import com.example.pastebinproject.model.TextBin;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CloudSimulation {
    private static final File theDir = new File("Pastebin-project/src/main/resources/BinContainer (cloud simulation)");
    private static Map<String, String> listOfFiles = new HashMap<>();
    private static File[] files;


    public static String storeBinIntoFile(TextBin textBin) throws IOException {
        String fileName = createFile(textBin);
        writeBinIntoFile(textBin, fileName);

        return fileName;
    }

    private static String createFile(TextBin textBin) throws IOException {
        if (!theDir.exists()) {
            theDir.mkdirs();

            //log
            System.out.println(DevelopmentServices.consoleMessage()+ "dir created, path=" + theDir.getAbsolutePath());
        }

        String fileName = textBin.getId() + ".txt";
        File file = new File(theDir + "/" + fileName);

        if(!file.exists()) {
            file.createNewFile();
            //log
            System.out.println(DevelopmentServices.consoleMessage() + "file=[" + fileName + "] was created");
        }

        return fileName;
    }

    private static void writeBinIntoFile(TextBin textBin, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(theDir + "/" + fileName);
        fileWriter.write(textBin.getTextOfBin());
        fileWriter.close();

        //log
        System.out.println(DevelopmentServices.consoleMessage() + "{textOfBin} of file=[" + fileName + "]: " + textBin.getTextOfBin());
    }


    // Достает список всех доступных файлов в Папке
    // Ключ = (Название файла)
    // Значение = (Текст Внутри файла)
    public static Map<String, String> getListOfAllAvailableFiles() throws IOException {
        if (!theDir.exists()) {
            return null;
        }

        updateFileList();
        //log
        System.out.println(DevelopmentServices.consoleMessage() + "List of all {Records from Cloud} (files)=" + listOfFiles);

        return listOfFiles;
    }

    private static String getTextFromFile(String fileName) throws IOException {
        String textFromFile;

        File file = new File(theDir + "/" + fileName);
        BufferedReader fileReader = new BufferedReader(new FileReader(file));

        while ((textFromFile = fileReader.readLine()) != null) {
            return textFromFile;
        }

        return null;
    }

    private static void updateFileList() throws IOException {
        files = new File(String.valueOf(theDir)).listFiles();

        for (File file: files) {
            if (file.isFile()) {
                String fileName = file.getName();

                listOfFiles.put(fileName, getTextFromFile(fileName));
            }
        }
    }

}
