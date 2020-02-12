package com.James.corporateportraitplatforms.mapper;

import com.James.corporateportraitplatforms.model.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface TagExtendMapper {

    @Select("SELECT tag.tid,tag.tag FROM company_tag LEFT JOIN tag ON company_tag.tid = tag.tid WHERE cid = #{cid}")
    @Results({
            @Result(column="tid", property="tid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="tag", property="tag", jdbcType=JdbcType.VARCHAR)
    })
    List<Tag> findByCid(String cid);
}
