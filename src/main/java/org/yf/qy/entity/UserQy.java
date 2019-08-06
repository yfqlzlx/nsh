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
public class UserQy implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private long id;
    private Long userId;

    private Long qyId;

    /**
     * 当作标记状态使用
     */
    private String ext1;

    private String ext2;

    private String ext3;

    private String ext4;


}
