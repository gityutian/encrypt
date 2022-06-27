package com.yutian.passnow.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author yutian
 * @date 2022/6/20
 * @time 17:30
 */
@Data
public class UserVo {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String createBy;
    private Date createTime;
    private String modifyBy;
    private Date modifyTime;
}
