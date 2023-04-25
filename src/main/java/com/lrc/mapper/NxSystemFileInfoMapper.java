package com.lrc.mapper;

import com.lrc.entity.NxSystemFileInfo;

public interface NxSystemFileInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NxSystemFileInfo record);

    int insertSelective(NxSystemFileInfo record);

    NxSystemFileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NxSystemFileInfo record);

    int updateByPrimaryKey(NxSystemFileInfo record);
}