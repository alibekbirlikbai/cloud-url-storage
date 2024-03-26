package com.example.pastebinproject.external.google.drive;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

public class GoogleDriveConnector {
    // Идентификатор (id) папки в Google Drive, в которой сохраняются файлы
    public static String folderId = "1wHDTuOQ4zCNXBnJrPVemLk5Jb0jXFf2W";

    // Путь к JSON файлу с ключом для Service Account
    private static String serviceAccountFile = "Pastebin-project/src/main/resources/service-account-auth.json";

    //  Это имя отображается в консоли разработчика Google Cloud Platform в списке авторизованных приложений
    private static final String APPLICATION_NAME = "Pastebin-project";

    // Инициализация учетных данных с помощью файла JSON
    public static Drive connectGoogleDrive() throws IOException {
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(serviceAccountFile))
                .createScoped(Collections.singleton(DriveScopes.DRIVE_FILE));

        // Инициализация объекта Drive
        return new Drive.Builder(credential.getTransport(), credential.getJsonFactory(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
