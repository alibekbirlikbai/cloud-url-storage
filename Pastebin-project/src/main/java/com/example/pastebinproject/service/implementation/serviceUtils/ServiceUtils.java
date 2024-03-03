package com.example.pastebinproject.service.implementation.serviceUtils;

import com.example.pastebinproject.Utils.DevelopmentServices;
import com.example.pastebinproject.model.TextBin;
import com.example.pastebinproject.repository.TextBinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ServiceUtils {
    private static ServiceHibernateUtils hibernateUtils;
    private static TextBinRepository textBinRepository;

    @Autowired
    public ServiceUtils(ServiceHibernateUtils hibernateUtils, TextBinRepository textBinRepository) {
        this.hibernateUtils = hibernateUtils;
        this.textBinRepository = textBinRepository;
    }

    public static TextBin mergeEntityAndTableValue(TextBin textBin) {
        TextBin savedEntity = hibernateUtils.saveOrUpdateEntity(textBin);
        return  savedEntity;
    }


    public static TextBin generateHashFromBin(TextBin textBin, String fileName) {
        int hashOfBin = Objects.hashCode(fileName);
        textBin.setHashOfBin(hashOfBin);

        //log
        System.out.println(DevelopmentServices.consoleMessage() + "{hashOfBin} of file=[" + fileName + "]: " + hashOfBin);
        return textBin;
    }

    public static String generateURLFromBin(TextBin textBin) {
        String URLofBin = "http://localhost:8080/textBin/getBin/" + textBin.getHashOfBin();

        //log
        System.out.println(DevelopmentServices.consoleMessage() + "URL generated for this Bin =[" + URLofBin + "]");
        return  URLofBin;
    }

    public static TextBin checkForBin(int hashOfBin, List<TextBin> listOfAllAvailableBins) throws IOException {
        List<Integer> listOfHash = getListOfHash(listOfAllAvailableBins);

        Optional<Integer> checkResult = listOfHash.stream()
                .filter(el -> el == hashOfBin)
                .findFirst();

        if (checkResult != null) {
            return getTextOfBin(hashOfBin);
        }

        return null;
    }


    private static List<Integer> getListOfHash(List<TextBin> listOfAllAvailableBins) {
        List<Integer> listOfHash = listOfAllAvailableBins.stream()
                .map(TextBin::getHashOfBin)
                .collect(Collectors.toList());

        //log
        System.out.println(DevelopmentServices.consoleMessage() + "List of all Available {hashOfBins} (in db):\n" + listOfHash + "\n+");

        return listOfHash;
    }

    private static TextBin getTextOfBin(int hashOfBin) throws IOException {
        TextBin textBin = textBinRepository.findByHashOfBin(hashOfBin);

        // Получаем значение текста внутри файла, Хэш-код которого соответствует переданному по URL (ссылка на Bin)
        // значение инициализируется ЛОКАЛЬНО (на уровне Java),
        // (на БД поле текста Bin -> @Transient)
        iterateThroughCloudRecords(textBin, hashOfBin);

        return textBin;
    }

    private static void iterateThroughCloudRecords(TextBin textBin, int hashOfBin) throws IOException {
        Map<String, String> cloudRecords = CloudSimulation.getListOfAllAvailableFiles();

        for (Map.Entry<String, String> element : cloudRecords.entrySet()) {
            int hashOfCurrentRecord = Objects.hashCode(element.getKey());

            if (hashOfCurrentRecord == hashOfBin) {
                textBin.setTextOfBin(element.getValue());

                //log
                System.out.println(DevelopmentServices.consoleMessage() + hashOfBin + "(hash from -> URL)" + " == " + hashOfCurrentRecord + "(converted hash of record (file) from -> Cloud (file directory))");
                System.out.println(DevelopmentServices.consoleMessage() + "Text of Bin from provided URL: " + element.getValue());
            }
        }
    }
}
