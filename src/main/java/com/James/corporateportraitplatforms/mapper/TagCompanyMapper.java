package com.James.corporateportraitplatforms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TagCompanyMapper {

    @Insert("INSERT INTO company_tag(cid, tid) VALUES (#{cid,jdbcType=VARCHAR}, #{tid,jdbcType=VARCHAR})")
    @Options(useGeneratedKeys = true)
    Long insert(String cid, int tid);

    void insertBatch(@Param("map") Map<Integer, List<Integer>> companyTagList);
}
