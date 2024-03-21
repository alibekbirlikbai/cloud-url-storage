package com.example.pastebinproject.controller;

import com.example.pastebinproject.controller.controllerUtils.ControllerUtils;
import com.example.pastebinproject.model.Bin;
import com.example.pastebinproject.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/pastbin")
public class BinController {
    private BinService service;

    @Autowired
    public BinController(BinService service) {
        this.service = service;
    }

    @PostMapping("/bins")
    public String createBin(@RequestBody Bin bin,
                            HttpServletRequest request) throws IOException {
        ControllerUtils.logStart(request);

        Bin savedBin = service.saveBin(bin, request);

        ControllerUtils.logEnd();
        return "Your Bin = {" + savedBin.getTextOfBin() + "} was successfully saved" +
                '\n' + "Url of your Bin = " + savedBin.getUrlOfBin();
    }

    @GetMapping("/bins/{hashOfBin}")
    public String getBin(@PathVariable int hashOfBin,
                         @RequestParam(value = "expiry_time", required = true) String expiry_time,
                         HttpServletRequest request) throws IOException {
        ControllerUtils.logStart(request);

        Bin bin = service.getBin(hashOfBin);

        ControllerUtils.logEnd();


        if (bin != null) {
            if (bin.isExpired() == false) {
                return "Bin from this URL = {" + bin.getTextOfBin() + "}";
            } else {
                return "Link has been expired";
            }
        }

        return "NO such Bin".toUpperCase(Locale.ROOT);
    }

    @GetMapping("/bins")
    public List<Bin> getAllBin(HttpServletRequest request) {
        ControllerUtils.logStart(request);

        return service.getAllBins(request);
    }

}
