package com.lrc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrc.entity.NxSystemFileInfo;
import com.lrc.mapper.NxSystemFileInfoMapper;
import com.lrc.mapper.NxSystemFileInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NxSystemFileInfoService {
    @Resource
    private NxSystemFileInfoMapper nxSystemFileInfoMapper;



    public NxSystemFileInfo addNxSystemFile(NxSystemFileInfo nxSystemFileInfo){
        nxSystemFileInfoMapper.insertSelective(nxSystemFileInfo);
        return nxSystemFileInfo;
    }

    public void updateNxSystemFile(NxSystemFileInfo nxSystemFileInfo){
       nxSystemFileInfoMapper.updateByPrimaryKeySelective(nxSystemFileInfo);
    }

    public void delete(Integer id) {
        nxSystemFileInfoMapper.deleteByPrimaryKey(id);
    }

    public NxSystemFileInfo findNxSystemFileById(Integer id){
        return nxSystemFileInfoMapper.selectByPrimaryKey(id);
    }
}
