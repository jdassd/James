package com.James.corporateportraitplatforms.controller;

import com.James.corporateportraitplatforms.mapper.CompanyMapper;
import com.James.corporateportraitplatforms.mapper.CompanyShowDataMapper;
import com.James.corporateportraitplatforms.model.AjaxResponseModel;
import com.James.corporateportraitplatforms.model.Company;
import com.James.corporateportraitplatforms.model.CompanyShowData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/show-data")
public class ShowDataController {

    @Autowired
    private CompanyShowDataMapper companyShowDataMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @GetMapping("/get-company-show-data-list")
    public AjaxResponseModel<List<CompanyShowData>> getCompanyShowDataList(@RequestParam String cid){
        return AjaxResponseModel.<List<CompanyShowData>>builder()
                .code(0)
                .data(companyShowDataMapper.getShowData(cid))
                .msg("getting companyShowDataList successfully").build();
    }

    @GetMapping("/get-company-data-list")
    public AjaxResponseModel<Company> getCompanyDataList(@RequestParam String cid){
        return AjaxResponseModel.<Company>builder()
                .code(0)
                .data(companyMapper.selectByPrimaryKey(cid))
                .msg("getting companyDataList successfully").build();
    }
}
