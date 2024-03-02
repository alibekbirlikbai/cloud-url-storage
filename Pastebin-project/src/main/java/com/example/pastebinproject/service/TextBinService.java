package com.example.pastebinproject.service;

import com.example.pastebinproject.model.TextBin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TextBinService {
    void saveBin(TextBin textBin);
    Optional<TextBin> getBin(long id);
    List<TextBin> getAllBins();
}
