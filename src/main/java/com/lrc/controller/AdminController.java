package com.lrc.controller;

import cn.hutool.core.util.StrUtil;
import com.lrc.common.Common;
import com.lrc.common.Result;
import com.lrc.common.ResultCode;
import com.lrc.entity.UserInfo;
import com.lrc.exception.CustomException;
import com.lrc.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class AdminController {
    @Resource
    private AdminService adminService;
    @PostMapping("/login")
    public Result<UserInfo> login(@RequestBody UserInfo userInfo, HttpServletRequest request){
        if (StrUtil.isBlank(userInfo.getName())|| StrUtil.isBlank(userInfo.getPassword())){
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        UserInfo login = adminService.login(userInfo.getName(), userInfo.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute(Common.USER_INFO,login);
        session.setMaxInactiveInterval(24*60);
        return Result.success(login);
    }

    @PostMapping("/resetPassword")
    public Result<UserInfo> ResetPassword(@RequestBody UserInfo userInfo){

        return Result.success(adminService.resetPassword(userInfo.getName()));
    }

    @GetMapping("/logout")
    public Result<?> logout(HttpServletRequest request){
        request.getSession().setAttribute(Common.USER_INFO,null);
        return Result.success();
    }
}
