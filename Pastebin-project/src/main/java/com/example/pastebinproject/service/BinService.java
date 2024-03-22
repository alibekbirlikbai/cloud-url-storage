package com.example.pastebinproject.service;

import com.example.pastebinproject.model.Bin;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface BinService {
    Bin saveBin(Bin bin, HttpServletRequest request) throws IOException;
    Bin getBin(int hashOfBin, String password) throws IOException;
    List<Bin> getAllBins();
    List<Bin> searchByCategory(String category);
}
