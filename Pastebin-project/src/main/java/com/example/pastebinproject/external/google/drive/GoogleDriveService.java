package com.example.pastebinproject.external.google.drive;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleDriveService {
    // Идентификатор (id) папки в Google Drive, в которой сохраняются файлы
    public static String folderId = "1wHDTuOQ4zCNXBnJrPVemLk5Jb0jXFf2W";

    // Путь к JSON файлу с ключом для Service Account
    private static String serviceAccountFile = "Pastebin-project/src/main/resources/service-account-auth.json";

    //  Это имя отображается в консоли разработчика Google Cloud Platform в списке авторизованных приложений
    private static final String APPLICATION_NAME = "Pastebin-project";

    // Тестируем Соединение к Google Drive
    public static void _uploadFileToGoogleDrive() throws IOException {
        // Инициализация учетных данных с помощью файла JSON
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(serviceAccountFile))
                .createScoped(Collections.singleton(DriveScopes.DRIVE_FILE));

        // Инициализация объекта Drive
        Drive drive = new Drive.Builder(credential.getTransport(), credential.getJsonFactory(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Создание нового файла "test.txt" на сервере
        String fileContent = "Hello, World!";
        java.io.File serverFile = new java.io.File("test.txt");
        try (FileOutputStream fos = new FileOutputStream(serverFile)) {
            fos.write(fileContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Загрузка файла "test.txt" на Google Drive
        File fileMetadata = new File();
        fileMetadata.setName("test.txt");
        fileMetadata.setParents(Collections.singletonList(folderId));
        FileContent mediaContent = new FileContent("text/plain", serverFile);
        File file = drive.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        System.out.println("File ID: " + file.getId());

        // Удаление файла "test.txt" с сервера
        serverFile.delete();
    }

}
