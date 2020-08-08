package com.James.corporateportraitplatforms.controller;

import com.James.corporateportraitplatforms.mapper.DataMapper;
import com.James.corporateportraitplatforms.model.AjaxResponseModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataMapper dataMapper;

    @GetMapping("/clean-all")
    public AjaxResponseModel<String> cleanData() throws IOException {
        dataMapper.cleanData();
        FileUtils.cleanDirectory(new File("downTemp"));
        return AjaxResponseModel.<String>builder().code(0).build();
    }
}
