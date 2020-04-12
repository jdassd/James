package com.James.corporateportraitplatforms.mapper;

import com.James.corporateportraitplatforms.model.Tag;
import org.apache.ibatis.annotations.*;
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

    /**
     * 根据 tid 查找所有 cid
     * @param tid
     * @return
     */
    @Select("SELECT DISTINCT cid FROM company_tag WHERE tid = #{tid}")
    List<String> selectCidListByTid(Integer tid);
}
