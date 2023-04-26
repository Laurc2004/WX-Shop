package com.lrc.controller;

import com.github.pagehelper.PageInfo;
import com.lrc.common.Result;
import com.lrc.entity.GoodsInfo;
import com.lrc.service.GoodsInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goodsInfo")
public class GoodsInfoController {
    @Resource
    private GoodsInfoService goodsInfoService;

    @GetMapping("/page/{name}")
    public Result<PageInfo<GoodsInfo>> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @PathVariable String name){
        return Result.success(goodsInfoService.findPage(pageNum,pageSize,name));
    }

    @PostMapping
    public Result<GoodsInfo> save(@RequestBody GoodsInfo goodsInfo){
        goodsInfoService.addGoods(goodsInfo);
        return Result.success(goodsInfo);
    }

    @PutMapping
    public Result update(@RequestBody GoodsInfo goodsInfo){
        goodsInfoService.updateGoods(goodsInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        goodsInfoService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<GoodsInfo> findGoodsById(@PathVariable Integer id){
        GoodsInfo goodsById = goodsInfoService.findGoodsById(id);
        return Result.success(goodsById);
    }
}
