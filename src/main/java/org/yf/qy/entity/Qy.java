package org.yf.qy.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yf
 * @since 2019-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Qy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private long id;
    /**
     * 网易奇遇的id
     */
    private Integer dataId;

    /**
     * 奇遇名称
     */
    private String name;

    /**
     * 奇遇等级
     */
    private String level;

    /**
     * 奇遇地点
     */
    private String location;

    /**
     * 触发条件
     */
    private String conditions;

    /**
     * 完成奖励
     */
    private String reward;

    /**
     * 副标题
     */
    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;


}
