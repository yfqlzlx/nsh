package org.yf.qy.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.yf.qy.entity.UserQy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.yf.qy.vo.QyVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
public interface UserQyMapper extends BaseMapper<UserQy> {


    IPage<QyVo> queryAll(IPage page, @Param("userId") long userId, @Param("param") Map<String, Object> param);
}
