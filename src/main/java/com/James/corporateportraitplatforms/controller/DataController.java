package com.James.corporateportraitplatforms.controller;

import com.James.corporateportraitplatforms.mapper.DataMapper;
import com.James.corporateportraitplatforms.model.AjaxResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataMapper dataMapper;

    @GetMapping("/clean-all")
    public AjaxResponseModel<String> cleanData() {
        dataMapper.cleanData();

        return AjaxResponseModel.<String>builder().code(0).build();
    }
}
