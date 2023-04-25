package com.lrc.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrc.common.ResultCode;
import com.lrc.entity.UserInfo;
import com.lrc.exception.CustomException;
import com.lrc.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private UserInfoMapper userInfoMapper;

    public UserInfo login(String name,String password){
        List<UserInfo> user = userInfoMapper.getUserByName(name);
        if (CollectionUtil.isEmpty(user)){
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        if (!SecureUtil.md5(password).equals(user.get(0).getPassword())){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return user.get(0);
    }

    public UserInfo resetPassword(String name) {
        List<UserInfo> user = userInfoMapper.getUserByName(name);
        if (CollectionUtil.isEmpty(user)){
            throw new CustomException(ResultCode.USER_NOT_EXIST_ERROR);
        }
        user.get(0).setPassword(SecureUtil.md5("123456"));
        userInfoMapper.updateByPrimaryKeySelective(user.get(0));
        return user.get(0);
    }

    public PageInfo<UserInfo> findPage(Integer pageNum,Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<UserInfo> users = userInfoMapper.getUserByName(name);
        return PageInfo.of(users);
    }

    public UserInfo addUser(UserInfo userInfo){
        Integer count = userInfoMapper.checkRepeat("name",userInfo.getName());
        if (count > 0){
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if (StrUtil.isBlank(userInfo.getPassword())){
            userInfo.setPassword(SecureUtil.md5("123456"));
        } else {
            userInfo.setPassword(SecureUtil.md5(userInfo.getPassword()));
        }
        userInfo.setLevel(3);
        userInfoMapper.insertSelective(userInfo);
        return userInfo;
    }

    public void updateUser(UserInfo userInfo){
       userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    public void delete(Integer id) {
        userInfoMapper.deleteByPrimaryKey(id);
    }
}
