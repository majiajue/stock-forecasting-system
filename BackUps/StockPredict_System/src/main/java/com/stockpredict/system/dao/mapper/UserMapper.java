package com.stockpredict.system.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stockpredict.system.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wangzhewen
 * @since 2022-04-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
