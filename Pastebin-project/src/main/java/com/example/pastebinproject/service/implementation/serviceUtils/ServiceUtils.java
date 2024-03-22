package com.example.pastebinproject.service.implementation.serviceUtils;

import com.example.pastebinproject.Utils.DevelopmentServices;
import com.example.pastebinproject.model.Bin;
import com.example.pastebinproject.repository.BinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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
    private static BinRepository binRepository;

    @Autowired
    public ServiceUtils(ServiceHibernateUtils hibernateUtils, BinRepository binRepository) {
        this.hibernateUtils = hibernateUtils;
        this.binRepository = binRepository;
    }

    public static Bin mergeEntityAndTableValue(Bin bin) {
        Bin savedEntity = hibernateUtils.saveOrUpdateEntity(bin);
        return  savedEntity;
    }


    public static int generateHashFromBin(Bin bin, String fileName) {
        int hashOfBin = Objects.hashCode(fileName);

//        //log
//        System.out.println(DevelopmentServices.consoleMessage() + "{hashOfBin} of file=[" + fileName + "]: " + hashOfBin);
        return hashOfBin;
    }

    public static String generateURLFromBin(Bin bin, HttpServletRequest request) {
        String URLofBin = ServiceUtils.getBaseURL(request)
                + request.getRequestURI()
                + "/" + bin.getHash()
                + defineUrlParameters(bin);
//        //log
//        System.out.println(DevelopmentServices.consoleMessage() + "URL generated for this Bin =[" + URLofBin + "]");
        return URLofBin;
    }

    public static Bin checkForBin(int hashOfBin, List<Bin> listOfAllAvailableBins) throws IOException {
        List<Integer> listOfHash = getListOfHash(listOfAllAvailableBins);

        Optional<Integer> checkResult = listOfHash.stream()
                .filter(el -> el == hashOfBin)
                .findFirst();
        System.out.println("checkResult: " + checkResult);

        if (checkResult.isPresent()) {
            Bin bin = getContentFromBin(hashOfBin);
            bin = checkURLforExpired(bin);
            return bin;
        }

        return null;
    }





    private static Bin checkURLforExpired(Bin bin) throws IOException {
        LocalDateTime currentTime = LocalDateTime.now();

        if (currentTime.isAfter(bin.getExpiry_time())) {
            bin.setExpired(true);
            binRepository.save(bin);

            // Delete File from Cloud (file-directory)
            // CloudSimulation.deleteExpiredBinFromCloud(textBin);
        }

        return bin;
    }

    private static String defineUrlParameters(Bin bin) {
        StringBuilder parameters_String = new StringBuilder("?");

        parameters_String.append("expiry_time=")
                .append(bin.getExpiry_time());
                //.append("&");


        return parameters_String.toString();
    }

    // Определяет Порт на котором запущен Сервер
    private static String getBaseURL(HttpServletRequest request) {
        String requestUrl = request.getRequestURL().toString();
        String baseUrl = requestUrl.replace(request.getRequestURI(), request.getContextPath());

        return baseUrl;
    }
    private static List<Integer> getListOfHash(List<Bin> listOfAllAvailableBins) {
        List<Integer> listOfHash = listOfAllAvailableBins.stream()
                .map(Bin::getHash)
                .collect(Collectors.toList());

//        //log
//        System.out.println(DevelopmentServices.consoleMessage() + "List of all Available {hashOfBins} (in db):\n" + listOfHash + "\n+");

        return listOfHash;
    }

    private static Bin getContentFromBin(int hashOfBin) throws IOException {
        Bin bin = binRepository.findByHash(hashOfBin);

        // Получаем значение текста внутри файла, Хэш-код которого соответствует переданному по URL (ссылка на Bin)
        // значение инициализируется ЛОКАЛЬНО (на уровне Java),
        // (на БД поле текста Bin -> @Transient)
        iterateThroughCloudRecords(bin);

        return bin;
    }

    private static void iterateThroughCloudRecords(Bin bin) throws IOException {
        Map<String, String> cloudRecords = CloudSimulation.getListOfAllAvailableFiles();

        for (Map.Entry<String, String> element : cloudRecords.entrySet()) {
            int hashOfCurrentRecord = Objects.hashCode(element.getKey());

            if (hashOfCurrentRecord == bin.getHash()) {
                bin.setContent(element.getValue());

//                //log
//                System.out.println(DevelopmentServices.consoleMessage() + "(HASH MANUAL CHECK) " + bin.getHash() + "(hash from -> URL)" + " == " + hashOfCurrentRecord + "(converted hash of record (file) from -> Cloud (file directory))");
//                System.out.println(DevelopmentServices.consoleMessage() + "Text of Bin from provided URL: " + element.getValue());
            }
        }
    }
}
