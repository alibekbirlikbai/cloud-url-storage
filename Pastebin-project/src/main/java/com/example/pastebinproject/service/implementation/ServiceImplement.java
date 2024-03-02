package com.example.pastebinproject.service.implementation;

import com.example.pastebinproject.Utils.DevelopmentServices;
import com.example.pastebinproject.model.TextBin;
import com.example.pastebinproject.repository.TextBinRepository;
import com.example.pastebinproject.service.TextBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImplement implements TextBinService {
    private TextBinRepository repository;
    private final File theDir = new File("Pastebin-project/src/main/resources/BinContainer (cloud simulation)");

    @Autowired
    public ServiceImplement(TextBinRepository repository) {
        this.repository = repository;
    }

    @Override
    public String saveBin(TextBin textBin) throws IOException {
        String fileName = storeBinIntoFile(textBin);
        int hashOfBin = generateHashforBin(fileName, textBin);
        
        String urlForBin = generateURLforBin(hashOfBin);

        repository.save(textBin);

        return urlForBin;
    }

    @Override
    public Optional<TextBin> getBin(long id) {
        return repository.findById(id);
    }

    @Override
    public List<TextBin> getAllBins() {
        return (List<TextBin>) repository.findAll();
    }

    private String storeBinIntoFile(TextBin textBin) throws IOException {
        if (!theDir.exists()){
            theDir.mkdirs();
            // System.out.println("dir created, path=" + theDir.getAbsolutePath());
        }

        String fileName = textBin.getId() + ".txt";

        FileWriter myWriter = new FileWriter(theDir + "/" + fileName);
        myWriter.write(textBin.getTextOfBin());
        myWriter.close();

        System.out.println(DevelopmentServices.consoleMessage() + "Successfully wrote to the file.");

        return fileName;
    }

    private int generateHashforBin(String fileName, TextBin textBin) {
        int hashOfBin = fileName.hashCode();
        textBin.setHashOfBin(hashOfBin);

        System.out.println(DevelopmentServices.consoleMessage() + "generated hashCode for this TextBin = " + hashOfBin);

        return hashOfBin;
    }

    private String generateURLforBin(int hashOfBin) {
        String urlForBin = "http://localhost:8080/textBin/" + hashOfBin;
        return urlForBin;
    }
}
