package com.stockpredict.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stockpredict.system.dao.entity.User;
import com.stockpredict.system.dao.mapper.UserMapper;
import com.stockpredict.system.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wangzhewen
 * @since 2022-04-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
