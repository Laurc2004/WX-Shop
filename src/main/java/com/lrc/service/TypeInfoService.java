package com.lrc.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrc.common.ResultCode;
import com.lrc.entity.TypeInfo;
import com.lrc.exception.CustomException;
import com.lrc.mapper.TypeInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeInfoService {
    @Resource
    private TypeInfoMapper typeInfoMapper;


    public PageInfo<TypeInfo> findPage(Integer pageNum,Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<TypeInfo> users = typeInfoMapper.getUserByName(name);
        return PageInfo.of(users);
    }

    public TypeInfo addType(TypeInfo typeInfo){
        typeInfoMapper.insertSelective(typeInfo);
        return typeInfo;
    }

    public void updateType(TypeInfo typeInfo){
       typeInfoMapper.updateByPrimaryKeySelective(typeInfo);
    }

    public void delete(Integer id) {
        typeInfoMapper.deleteByPrimaryKey(id);
    }

    public TypeInfo findTypeById(Integer id){
        return typeInfoMapper.selectByPrimaryKey(id);
    }
}
