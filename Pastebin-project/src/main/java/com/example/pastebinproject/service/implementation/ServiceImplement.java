package com.example.pastebinproject.service.implementation;

import com.example.pastebinproject.model.Bin;
import com.example.pastebinproject.repository.BinRepository;
import com.example.pastebinproject.service.BinService;
import com.example.pastebinproject.service.implementation.serviceUtils.CloudSimulation;
import com.example.pastebinproject.service.implementation.serviceUtils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Service
public class ServiceImplement implements BinService {
    private final BinRepository repository;
    private List<Bin> listOfAllAvailableBins = new ArrayList<>();

    @Autowired
    public ServiceImplement(BinRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bin saveBin(Bin bin, HttpServletRequest request) throws IOException {
        // автоинкремент id-объекта (merge объекта с таблицей)
        bin = ServiceUtils.mergeEntityAndTableValue(bin);
        // Симуляция сохранения контента (Bin) в Cloud
        String fileName = CloudSimulation.storeBinInCloud(bin);

        int hashOfBin = ServiceUtils.generateHashFromBin(bin, fileName);
        bin.setHashOfBin(hashOfBin);

        String URLofBin = ServiceUtils.generateURLFromBin(bin, request);
        bin.setUrlOfBin(URLofBin);

        repository.save(bin);

        return bin;
    }

    @Override
    public Bin getBin(int hashOfBin) throws IOException {
        getAllAvailableBins();
        Bin bin = ServiceUtils.checkForBin(hashOfBin, listOfAllAvailableBins);

        return bin;
    }

    @Override
    public List<Bin> getAllBins(HttpServletRequest request) {
        getAllAvailableBins();

        listOfAllAvailableBins.stream().forEach(bin -> bin.setTextOfBin("--classified--"));

        //log - End
        System.out.println();

        return listOfAllAvailableBins;
    }

    private List<Bin> getAllAvailableBins() {
        listOfAllAvailableBins = (List<Bin>) repository.findAll();
        return listOfAllAvailableBins;
    }

}
