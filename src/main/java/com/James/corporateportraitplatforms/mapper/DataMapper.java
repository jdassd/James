package com.James.corporateportraitplatforms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DataMapper {

    @Update("CALL clean_data();")
    public void cleanData();
}
