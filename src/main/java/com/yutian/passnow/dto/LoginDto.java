package com.yutian.passnow.dto;

import lombok.Data;

/**
 * @author yutian
 * @date 2022/6/20
 * @time 17:30
 */
@Data
public class LoginDto {
    private String name;
    private String phone;
    private String password;
}
