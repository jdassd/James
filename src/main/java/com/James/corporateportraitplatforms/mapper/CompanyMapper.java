package com.James.corporateportraitplatforms.mapper;

import com.James.corporateportraitplatforms.model.Company;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface CompanyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company
     *
     * @mbg.generated Wed Feb 12 12:51:31 CST 2020
     */
    @Delete({
        "delete from company",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company
     *
     * @mbg.generated Wed Feb 12 12:51:31 CST 2020
     */
    @Insert({
        "insert into company (id, register_time, ",
        "register_money, industry, ",
        "city, company_type, ",
        "controller_type, controller_proportion, ",
        "flag)",
        "values (#{id,jdbcType=VARCHAR}, #{registerTime,jdbcType=VARCHAR}, ",
        "#{registerMoney,jdbcType=VARCHAR}, #{industry,jdbcType=VARCHAR}, ",
        "#{city,jdbcType=VARCHAR}, #{companyType,jdbcType=VARCHAR}, ",
        "#{controllerType,jdbcType=VARCHAR}, #{controllerProportion,jdbcType=VARCHAR}, ",
        "#{flag,jdbcType=VARCHAR})"
    })
    int insert(Company record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company
     *
     * @mbg.generated Wed Feb 12 12:51:31 CST 2020
     */
    @Select({
        "select",
        "id, register_time, register_money, industry, city, company_type, controller_type, ",
        "controller_proportion, flag",
        "from company",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="register_time", property="registerTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="register_money", property="registerMoney", jdbcType=JdbcType.VARCHAR),
        @Result(column="industry", property="industry", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="company_type", property="companyType", jdbcType=JdbcType.VARCHAR),
        @Result(column="controller_type", property="controllerType", jdbcType=JdbcType.VARCHAR),
        @Result(column="controller_proportion", property="controllerProportion", jdbcType=JdbcType.VARCHAR),
        @Result(column="flag", property="flag", jdbcType=JdbcType.VARCHAR)
    })
    Company selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company
     *
     * @mbg.generated Wed Feb 12 12:51:31 CST 2020
     */
    @Select({
        "select",
        "id, register_time, register_money, industry, city, company_type, controller_type, ",
        "controller_proportion, flag",
        "from company"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="register_time", property="registerTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="register_money", property="registerMoney", jdbcType=JdbcType.VARCHAR),
        @Result(column="industry", property="industry", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="company_type", property="companyType", jdbcType=JdbcType.VARCHAR),
        @Result(column="controller_type", property="controllerType", jdbcType=JdbcType.VARCHAR),
        @Result(column="controller_proportion", property="controllerProportion", jdbcType=JdbcType.VARCHAR),
        @Result(column="flag", property="flag", jdbcType=JdbcType.VARCHAR)
    })
    List<Company> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table company
     *
     * @mbg.generated Wed Feb 12 12:51:31 CST 2020
     */
    @Update({
        "update company",
        "set register_time = #{registerTime,jdbcType=VARCHAR},",
          "register_money = #{registerMoney,jdbcType=VARCHAR},",
          "industry = #{industry,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "company_type = #{companyType,jdbcType=VARCHAR},",
          "controller_type = #{controllerType,jdbcType=VARCHAR},",
          "controller_proportion = #{controllerProportion,jdbcType=VARCHAR},",
          "flag = #{flag,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Company record);
}