package com.example.pastebinproject.service.implementation;

import com.example.pastebinproject.model.TextBin;
import com.example.pastebinproject.repository.TextBinRepository;
import com.example.pastebinproject.service.TextBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImplement implements TextBinService {
    private TextBinRepository repository;

    @Autowired
    public ServiceImplement(TextBinRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveBin(TextBin textBin) {
        repository.save(textBin);
    }

    @Override
    public Optional<TextBin> getBin(long id) {
        return repository.findById(id);
    }

    @Override
    public List<TextBin> getAllBins() {
        return (List<TextBin>) repository.findAll();
    }
}
