package stock.predict.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.web.bind.annotation.*;

import stock.predict.common.domain.ResResult;
import stock.predict.dao.entity.User;
import stock.predict.service.IUserService;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    /**
     * 用户登录
     **/
    @GetMapping("/login")
    public ResResult login(String name, String password) {
        User user = iUserService.login(name, password);
        if (ObjectUtil.isNotEmpty(user)) {
            return ResResult.success(user);
        } else {
            return ResResult.error("登陆失败");
        }
    }

    /**
     * 用户注册
     **/
    @PostMapping("/register")
    public ResResult register(@Valid @RequestBody User user) {
        if (ObjectUtil.isNotEmpty(iUserService.getById(user.getId())))
        {
            if(iUserService.save(user)) {
                return ResResult.success("新增成功");
            } else{
                return ResResult.error("新增失败！");
            }
        } else {
            return ResResult.error("用户已存在!");
        }
    }

    /**
     * 用户信息更新
     **/

    /**
     * 调查问卷
     **/

}
