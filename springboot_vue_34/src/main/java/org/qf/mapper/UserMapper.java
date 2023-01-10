package org.qf.mapper;


import org.qf.entity.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户接口
 */
@Component
public interface UserMapper extends Mapper<User> {
}
