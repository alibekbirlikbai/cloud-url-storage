//package com.example.pastebinproject.service.implementation.serviceUtils;
//
//import com.example.pastebinproject.Utils.DevelopmentServices;
//import com.example.pastebinproject.model.Bin;
//
//import java.io.*;
//import java.util.*;
//
//public class CloudSimulation {
//    private static final File theDir = new File("Pastebin-project/src/main/resources/BinContainer (cloud simulation)");
//    private static Map<String, String> listOfFiles = new HashMap<>();
//    private static File[] files;
//
//
//    public static String storeBinInCloud(Bin bin) throws IOException {
//        String fileName = createFile(bin);
//        writeBinIntoFile(bin, fileName);
//
//        return fileName;
//    }
//
//    // Достает список всех доступных файлов в Папке
//    // Ключ = (Название файла)
//    // Значение = (Текст Внутри файла)
//    public static Map<String, String> getListOfAllAvailableFiles() throws IOException {
//        if (!theDir.exists()) {
//            return null;
//        }
//
//        updateFileList();
////        //log
////        System.out.println(DevelopmentServices.consoleMessage() + "List of all {Records from Cloud} (files):\n" + listOfFiles + "\n+");
//
//        return listOfFiles;
//    }
//
////    // ALERT! Оказывается файлы блокируются во время работы Сервера
////    public static void deleteExpiredBinFromCloud(TextBin textBin) throws IOException {
////        Map<String, String> cloudRecords = getListOfAllAvailableFiles();
////
////        for (Map.Entry<String, String> element : cloudRecords.entrySet()) {
////            int hashOfCurrentRecord = Objects.hashCode(element.getKey());
////
////            if (hashOfCurrentRecord == textBin.getHashOfBin()) {
////
////                // Так как мы не можем удалить файл во время работы сервера,
////                // потому что сервер блокирует все файлы/директории
////                // я делаю симуляцию удаления, а именно:
////                // (1) переписываю название файла на - "deactivated"
////                // +
////                // (2) обнуляю содержимое файла
////                deactivateBinInDir(element.getKey());
////
////            }
////        }
////
////    }
////
////
////    private static void deactivateBinInDir(String fileName) {
////        // Создаем объект File с указанным путем и именем файла
////        File fileToDeactivate = new File(theDir + "/" + fileName);
////
////        // Проверяем, существует ли файл перед удалением
////        if (fileToDeactivate.exists()) {
////            // (1)
////            System.out.println("before - " + fileToDeactivate);
////            File newFileName = new File(theDir + "/" + "deactivated.txt");
////
////            boolean isRenamed = fileToDeactivate.renameTo(newFileName);
////            System.out.println("isRenamed=" + isRenamed);
////            System.out.println("after - " + fileToDeactivate);
////            // (2)
////
////        } else {
////            System.out.println("File={" + fileName + "} not found");
////        }
////    }
//
//    private static String createFile(Bin bin) throws IOException {
//        if (!theDir.exists()) {
//            theDir.mkdirs();
//
////            //log
////            System.out.println(DevelopmentServices.consoleMessage()+ "dir created, path=" + theDir.getAbsolutePath());
//        }
//
//        String fileName = bin.getId() + ".txt";
//        File file = new File(theDir + "/" + fileName);
//
//        if(!file.exists()) {
//            file.createNewFile();
////            //log
////            System.out.println(DevelopmentServices.consoleMessage() + "file=[" + fileName + "] was created");
//        }
//
//        return fileName;
//    }
//
//    private static void writeBinIntoFile(Bin bin, String fileName) throws IOException {
//        FileWriter fileWriter = new FileWriter(theDir + "/" + fileName);
//        fileWriter.write(bin.getContent());
//        fileWriter.close();
//
////        //log
////        System.out.println(DevelopmentServices.consoleMessage() + "{textOfBin} of file=[" + fileName + "]: " + bin.getContent());
//    }
//
//    private static String getTextFromFile(String fileName) throws IOException {
//        String textFromFile;
//
//        File file = new File(theDir + "/" + fileName);
//        BufferedReader fileReader = new BufferedReader(new FileReader(file));
//
//        while ((textFromFile = fileReader.readLine()) != null) {
//            return textFromFile;
//        }
//
//        return null;
//    }
//
//    private static void updateFileList() throws IOException {
//        files = new File(String.valueOf(theDir)).listFiles();
//
//        for (File file: files) {
//            if (file.isFile()) {
//                String fileName = file.getName();
//
//                listOfFiles.put(fileName, getTextFromFile(fileName));
//            }
//        }
//    }
//
//}
