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
public class User {
    @TableId
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String createBy;
    private Date createTime;
    private String modifyBy;
    private Date modifyTime;
}
