package com.James.corporateportraitplatforms.service;

import com.James.corporateportraitplatforms.mapper.CompanyShowDataMapper;
import com.James.corporateportraitplatforms.model.CompanyShowData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyShowDataService {

    @Autowired
    private CompanyShowDataMapper companyShowDataMapper;

    public List<CompanyShowData> getShowDataById(String id) {
        return companyShowDataMapper.getShowData(id);
    }
}
