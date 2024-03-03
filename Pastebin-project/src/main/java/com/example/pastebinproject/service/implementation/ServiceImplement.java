package com.example.pastebinproject.service.implementation;

import com.example.pastebinproject.model.TextBin;
import com.example.pastebinproject.repository.TextBinRepository;
import com.example.pastebinproject.service.TextBinService;
import com.example.pastebinproject.service.implementation.serviceUtils.ServiceHibernateUtils;
import com.example.pastebinproject.service.implementation.serviceUtils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceImplement implements TextBinService {
    private final TextBinRepository repository;
    private List<TextBin> listOfAllBins = new ArrayList<>();

    @Autowired
    public ServiceImplement(TextBinRepository repository) {
        this.repository = repository;
    }

    @Override
    public String saveBin(TextBin textBin) throws IOException {
        // автоинкремент id объекта (merge объекта с таблицей)
        textBin = ServiceUtils.mergeEntityAndTableValue(textBin);

        String fileName = ServiceUtils.storeBinIntoFile(textBin);

        textBin = ServiceUtils.generateHashFromBin(textBin, fileName);
        repository.save(textBin);

        String URLofBin = ServiceUtils.generateURLFromBin(textBin);

        return URLofBin;
    }

    @Override
    public Optional<TextBin> getBin(int hashOfBin) {
        listOfAllBins = getAllBin();

        List<String> listOfHash = ServiceUtils.getListOfHash(listOfAllBins);

        return Optional.empty();
    }

    @Override
    public List<TextBin> getAllBin() {
        return (List<TextBin>) repository.findAll();
    }

}
