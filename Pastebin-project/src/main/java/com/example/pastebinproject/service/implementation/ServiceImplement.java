package com.example.pastebinproject.service.implementation;

import com.example.pastebinproject.model.TextBin;
import com.example.pastebinproject.repository.TextBinRepository;
import com.example.pastebinproject.service.TextBinService;
import com.example.pastebinproject.service.implementation.serviceUtils.CloudSimulation;
import com.example.pastebinproject.service.implementation.serviceUtils.ServiceHibernateUtils;
import com.example.pastebinproject.service.implementation.serviceUtils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Text;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceImplement implements TextBinService {
    private final TextBinRepository repository;
    private List<TextBin> listOfAllAvailableBins = new ArrayList<>();

    @Autowired
    public ServiceImplement(TextBinRepository repository) {
        this.repository = repository;
    }

    @Override
    public String saveBin(TextBin textBin, HttpServletRequest request) throws IOException {
        // автоинкремент id-объекта (merge объекта с таблицей)
        textBin = ServiceUtils.mergeEntityAndTableValue(textBin);
        String fileName = CloudSimulation.storeBinIntoFile(textBin);
        textBin = ServiceUtils.generateHashFromBin(textBin, fileName);

        repository.save(textBin);

        return ServiceUtils.generateURLFromBin(textBin, request);
    }

    @Override
    public TextBin getBin(int hashOfBin) throws IOException {
        getAllTextBin();
        TextBin textBin = ServiceUtils.checkForBin(hashOfBin, listOfAllAvailableBins);

        return textBin;
    }

    @Override
    public Map<Long, String> getAllBin(HttpServletRequest request) {
        Map<Long, String> listOfLinksToBins = new HashMap<>();
        getAllTextBin();

        for (TextBin textBin: listOfAllAvailableBins) {
            listOfLinksToBins.put(textBin.getId(), ServiceUtils.generateURLFromBin(textBin, request));
        }

        //log - End
        System.out.println();

        return listOfLinksToBins;
    }

    private List<TextBin> getAllTextBin() {
        listOfAllAvailableBins = (List<TextBin>) repository.findAll();
        return listOfAllAvailableBins;
    }

}
