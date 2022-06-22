package com.yutian.encrypt;

import com.yutian.passnow.utils.RSAUtils;
import com.yutian.passnow.utils.RsaKeyPair;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EncryptApplicationTests {

    @Test
    void contextLoads(){
        try {
            RsaKeyPair rsaKeyPair = RSAUtils.generateKeyPair();
            String content = RSAUtils.encryptByPublicKey(rsaKeyPair.getPublicKey(),"hello world!");
            System.out.println(content);
            System.out.println(RSAUtils.decryptByPrivateKey(rsaKeyPair.getPrivateKey(),content));
            String privateContent = RSAUtils.encryptByPrivateKey(rsaKeyPair.getPrivateKey(),"yutian");
            System.out.println(privateContent);
            System.out.println(RSAUtils.decryptByPublicKey(rsaKeyPair.getPublicKey(), privateContent));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
