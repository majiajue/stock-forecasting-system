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
     *
     * @return stock.predict.dao.entity.User
     * @Author WangZheWen
     * @Description TODO
     * @Date 2022/4/8 14:04
     * @Param [name, password]
     **/
    User login(String name, String password);

    /**
     * 提交分数
     *
     * @return boolean
     * @Author WangZheWen
     * @Description TODO
     * @Date 2022/4/12 11:46
     * @Param [score, userId]
     **/
    boolean updateScore(Double score, Long userId);

    /**
     * 查找用户 By Name
     *
     * @return java.lang.Object
     * @Author WangZheWen
     * @Description TODO
     * @Date 2022/4/12 16:36
     * @Param [id]
     **/
    User getByName(String name);
}
