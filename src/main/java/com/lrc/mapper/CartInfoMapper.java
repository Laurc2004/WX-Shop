package com.lrc.mapper;

import com.lrc.entity.CartInfo;

public interface CartInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CartInfo record);

    int insertSelective(CartInfo record);

    CartInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CartInfo record);

    int updateByPrimaryKey(CartInfo record);
}