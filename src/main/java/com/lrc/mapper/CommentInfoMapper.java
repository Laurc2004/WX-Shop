package com.lrc.mapper;

import com.lrc.entity.CommentInfo;

public interface CommentInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommentInfo record);

    int insertSelective(CommentInfo record);

    CommentInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentInfo record);

    int updateByPrimaryKeyWithBLOBs(CommentInfo record);

    int updateByPrimaryKey(CommentInfo record);
}