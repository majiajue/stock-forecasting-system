package stock.predict.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import stock.predict.dao.entity.User;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wangzhewen
 * @since 2022-04-07
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
