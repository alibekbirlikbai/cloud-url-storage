package com.example.pastebinproject.service;

import com.example.pastebinproject.model.TextBin;
import org.w3c.dom.Text;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TextBinService {
    String saveBin(TextBin textBin, HttpServletRequest request) throws IOException;
    TextBin getBin(int hashOfBin) throws IOException;
    Map<Long, String> getAllBins(HttpServletRequest request);
}
