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

@Service
public class ServiceImplement implements TextBinService {
    private final TextBinRepository repository;
//    private List<String> listOfAllBins = new ArrayList<>();


    @Autowired
    public ServiceImplement(TextBinRepository repository) {
        this.repository = repository;
    }

    @Override
    public String saveBin(TextBin textBin) throws IOException {
        // автоинкремент id объекта (merge объекта с таблицей)
        textBin = ServiceUtils.mergeEntityAndTableValue(textBin);

        ServiceUtils.storeBinIntoFile(textBin);

        return null;
    }

//    @Override
//    public String saveBin(TextBin textBin) throws IOException {
//        System.out.println("(SERVICE start) - " + textBin.toString());
//
//        String fileName = ServiceImplementUtils.storeBinIntoFile(textBin);
//
//
//
//
//
//
//        int hashOfBin = ServiceImplementUtils.generateHashforBin(fileName, textBin);
//
//        String urlForBin = ServiceImplementUtils.generateURLforBin(hashOfBin);
//
//        repository.save(textBin);
//
//        System.out.println("(SERVICE end) - " + textBin.toString());
//
//        return urlForBin;
//    }

//    @Override
//    public Optional<TextBin> getBinFromURL(int hashOfBin) {
//        ServiceImplementUtils.getHashFromURL(hashOfBin);
//
//
//        return repository.getByHashOfBin(hashOfBin);
//    }
//
//    @Override
//    public List<String> getAllURL() {
//        List<TextBin> textBinList = (List<TextBin>) repository.findAll();
//
//        for (int i = 0; i < textBinList.size(); i++) {
//            listOfAllBins.add(String.valueOf(textBinList.get(i).getHashOfBin()));
//        }
//        System.out.println(listOfAllBins);
//
//
//        return listOfAllBins;
//    }

//    @Override
//    public List<String> getAllURL() {
//        List<TextBin> textBinList = (List<TextBin>) repository.findAll();
//
//        while(textBinList.size() < 0) {
//
//        }
//
//
//
//
//        listOfAllBins = repository.getAllByHashOfBin().stream()
//                    .map(el -> String.valueOf(el))
//                    .collect(Collectors.toList());
//
//        ListIterator<String> listIterator = listOfAllBins.listIterator();
//
//        while(listIterator.hasNext()) {
//            String hashOfBin = listIterator.next();
//            listIterator.set("http://localhost:8080/textBin/getBin/" + hashOfBin);
//        }
//
//        System.out.println(listOfAllBins);
//
//        return listOfAllBins;
//    }
}
