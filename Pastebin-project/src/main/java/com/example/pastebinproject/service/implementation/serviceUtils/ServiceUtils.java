package com.example.pastebinproject.service.implementation.serviceUtils;

import com.example.pastebinproject.Utils.DevelopmentServices;
import com.example.pastebinproject.controller.controllerUtils.ControllerUtils;
import com.example.pastebinproject.model.TextBin;
import com.example.pastebinproject.repository.TextBinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Text;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
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


    public static int generateHashFromBin(TextBin textBin, String fileName) {
        int hashOfBin = Objects.hashCode(fileName);

        //log
        System.out.println(DevelopmentServices.consoleMessage() + "{hashOfBin} of file=[" + fileName + "]: " + hashOfBin);
        return hashOfBin;
    }

    public static String generateURLFromBin(TextBin textBin, HttpServletRequest request) {
        String URLofBin = ServiceUtils.getBaseURL(request)
                + request.getRequestURI()
                + "/" + textBin.getHashOfBin()
                + defineUrlParameters(textBin);
        //log
        System.out.println(DevelopmentServices.consoleMessage() + "URL generated for this Bin =[" + URLofBin + "]");
        return URLofBin;
    }

    public static TextBin checkForBin(int hashOfBin, List<TextBin> listOfAllAvailableBins) throws IOException {
        List<Integer> listOfHash = getListOfHash(listOfAllAvailableBins);

        Optional<Integer> checkResult = listOfHash.stream()
                .filter(el -> el == hashOfBin)
                .findFirst();
        System.out.println("checkResult: " + checkResult);

        if (checkResult.isPresent()) {
            TextBin textBin = getTextOfBin(hashOfBin);
            textBin = checkURLforExpired(textBin);
            return textBin;
        }

        return null;
    }



    private static TextBin checkURLforExpired(TextBin textBin) throws IOException {
        LocalDateTime currentTime = LocalDateTime.now();

        if (currentTime.isAfter(textBin.getExpiry_time())) {
            textBin.setExpired(true);
            textBinRepository.save(textBin);

            // Delete File from Cloud (file-directory)
            // CloudSimulation.deleteExpiredBinFromCloud(textBin);
        }

        return textBin;
    }


    private static String defineUrlParameters(TextBin textBin) {
        StringBuilder parameters_String = new StringBuilder("?");

        parameters_String.append("expiry_time=")
                .append(textBin.getExpiry_time());
                //.append("&");


        return parameters_String.toString();

        // Сделать Потом
//        for (Map.Entry<String, String> entry : parameters.entrySet()) {
//            parameters_String.append(entry.getKey())
//                    .append("=")
//                    .append(entry.getValue())
//                    .append("&");
//
//            System.out.println(entry.getKey() + "=" + entry.getValue());
//        }
//        parameters_String.deleteCharAt(parameters_String.length() - 1); // Удаляем последний символ "&"
    }

    // Определяет Порт на котором запущен Сервер
    private static String getBaseURL(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        String baseUrl = requestUrl.replace(request.getRequestURI(), request.getContextPath());

        return baseUrl;
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
        iterateThroughCloudRecords(textBin);

        return textBin;
    }

    private static void iterateThroughCloudRecords(TextBin textBin) throws IOException {
        Map<String, String> cloudRecords = CloudSimulation.getListOfAllAvailableFiles();

        for (Map.Entry<String, String> element : cloudRecords.entrySet()) {
            int hashOfCurrentRecord = Objects.hashCode(element.getKey());

            if (hashOfCurrentRecord == textBin.getHashOfBin()) {
                textBin.setTextOfBin(element.getValue());

                //log
                System.out.println(DevelopmentServices.consoleMessage() + "(HASH MANUAL CHECK) " + textBin.getHashOfBin() + "(hash from -> URL)" + " == " + hashOfCurrentRecord + "(converted hash of record (file) from -> Cloud (file directory))");
                System.out.println(DevelopmentServices.consoleMessage() + "Text of Bin from provided URL: " + element.getValue());
            }
        }
    }
}
