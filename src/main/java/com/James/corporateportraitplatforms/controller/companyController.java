package com.James.corporateportraitplatforms.controller;

import com.James.corporateportraitplatforms.model.Company;
import com.James.corporateportraitplatforms.service.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class companyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/get-company-all")
    public PageInfo<Company> getCompanyList(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(companyService.findAll());
    }

}
