package com.example.pastebinproject.controller;

import com.example.pastebinproject.Utils.DevelopmentServices;
import com.example.pastebinproject.controller.controllerUtils.ControllerUtils;
import com.example.pastebinproject.model.TextBin;
import com.example.pastebinproject.service.TextBinService;
import com.example.pastebinproject.service.implementation.serviceUtils.CloudSimulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pastbin")
public class TextBinController {
    private TextBinService service;

    @Autowired
    public TextBinController(TextBinService service) {
        this.service = service;
    }

    @PostMapping("/bins")
    public String createBin(@RequestBody TextBin textBin,
                            HttpServletRequest request) throws IOException {
        ControllerUtils.logStart(request);

        String urlForBin = service.saveBin(textBin, request);

        ControllerUtils.logEnd();
        return "Your Bin = {" + textBin.getTextOfBin() + "} was successfully saved" +
                '\n' + "Url of your Bin = " + urlForBin;
    }

    @GetMapping("/bins/{hashOfBin}")
    public String getBin(@PathVariable int hashOfBin,
                         @RequestParam(value = "expiry_time", required = true) String expiry_time,
                         HttpServletRequest request) throws IOException {
        ControllerUtils.logStart(request);

        TextBin textBin = service.getBin(hashOfBin);

        ControllerUtils.logEnd();


        if (textBin != null) {
            if (textBin.isExpired() == false) {
                return "Bin from this URL = {" + textBin.getTextOfBin() + "}";
            } else {
                return "Link has been expired";
            }
        }

        return "NO such Bin".toUpperCase(Locale.ROOT);
    }

    @GetMapping("/bins")
    public Map<Long, String> getAllBin(HttpServletRequest request) {
        ControllerUtils.logStart(request);

        return service.getAllBins(request);
    }

}
