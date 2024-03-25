package com.example.pastebinproject.external.google.drive;

import com.example.pastebinproject.model.Bin;
import com.example.pastebinproject.service.implementation.serviceUtils.CloudUtils;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GoogleDriveService {
    // Подключение к Google Drive
    static Drive drive;

    static {
        try {
            drive = GoogleDriveConnector.connectGoogleDrive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String storeBinInCloud(Bin bin) throws IOException {
        Map<String, java.io.File> fileMap = CloudUtils.createFile(bin);

        fileUploadIntoCloud(fileMap, bin);

        // return fileName
        return fileMap.entrySet().iterator().next().getKey();
    }




    private static void fileUploadIntoCloud(Map<String, java.io.File> fileMap, Bin bin) throws IOException {
        String fileName = fileMap.entrySet().iterator().next().getKey();
        java.io.File serverFile = fileMap.entrySet().iterator().next().getValue();

        // Загрузка файла на Google Drive
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        fileMetadata.setParents(Collections.singletonList(GoogleDriveConnector.folderId));
        FileContent mediaContent = new FileContent("text/plain", serverFile);
        File file = drive.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();

        bin.setCloud_id(file.getId());
        System.out.println("File ID: " + file.getId());

        // Удаление файла с сервера
        serverFile.delete();
    }



    public static void deleteExpiredBinFromCloud(Bin bin) {}


    private static void getTextFromFile(String fileName) {}


}
