package com.lrc.controller;

import com.github.pagehelper.PageInfo;
import com.lrc.common.Result;
import com.lrc.entity.TypeInfo;
import com.lrc.service.TypeInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/typeInfo")
public class TypeInfoController {
    @Resource
    private TypeInfoService typeInfoService;

    @GetMapping("/page/{name}")
    public Result<PageInfo<TypeInfo>> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @PathVariable String name){
        return Result.success(typeInfoService.findPage(pageNum,pageSize,name));
    }

    @PostMapping
    public Result<TypeInfo> save(@RequestBody TypeInfo typeInfo){
        typeInfoService.addType(typeInfo);
        return Result.success(typeInfo);
    }

    @PutMapping
    public Result update(@RequestBody TypeInfo typeInfo){
        typeInfoService.updateType(typeInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        typeInfoService.delete(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<TypeInfo> findTypeById(@PathVariable Integer id){
        TypeInfo typeById = typeInfoService.findTypeById(id);
        return Result.success(typeById);
    }
}
