package org.yf.qy.mapper;

import org.yf.qy.entity.UpdateLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
public interface UpdateLogMapper extends BaseMapper<UpdateLog> {

    Integer getMaxVersion();

    UpdateLog info();
}
