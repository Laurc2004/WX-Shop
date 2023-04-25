package com.lrc.mapper;

import com.lrc.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
    List<UserInfo> getUserByName(@Param("name") String name);

    Integer checkRepeat(@Param("column") String column,@Param("value") String value);
}