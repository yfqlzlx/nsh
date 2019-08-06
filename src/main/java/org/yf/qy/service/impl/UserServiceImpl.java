package org.yf.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.yf.qy.entity.User;
import org.yf.qy.mapper.UserMapper;
import org.yf.qy.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yf.qy.vo.Response;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private  UserMapper userMapper;
    @Override
    public User login(User user) {
        if(user == null || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            return null;
        }
        User dbuser = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if(dbuser == null || !user.getPassword().equals(dbuser.getPassword())){
            return null;
        }
        return dbuser;
    }

    @Override
    public boolean registry(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public User findByName(User user) {
        if(user == null || StringUtils.isEmpty(user.getUsername())){
            return null;
        }
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
    }

    @Override
    public Response getAllUser(int pageNo, int pageSize) {
        IPage page = new Page(pageNo,pageSize);
        IPage dbData = userMapper.selectPage(page, new QueryWrapper<User>().orderByDesc("createtime"));
        return new Response(200,dbData.getRecords()).setTotalSize(dbData.getTotal());
    }
}
