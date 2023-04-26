package com.lrc.mapper;

import com.lrc.entity.GoodsInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface GoodsInfoMapper extends Mapper<GoodsInfo> {

    List<GoodsInfo> findGoodsByName(@Param("name") String name,@Param("id") Integer id);
}