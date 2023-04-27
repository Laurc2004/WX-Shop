package com.lrc.service;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrc.entity.GoodsInfo;
import com.lrc.mapper.GoodsInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class GoodsInfoService {
    @Resource
    private GoodsInfoMapper goodsInfoMapper;


    public PageInfo<GoodsInfo> findPage(Integer pageNum,Integer pageSize,String name){
        PageHelper.startPage(pageNum,pageSize);
        List<GoodsInfo> goods = goodsInfoMapper.findGoodsByName(name,null);
        return PageInfo.of(goods);
    }

    public GoodsInfo addGoods(GoodsInfo goodsInfo){
        convertFileListToFields(goodsInfo);
        goodsInfoMapper.insertSelective(goodsInfo);
        return goodsInfo;
    }

    public void updateGoods(GoodsInfo goodsInfo){
        convertFileListToFields(goodsInfo);
       goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
    }

    private void convertFileListToFields(GoodsInfo goodsInfo){
        List<Long> fileList = goodsInfo.getFileList();
        if (!CollectionUtil.isEmpty(fileList)){
            goodsInfo.setFields(fileList.toString());
        }
    }

    public void delete(Integer id) {
        goodsInfoMapper.deleteByPrimaryKey(id);
    }

    public GoodsInfo findGoodsById(Integer id){
        List<GoodsInfo> goods = goodsInfoMapper.findGoodsByName(null, id);
        if (goods == null || goods.size() == 0){
            return null;
        }
        return goods.get(0);
    }
}
