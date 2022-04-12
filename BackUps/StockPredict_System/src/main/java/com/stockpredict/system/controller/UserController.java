package com.stockpredict.system.controller;


import com.stockpredict.system.dao.entity.User;
import com.stockpredict.system.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wangzhewen
 * @since 2022-04-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;

    @ApiOperation(value = "用户登录")
    @GetMapping("/login")
    public User login(String name, String password) {
        return new User();
    }

}
