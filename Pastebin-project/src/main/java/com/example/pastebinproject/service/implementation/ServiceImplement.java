package com.example.pastebinproject.service.implementation;

import com.example.pastebinproject.exception.BinCategoryException;
import com.example.pastebinproject.model.Bin;
import com.example.pastebinproject.model.BinCategory;
import com.example.pastebinproject.repository.BinRepository;
import com.example.pastebinproject.service.BinService;
import com.example.pastebinproject.service.implementation.serviceUtils.CloudSimulation;
import com.example.pastebinproject.service.implementation.serviceUtils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
        if (isValidBin(bin) && isCategoryValid(bin)) {
            // автоинкремент id-объекта (merge объекта с таблицей)
            bin = ServiceUtils.mergeEntityAndTableValue(bin);
            // Симуляция сохранения контента (Bin) в Cloud
            String fileName = CloudSimulation.storeBinInCloud(bin);

            int hashOfBin = ServiceUtils.generateHashFromBin(bin, fileName);
            bin.setHash(hashOfBin);

            String URLofBin = ServiceUtils.generateURLFromBin(bin, request);
            bin.setURL(URLofBin);

            repository.save(bin);
        } else if (!isCategoryValid(bin)) {
            throw new BinCategoryException("Invalid bin category");
        } else {
            throw new IOException("Некорректные данные для сохранения в базе данных");
        }

        return bin;
    }

    @Override
    public Bin getBin(int hashOfBin, String password) throws IOException {
        getAllAvailableBins();
        Bin bin = ServiceUtils.checkForBin(hashOfBin, listOfAllAvailableBins);

        if (!ServiceUtils.checkForPassword(bin, password)) {
            bin.setPassword_match(false);
        }
        return bin;
    }

    @Override
    public List<Bin> getAllBins() {
        getAllAvailableBins();

        //log - End
        System.out.println();

        return listOfAllAvailableBins;
    }

    @Override
    public List<Bin> searchByCategory(String category) {
        List<Bin> binsByCategory = ServiceUtils.getByCategory(getAllAvailableBins(), category);
        return binsByCategory;
    }





    private List<Bin> getAllAvailableBins() {
        listOfAllAvailableBins = (List<Bin>) repository.findAll();
        listOfAllAvailableBins.stream().forEach(bin -> bin.setContent("--classified--"));

        return listOfAllAvailableBins;
    }

    private boolean isValidBin(Bin bin) {
        // Проверка всех полей объекта bin на наличие некорректных значений
        return bin != null && bin.getContent() != null && bin.getExpiry_time() != null && bin.getCategory() != null;
    }

    private boolean isCategoryValid(Bin bin) {
        String binCategory = bin.getCategory();

        for (BinCategory category : BinCategory.values()) {
            if (binCategory.equals(category.toString())) {
                return true;
            }
        }
        return false;
    }
}
