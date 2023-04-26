package com.lrc.mapper;

import com.lrc.entity.TypeInfo;
import com.lrc.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TypeInfoMapper extends Mapper<TypeInfo> {
    List<TypeInfo> getUserByName(@Param("name") String name);
}