package com.yutian.passnow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author FSX
 * @date 2022/6/20
 * @time 17:30
 */
@Data
public class SecretCustody {
    @TableId
    private Long id;
    private String userId;
    private String name;
    private String url;
    private String account;
    private String password;
    private String remark;
    private String createBy;
    private Date createTime;
    private String modifyBy;
    private Date modifyTime;
}
