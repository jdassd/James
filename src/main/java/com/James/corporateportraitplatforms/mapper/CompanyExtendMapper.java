package com.James.corporateportraitplatforms.mapper;

import com.James.corporateportraitplatforms.model.Company;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompanyExtendMapper {

    @Select({
            "select",
            "id, register_time, register_money, industry, city, company_type, controller_type, ",
            "controller_proportion, flag",
            "from company",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.VARCHAR, id=true),
            @Result(column="register_time", property="registerTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="register_money", property="registerMoney", jdbcType=JdbcType.VARCHAR),
            @Result(column="industry", property="industry", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="company_type", property="companyType", jdbcType=JdbcType.VARCHAR),
            @Result(column="controller_type", property="controllerType", jdbcType=JdbcType.VARCHAR),
            @Result(column="controller_proportion", property="controllerProportion", jdbcType=JdbcType.VARCHAR),
            @Result(column="flag", property="flag", jdbcType=JdbcType.VARCHAR),

            @Result(column = "id", property = "knowledgeReport",
                    one = @One(select = "com.James.corporateportraitplatforms.mapper.KnowledgeReportMapper.selectByCid", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "moneyReport",
                    one = @One(select = "com.James.corporateportraitplatforms.mapper.MoneyReportMapper.selectByCid", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "yearReport",
                    one = @One(select = "com.James.corporateportraitplatforms.mapper.YearReportMapper.selectByCid", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "tags",
                    many = @Many(select = "com.James.corporateportraitplatforms.mapper.TagExtendMapper.findByCid", fetchType = FetchType.LAZY))
    })
    Company selectByPrimaryKeyComplete(String id);
}
