package org.yf.qy.service;

import org.yf.qy.entity.Qy;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
public interface QyService extends IService<Qy> {

    void update() throws Exception;
}
