package com.James.corporateportraitplatforms.mapper;


import com.James.corporateportraitplatforms.model.CompanyScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import sweeneyhe.bean.ScoreBean;

import java.util.List;

@Mapper
@Repository
public interface CompanyScoreMapper {

    @Select("SELECT id, cid, score, roe, roa, " +
            "growth_rate_of_total_assets, asset_liability_ratio, " +
            "growth_rate_of_operation_income, turnover_of_total_assets FROM score WHERE cid = #{cid,jdbcType=VARCHAR}")
    CompanyScore getScoreByCid(String cid);

    void insertBatch(@Param("list") List<ScoreBean> companyScoreList);
}
