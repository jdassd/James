package com.James.corporateportraitplatforms.service;

import com.James.corporateportraitplatforms.mapper.CompanyScoreMapper;
import com.James.corporateportraitplatforms.model.CompanyScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyScoreService {

    @Autowired
    private CompanyScoreMapper companyScoreMapper;

    public CompanyScore getCompanyScoreByCid(String cid) {
        return companyScoreMapper.getScoreByCid(cid);
    }
}
