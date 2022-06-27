package com.yutian.passnow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author yutian
 * @date 2022/6/20
 * @time 17:30
 */
@Data
public class UserSecret {
    @TableId
    private Long id;
    private Long userId;
    private String privateKey;
    private String publicKey;
    private String customKey;
    private String createBy;
    private Date createTime;
    private String modifyBy;
    private Date modifyTime;
}
