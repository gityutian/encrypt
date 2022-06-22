package com.yutian.passnow.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author FSX
 * @date 2022/6/20
 * @time 17:30
 */
@Data
public class UserDto {
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    private String email;
    @NotBlank
    private String password;
}
