package org.yf.qy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sun.jndi.ldap.sasl.LdapSasl;
import org.springframework.beans.factory.annotation.Autowired;
import org.yf.qy.entity.UserQy;
import org.yf.qy.mapper.UserQyMapper;
import org.yf.qy.service.UserQyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yf.qy.vo.QyVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
@Service
public class UserQyServiceImpl extends ServiceImpl<UserQyMapper, UserQy> implements UserQyService {
    private static final String MARKED = "1";
    private static final String UN_MARK = "0";


    @Autowired
    private UserQyMapper userQyMapper;

    @Override
    public void mark(long id, long userId) {
        UserQy userQy = userQyMapper.selectOne(new QueryWrapper<UserQy>().eq("user_id", userId).eq("qy_id", id));
        if(userQy == null){
            userQy = new UserQy();
            userQy.setExt1(MARKED).setUserId(userId).setQyId(id);
            userQyMapper.insert(userQy);
        }else{
            userQy.setExt1(MARKED);
            userQyMapper.updateById(userQy);
        }
    }

    @Override
    public void unMark(long id, long userId) {
        UserQy userQy = userQyMapper.selectOne(new QueryWrapper<UserQy>().eq("user_id", userId).eq("qy_id", id));
        if(userQy == null){
            userQy = new UserQy();
            userQy.setExt1(UN_MARK).setUserId(userId).setQyId(id);
            userQyMapper.insert(userQy);
        }else{
            userQy.setExt1(UN_MARK);
            userQyMapper.updateById(userQy);
        }
    }

    @Override
    public void markBatch(List<Long> ids, long userId) {
        ids.forEach(id->mark(id,userId));
    }

    @Override
    public void unMarkBatch(List<Long> ids, long userId) {
        ids.forEach(id->unMark(id,userId));
    }

    @Override
    public List<QyVo> getAll(long userId, IPage page) {
        return userQyMapper.getAllQyWithState(page,userId);
    }

    @Override
    public List<QyVo> query(long userId, Map<String, Object> param, IPage page) {
        return userQyMapper.queryAll(page,userId,param);
    }


}
