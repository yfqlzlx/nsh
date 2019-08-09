package org.yf.qy.vo;

import lombok.Data;

/**
 * @author yfqlzlx
 * @date 2019/8/6 16:30
 */
@Data
public class QyVo {
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
    private String subTitle;

    /**
     * 标记状态
     */
    private String state;
}
