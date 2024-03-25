package com.example.pastebinproject.external.google.drive;

import com.example.pastebinproject.model.Bin;
import com.example.pastebinproject.service.implementation.serviceUtils.CloudUtils;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

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

    public static String getBinFromCloud(String fileId) throws IOException {
        // Получение информации о файле по его ID
        File file = drive.files().get(fileId).execute();

        // Получение потока ввода содержимого файла
        try (InputStream inputStream = drive.files().get(fileId).executeMediaAsInputStream()) {
            // Чтение содержимого файла в байтовый массив
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] contentBytes = outputStream.toByteArray();

            // Конвертация байтового массива в строку
            String fileContent = new String(contentBytes, StandardCharsets.UTF_8);
            return fileContent;
        }
    }

    public static void deleteBinFromCloud(String fileId) throws IOException {
        drive.files().delete(fileId).execute();
        System.out.println("File with ID " + fileId + " has been deleted successfully.");
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
}
