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

        return "Your Bin = {" + textBin.getTextOfBin() + "} was successfully saved" +
                '\n' + "Url of your Bin = " + urlForBin;
    }

    @GetMapping("/getBin/{hashOfBin}")
    public String getBin(@PathVariable int hashOfBin) {
        Optional<TextBin> textBin = service.getBin(hashOfBin);

        if (textBin.isPresent()) {
            return "Bin from this URL = {" + textBin.get().getTextOfBin() + "}";
        }

        return "No Such Bin";
    }

    @GetMapping("/getBin/all")
    public List<TextBin> getAllBin() {
        return service.getAllBin();
    }


}
