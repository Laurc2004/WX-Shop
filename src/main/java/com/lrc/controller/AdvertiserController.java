package com.lrc.controller;

import com.github.pagehelper.PageInfo;
import com.lrc.common.Result;
import com.lrc.entity.AdvertiserInfo;
import com.lrc.entity.UserInfo;
import com.lrc.service.AdminService;
import com.lrc.service.AdvertiserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/advertiserInfo")
public class AdvertiserController {
    @Resource
    private AdvertiserService advertiserService;

    @GetMapping("page/{name}")
    public Result<PageInfo<AdvertiserInfo>> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @PathVariable String name){
        return Result.success(advertiserService.findPage(pageNum,pageSize,name));
    }

    @PostMapping
    public Result<AdvertiserInfo> save(@RequestBody AdvertiserInfo advertiserInfo){
        advertiserService.addAdvertiser(advertiserInfo);
        return Result.success(advertiserInfo);
    }

    @PutMapping
    public Result update(@RequestBody AdvertiserInfo advertiserInfo){
        advertiserService.updateAdvertiser(advertiserInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        advertiserService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<AdvertiserInfo> findAdvertiserById(@PathVariable Integer id){
        AdvertiserInfo advertiserById = advertiserService.findAdvertiserById(id);
        return Result.success(advertiserById);
    }
}
