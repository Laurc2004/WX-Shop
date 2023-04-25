package com.lrc.mapper;

import com.lrc.entity.OrderGoodsRel;

public interface OrderGoodsRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderGoodsRel record);

    int insertSelective(OrderGoodsRel record);

    OrderGoodsRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderGoodsRel record);

    int updateByPrimaryKey(OrderGoodsRel record);
}