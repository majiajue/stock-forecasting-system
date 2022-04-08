package stock.predict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import stock.predict.dao.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wangzhewen
 * @since 2022-04-07
 */
public interface IUserService extends IService<User> {

    User login(String name, String password);
}
