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
        String urlForBin = service.saveBin(textBin);

        System.out.println();

        return "Your textBin = {" + textBin.getTextOfBin() + "} successfully saved in System" +
                '\n' + "Url for your textBin = " + urlForBin;
    }

//    @GetMapping("/getBin/{hashOfBin}")
//    public Optional<TextBin> getBin(@PathVariable int hashOfBin) {
//        return service.getBinFromURL(hashOfBin);
//    }
//
//    @GetMapping("/getBin/all")
//    public List<String> getAllBin() {
//        return service.getAllURL();
//    }
}
