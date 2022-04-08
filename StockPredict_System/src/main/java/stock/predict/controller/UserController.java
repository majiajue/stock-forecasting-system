package stock.predict.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import stock.predict.common.domain.ResResult;
import stock.predict.dao.entity.User;
import stock.predict.service.IUserService;

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

    @GetMapping("/login")
    public ResResult login(String name, String password) {
        User user = iUserService.login(name, password);
        if (ObjectUtil.isNotEmpty(user)) {
            return ResResult.success(user);
        } else {
            return ResResult.error("登陆失败");
        }
    }

}
