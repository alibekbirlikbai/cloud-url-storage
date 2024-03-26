package com.example.pastebinproject.service.implementation.serviceUtils;

import com.example.pastebinproject.model.Bin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CloudUtils {
    public static Map<String, File> createFile(Bin bin) {
        Map<String, File> fileMap = new LinkedHashMap<>();

        // Создание нового файла на сервере
        String fileName = bin.getId() + ".txt";
        File serverFile = new File(fileName);

        try (FileOutputStream fos = new FileOutputStream(serverFile)) {
            // Сразу сохраняем содержимое Bin
            fos.write(bin.getContent().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileMap.put(fileName, serverFile);

        return fileMap;
    }
}
