package com.James.corporateportraitplatforms.service;

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

    public List<Company> findAll() {
        return companyMapper.selectAll();
    }

    public Company findById(String id) {
        return companyMapper.selectByPrimaryKey(id);
    }

//    public List<Company> fintBy
}
