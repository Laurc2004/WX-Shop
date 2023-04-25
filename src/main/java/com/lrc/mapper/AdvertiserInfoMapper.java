package com.lrc.mapper;

import com.lrc.entity.AdvertiserInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AdvertiserInfoMapper extends Mapper<AdvertiserInfo> {
    List<AdvertiserInfo> findByName(@Param("name") String name);
}