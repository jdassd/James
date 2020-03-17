package com.James.corporateportraitplatforms.controller;

import com.James.corporateportraitplatforms.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private CsvService csvService;

    @GetMapping("/new-calc-result")
    public void downloadNewCalcResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        csvService.downCsvFile(request, response);
    }
}
