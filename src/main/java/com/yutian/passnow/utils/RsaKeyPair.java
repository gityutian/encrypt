package com.yutian.passnow.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yutian
 * @date 2022/6/20
 * @time 16:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RsaKeyPair {
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;
}
