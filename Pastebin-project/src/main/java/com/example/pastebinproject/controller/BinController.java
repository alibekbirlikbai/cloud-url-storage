package com.example.pastebinproject.controller;

import com.example.pastebinproject.controller.controllerUtils.ControllerUtils;
import com.example.pastebinproject.exception.BinCategoryException;
import com.example.pastebinproject.model.Bin;
import com.example.pastebinproject.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createBin(@RequestBody Bin bin,
                            HttpServletRequest request) {
        ControllerUtils.logStart(request);
        StringBuilder stringResponse = new StringBuilder();

        try {
            Bin savedBin = service.saveBin(bin, request);
            stringResponse.append("Your Bin was successfully saved!")
                    .append('\n')
                    .append("Content = " + savedBin.getContent())
                    .append('\n')
                    .append("URL = " + savedBin.getURL())
                    .append('\n')
                    .append("Category = " + savedBin.getCategory());

            ControllerUtils.logEnd();
            return ResponseEntity.ok(stringResponse);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка сохранения записи в базе данных");
        } catch (BinCategoryException customException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Несуществующая Категория");
        }
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
                return "Bin from this URL = {" + bin.getContent() + "}";
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
