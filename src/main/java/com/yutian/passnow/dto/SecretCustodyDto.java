package com.yutian.passnow.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author yutian
 * @date 2022/6/20
 * @time 17:30
 */
@Data
public class SecretCustodyDto {
    /**
     * 是否随机密码
     */
    @NotNull
    private Integer randomPassword;
    /**
     * 随机密码长度
     */
    private Integer length;
    @NotBlank
    private String name;
    @NotBlank
    private String url;
    @NotBlank
    private String account;
    @NotBlank
    private String password;
    private String remark;
}
