package org.yf.qy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.jndi.ldap.sasl.LdapSasl;
import org.springframework.beans.factory.annotation.Autowired;
import org.yf.qy.entity.UpdateLog;
import org.yf.qy.entity.UserQy;
import org.yf.qy.mapper.QyMapper;
import org.yf.qy.mapper.UpdateLogMapper;
import org.yf.qy.mapper.UserQyMapper;
import org.yf.qy.service.UserQyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yf.qy.vo.QyVo;
import org.yf.qy.vo.Response;

import java.util.HashMap;
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
    @Autowired
    private UpdateLogMapper updateLogMapper;

    @Override
    public void mark(Long id, Long userId) {
        UserQy userQy = userQyMapper.selectOne(new QueryWrapper<UserQy>().eq("user_id", userId).eq("qy_id", id));
        if(userQy == null){
            userQy = new UserQy();
            userQy.setExt1(MARKED).setUserId(userId).setQyId(id);
            userQyMapper.insert(userQy);
        }
    }

    @Override
    public void unMark(Long id, Long userId) {
        userQyMapper.delete(new QueryWrapper<UserQy>().eq("user_id", userId).eq("qy_id", id));
    }

    @Override
    public void markBatch(List<Long> ids, Long userId) {
        ids.forEach(id->mark(id,userId));
    }

    @Override
    public void unMarkBatch(List<Long> ids, long userId) {
        ids.forEach(id->unMark(id,userId));
    }


    @Override
    public Response query(Map<String, Object> param) {
        Map<String, Object> convert = (Map<String, Object>) JSONObject.parse(JSONObject.toJSONString(param));
        Object extraObj = convert.get("extra");
        long userId ;
        if(extraObj != null){
            JSONObject jsonObject = JSONObject.parseObject(extraObj.toString());
            userId = Long.parseLong(jsonObject.getString("userId"));
            param = new HashMap<>(2);
            param.put("level",jsonObject.getString("level"));
            param.put("mark",jsonObject.getString("mark"));
            param.put("page",convert.get("page"));
            param.put("limit",convert.get("limit"));
        }else{
            userId = Long.parseLong(param.get("userId").toString());
        }

        Object pageNo = param.get("page");
        Object pageSize = param.get("limit");
        IPage page = new Page(pageNo==null?1:Integer.valueOf(pageNo.toString()),pageSize==null?10:Integer.valueOf(pageSize.toString()));
        page = userQyMapper.queryAll(page,userId,param);
        return new Response(200,page.getRecords()).setTotalSize(page.getTotal());
    }

    @Override
    public Object info() {
        return updateLogMapper.info();
    }


}
