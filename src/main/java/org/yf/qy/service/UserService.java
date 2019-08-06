package org.yf.qy.service;

import org.yf.qy.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.yf.qy.vo.Response;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
public interface UserService extends IService<User> {

    User login(User user);

    boolean registry(User user);


    User findByName(User user);

    Response getAllUser(int pageNo, int pageSize);
}
