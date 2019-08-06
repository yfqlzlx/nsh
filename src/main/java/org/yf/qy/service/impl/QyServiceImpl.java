package org.yf.qy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.yf.qy.entity.Qy;
import org.yf.qy.entity.UpdateLog;
import org.yf.qy.mapper.QyMapper;
import org.yf.qy.mapper.UpdateLogMapper;
import org.yf.qy.service.QyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
@Service("updateServiceImpl")
public class QyServiceImpl extends ServiceImpl<QyMapper, Qy> implements QyService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private QyMapper qyMapper;
    @Autowired
    private UpdateLogMapper updateLogMapper;

    private static final String URL = "https://nishuihan.net/Scripts/QiYu?v=YkUGQu2K1h24By0nkb0Eor7-pBWouPxHmi0-LDyVSx41";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update() throws  Exception{
        List<Qy> qies = convertToQy(fetch());
        UpdateLog log = new UpdateLog();
        log.setCreatetime(LocalDateTime.now());
        log.setState(true);
        Qy dbQy;
        int update = 0;
        int insert = 0;
        try{
            for (Qy item : qies) {
                dbQy = qyMapper.selectOne(new QueryWrapper<Qy>().eq("data_id",item.getDataId()));
                if(dbQy != null){
                    // update
                    item.setId(dbQy.getId());
                    qyMapper.updateById(dbQy);
                    update++;
                }else{
                    // insert
                    qyMapper.insert(item);
                    insert++;
                }
            }
        }catch (Exception e){
            log.setReason(e.getMessage());
            log.setState(false);
            throw new Exception("更新失败");
        }
        // 保存到更新表
        log.setAdd(insert);
        log.setModifyed(update);
        log.setVersion(updateLogMapper.getMaxVersion()+1);
        updateLogMapper.insert(log);
    }

    /**
     * 获取源json数据，是一个properties形式，去除xxx=即可
     */
    private String fetch()throws  Exception{
        String forObject = restTemplate.getForObject(URL, String.class);
        if(StringUtils.isEmpty(forObject)){
            throw new Exception("未获取到源数据");
        }
        int index = forObject.indexOf("=");
        if(index <=0){
            throw new Exception("获取源数据格式错误");
        }
        return forObject.substring(index+1);
    }

    /**
     * 将获取到的json串解析成奇遇对象
     */
    private List<Qy> convertToQy(String src){
        List<Qy> ret = new ArrayList<>();
        Qy qy;
        JSONArray arr;
        JSONArray array = JSONArray.parseArray(src);
        for (Object o : array) {
            arr = JSONArray.parseArray(o.toString());
            qy = new Qy();
            // 网易的id
            qy.setDataId(Integer.parseInt(arr.get(0).toString()));
            // 奇遇名
            qy.setName(arr.get(1).toString());
            // 奇遇等级
            qy.setLevel(arr.get(2).toString());
            // 地点 + 人物
            qy.setLocation(arr.get(3).toString() + "\r\n" + arr.get(4).toString());
            // 附标题
            qy.setExt1(arr.get(5).toString());
            // 触发条件
            qy.setCondition(arr.get(6).toString());
            // 奖励
            qy.setReward(arr.get(7).toString());
            ret.add(qy);
        }
        return ret;
    }
}
