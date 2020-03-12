package com.James.corporateportraitplatforms.mapper;


import com.James.corporateportraitplatforms.model.CompanyShowData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import sweeneyhe.bean.ShowData;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CompanyShowDataMapper {

    @Select("SELECT id, cid, year, roe, roa, asset_liability_ratio, total_operation_income, total_assets FROM show_data WHERE cid=#{cid} ORDER BY `year` ASC")
    List<CompanyShowData> getShowData(String cid);

    void insertBatch(@Param("map") Map<Integer, List<ShowData>> showDataMap);
}
