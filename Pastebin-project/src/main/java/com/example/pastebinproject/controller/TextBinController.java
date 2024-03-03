package com.example.pastebinproject.controller;

import com.example.pastebinproject.Utils.DevelopmentServices;
import com.example.pastebinproject.model.TextBin;
import com.example.pastebinproject.service.TextBinService;
import com.example.pastebinproject.service.implementation.serviceUtils.CloudSimulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
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
    public String createBin(@RequestBody TextBin textBin, HttpServletRequest request) throws IOException {
        //log - Start
        System.out.println();
        System.out.println(request.getRequestURI());
        System.out.println(DevelopmentServices.consoleMessage() + "invoked EndPoint={" + request.getRequestURL() + "}");

        String urlForBin = service.saveBin(textBin);

        //log - End
        System.out.println();

        return "Your Bin = {" + textBin.getTextOfBin() + "} was successfully saved" +
                '\n' + "Url of your Bin = " + urlForBin;
    }

    @GetMapping("/getBin/{hashOfBin}")
    public String getBin(@PathVariable int hashOfBin, HttpServletRequest request) throws IOException {
        //log - Start
        System.out.println();
        System.out.println(request.getRequestURI());
        System.out.println(DevelopmentServices.consoleMessage() + "invoked EndPoint={" + request.getRequestURL() + "}");

        TextBin textBin = service.getBin(hashOfBin);

        //log - End
        System.out.println();

        if (textBin != null) {
            return "Bin from this URL = {" + textBin.getTextOfBin() + "}";
        }

        return "NO such Bin".toUpperCase(Locale.ROOT);
    }

    @GetMapping("/getBin/all")
    public List<TextBin> getAllBin() {
        return service.getAllBin();
    }


}
