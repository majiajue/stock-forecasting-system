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

    /**
     * 用户登录
     * @Author WangZheWen
     * @Description TODO
     * @Date 2022/4/8 14:04
     * @Param [name, password]
     * @return stock.predict.dao.entity.User
     **/
    User login(String name, String password);

}
