package stock.predict.service.impl;

import cn.hutool.core.util.ObjectUtil;
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

    @Override12
    public User login(String name, String password) {
        return this.lambdaQuery()
                .eq(User::getName, name)
                .eq(User::getPassword, password)
                .one();
    }

    @Override
    public boolean updateScore(Double score, Long userId) {
        return this.lambdaUpdate()
                .eq(User::getId, userId)
                .set(User::getScore, score)
                .update();
    }

    @Override
    public User getByName(String name) {
        return this.lambdaQuery()
                .eq(User::getName, name)
                .one();
    }

    @Override
    public boolean updateById(User user) {
        return this.lambdaUpdate()
                .eq(User::getId, user.getId())
                .set(ObjectUtil.isNotEmpty(user.getName()), User::getName, user.getName())
                .set(ObjectUtil.isNotEmpty(user.getPassword()), User::getPassword, user.getPassword())
                .set(ObjectUtil.isNotEmpty(user.getBirthday()), User::getBirthday, user.getBirthday())
                .update();
    }

}
