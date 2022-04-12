package stock.predict.controller;


import cn.hutool.core.util.ObjectUtil;
import org.springframework.web.bind.annotation.*;
import stock.predict.common.domain.ResResult;
import stock.predict.dao.entity.User;
import stock.predict.service.IUserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
    /* @pathparam 请求接口时 将变量URI路径片段映射到方法调用中 ; @pathVariable 请求接口时 将占位符参数（Restful风格）映射到方法调用中*/
    public ResResult login(@PathParam("username") String username, @PathParam("password") String password) {
        User user = iUserService.login(username, password);
        if (ObjectUtil.isNotEmpty(user)) {
            user.setPassword(null);
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
        user.setScore((float) -1);
        if (ObjectUtil.isEmpty(iUserService.getByName(user.getName()))) {
            if (iUserService.save(user)) {
                return ResResult.success("新增成功");
            } else {
                return ResResult.error("新增失败！");
            }
        } else {
            return ResResult.error("用户已存在!");
        }
    }

    /**
     * 用户信息更新
     **/
    @PostMapping("/update")
    public ResResult update(@RequestBody User user) {
        if (iUserService.updateById(user)) {
            return ResResult.success();
        } else {
            return ResResult.error("更新失败！");
        }
    }

    /**
     * 调查问卷分数上传
     **/
    @GetMapping("/questionnaire")
    public ResResult questionnaire(Double score, Long userId) {
        if (iUserService.updateScore(score, userId)) {
            return ResResult.success();
        } else {
            return ResResult.error("提交失败");
        }
    }

}
