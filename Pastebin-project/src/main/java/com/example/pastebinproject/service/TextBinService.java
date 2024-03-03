package com.example.pastebinproject.service;

import com.example.pastebinproject.model.TextBin;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TextBinService {
    String saveBin(TextBin textBin) throws IOException;
    Optional<TextBin> getBin(int hashOfBin);
    List<TextBin> getAllBin();
}
