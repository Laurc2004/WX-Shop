package com.lrc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrc.entity.AdvertiserInfo;
import com.lrc.entity.UserInfo;
import com.lrc.mapper.AdvertiserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AdvertiserService {
    @Resource
    private AdvertiserInfoMapper advertiserInfoMapper;
    

    public AdvertiserInfo addAdvertiser(AdvertiserInfo advertiserInfo){
        advertiserInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        advertiserInfoMapper.insertSelective(advertiserInfo);
        return advertiserInfo;
    }

    public void updateAdvertiser(AdvertiserInfo advertiserInfo){
        advertiserInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
       advertiserInfoMapper.updateByPrimaryKeySelective(advertiserInfo);
    }

    public void delete(Integer id) {
        advertiserInfoMapper.deleteByPrimaryKey(id);
    }

    public AdvertiserInfo findAdvertiserById(Integer id){
        return advertiserInfoMapper.selectByPrimaryKey(id);
    }

    public PageInfo<AdvertiserInfo> findPage(Integer pageNum, Integer pageSize, String name){
        PageHelper.startPage(pageNum,pageSize);
        List<AdvertiserInfo> advertisers = advertiserInfoMapper.findByName(name);
        return PageInfo.of(advertisers);
    }
}
