package org.yf.qy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.yf.qy.entity.Qy;
import org.yf.qy.entity.UserQy;
import com.baomidou.mybatisplus.extension.service.IService;
import org.yf.qy.vo.QyVo;
import org.yf.qy.vo.Response;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
public interface UserQyService extends IService<UserQy> {

    void mark(long id, long userId);

    void unMark(long id,long userId);

    void markBatch(List<Long> ids,long userId);

    void unMarkBatch(List<Long> ids,long userId);

    List<QyVo> getAll(long userId, IPage page);

    List<QyVo> query(long userId, Map<String,Object> param,IPage page);
}
