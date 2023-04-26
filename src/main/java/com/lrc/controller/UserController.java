package com.lrc.controller;

import com.github.pagehelper.PageInfo;
import com.lrc.common.Result;
import com.lrc.entity.UserInfo;
import com.lrc.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userInfo")
public class UserController {
    @Resource
    private AdminService adminService;

    @GetMapping("/page/{name}")
    public Result<PageInfo<UserInfo>> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @PathVariable String name){
        return Result.success(adminService.findPage(pageNum,pageSize,name));
    }

    @PostMapping
    public Result<UserInfo> save(@RequestBody UserInfo userInfo){
        adminService.addUser(userInfo);
        return Result.success(userInfo);
    }

    @PutMapping
    public Result<?> update(@RequestBody UserInfo userInfo){
        adminService.updateUser(userInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id){
        adminService.delete(id);
        return Result.success();
    }
}
