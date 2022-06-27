package com.yutian.passnow.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author yutian
 * @date 2022/6/20
 * @time 17:30
 */
@Data
public class GenerateCustomKeyDto {
    @NotBlank
    private String customKey;
}
