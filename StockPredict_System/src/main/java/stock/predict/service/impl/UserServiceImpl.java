package stock.predict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import stock.predict.dao.entity.User;
import stock.predict.dao.mapper.UserMapper;
import stock.predict.service.IUserService;

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

    @Override
    public User login(String name, String password) {
        return this.lambdaQuery()
                .eq(User::getName, name)
                .eq(User::getPassword, password)
                .one()
                .setPassword(null);
    }

}
