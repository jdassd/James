package com.James.corporateportraitplatforms.service;

import com.James.corporateportraitplatforms.mapper.CompanyExtendMapper;
import com.James.corporateportraitplatforms.mapper.CompanyMapper;
import com.James.corporateportraitplatforms.model.Company;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyExtendMapper companyExtendMapper;


    public List<Company> findAll() {
        return companyMapper.selectAll();
    }

    public Company findById(String id) {
        return companyMapper.selectByPrimaryKey(id);
    }

    public List<Company> findByCity(String city) {
        return companyExtendMapper.selectByCity("%" + city + "%");
    }

    public List<Company> findByIndustry(String industry) {
        return companyExtendMapper.selectByIndustry("%" + industry + "%");
    }

    public List<Company> findByCompanyType(String companyType) {
        return companyExtendMapper.selectByCompanyType("%" + companyType + "%");
    }

    public List<Company> findByControllerType(String controllerType) {
        return companyExtendMapper.selectByControllerType("%" + controllerType + "%");
    }
}
