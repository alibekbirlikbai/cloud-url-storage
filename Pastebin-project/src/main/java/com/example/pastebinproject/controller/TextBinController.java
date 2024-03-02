package com.example.pastebinproject.controller;

import com.example.pastebinproject.model.TextBin;
import com.example.pastebinproject.service.TextBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/textBin")
public class TextBinController {
    private TextBinService service;

    @Autowired
    public TextBinController(TextBinService service) {
        this.service = service;
    }

    @PostMapping("/createBin")
    public String createBin(@RequestBody TextBin textBin) throws IOException {
        service.saveBin(textBin);
        return "Your textBin={" + textBin.getTextOfBin() + "} successfully saved in System";
    }

    @GetMapping("/getBin/{id}")
    public Optional<TextBin> getBin(@PathVariable long id) {
        return service.getBin(id);
    }

    @GetMapping("/getBin/all")
    public List<TextBin> getAllBin() {
        return service.getAllBins();
    }
}
