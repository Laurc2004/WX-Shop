package com.lrc.mapper;

import com.lrc.entity.TypeInfo;

public interface TypeInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TypeInfo record);

    int insertSelective(TypeInfo record);

    TypeInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TypeInfo record);

    int updateByPrimaryKey(TypeInfo record);
}