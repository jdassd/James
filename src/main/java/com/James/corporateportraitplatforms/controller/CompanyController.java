package com.James.corporateportraitplatforms.controller;

import com.James.corporateportraitplatforms.model.AjaxResponseModel;
import com.James.corporateportraitplatforms.model.Company;
import com.James.corporateportraitplatforms.service.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/get-company-all")
    public AjaxResponseModel<PageInfo<Company>> getCompanyList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return AjaxResponseModel.<PageInfo<Company>>builder().code(0).data(new PageInfo<>(companyService.findAll())).build();
    }

    @GetMapping("/get-company-id")
    public AjaxResponseModel<PageInfo<Company>> getCompanyById(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10")int pageSize, String id) {
        return AjaxResponseModel.<PageInfo<Company>>builder().code(0).data(new PageInfo<>(Collections.singletonList(companyService.findById(id)))).build();
    }

    @GetMapping("/get-company-city")
    public AjaxResponseModel<PageInfo<Company>> getCompanyByCity(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, String city) {
        log.info("getCompanyByCity {}", city);
        PageHelper.startPage(pageNum, pageSize);
        return AjaxResponseModel.<PageInfo<Company>>builder().code(0).data(new PageInfo<>(companyService.findByCity(city))).build();
    }

    @GetMapping("/get-company-industry")
    public AjaxResponseModel<PageInfo<Company>> getCompanyByIndustry(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, String industry) {
        PageHelper.startPage(pageNum, pageSize);
        return AjaxResponseModel.<PageInfo<Company>>builder().code(0).data(new PageInfo<>(companyService.findByIndustry(industry))).build();
    }

    @GetMapping("/get-company-companyType")
    public AjaxResponseModel<PageInfo<Company>> getCompanyByCompanyType(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, String companyType) {
        PageHelper.startPage(pageNum, pageSize);
        return AjaxResponseModel.<PageInfo<Company>>builder().code(0).data(new PageInfo<>(companyService.findByCompanyType(companyType))).build();
    }

    @GetMapping("/get-company-controllerType")
    public AjaxResponseModel<PageInfo<Company>> getCompanyByControllerType(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, String controllerType) {
        PageHelper.startPage(pageNum, pageSize);
        return AjaxResponseModel.<PageInfo<Company>>builder().code(0).data(new PageInfo<>(companyService.findByControllerType(controllerType))).build();
    }



}
