package com.yutian.passnow.domain;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yutian.passnow.dto.SecretCustodyDto;
import com.yutian.passnow.entity.SecretCustody;
import com.yutian.passnow.entity.UserSecret;
import com.yutian.passnow.mapper.SecretCustodyMapper;
import com.yutian.passnow.mapper.UserSecretMapper;
import com.yutian.passnow.utils.PasswordUtils;
import com.yutian.passnow.utils.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yutian
 * @date 2022/6/27
 * @time 17:13
 * */
@Service
public class SecretCustodyService {
    @Autowired
    private SecretCustodyMapper secretCustodyMapper;
    @Autowired
    private UserSecretMapper userSecretMapper;

    /**
     * 添加加密信息
     * @param secretCustodyDto
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public SecretCustody encryptCustody(SecretCustodyDto secretCustodyDto, Long userId){
        UserSecret userSecret = new LambdaQueryChainWrapper<>(userSecretMapper).eq(UserSecret::getUserId, userId).one();
        SecretCustody secretCustody = new SecretCustody();
        secretCustody.setUserId(userId);
        secretCustody.setName(secretCustodyDto.getName());
        secretCustody.setAccount(secretCustodyDto.getAccount());
        //用公钥加密
        try {
            if(secretCustodyDto.getRandomPassword().equals(1)){
                secretCustodyDto.setPassword(PasswordUtils.generatePassword(secretCustodyDto.getLength()));
            }
            String password = RSAUtils.encryptByPublicKey(userSecret.getPublicKey(), secretCustodyDto.getPassword());
            secretCustody.setPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        secretCustody.setUrl(secretCustodyDto.getUrl());
        secretCustody.setRemark(secretCustodyDto.getRemark());
        secretCustody.setCreateTime(new Date());
        secretCustodyMapper.insert(secretCustody);
        return secretCustody;
    }

    /**
     * 解密
     * @param password
     * @param userId
     * @return
     */
    public String decryptCustody(String password, Long userId){
        UserSecret userSecret = new LambdaQueryChainWrapper<>(userSecretMapper).eq(UserSecret::getUserId, userId).one();
        try {
            return RSAUtils.decryptByPrivateKey(userSecret.getPrivateKey(), password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";
    }

    /**
     * 分页查询
     * @param pageSize
     * @param pageNo
     * @param userId
     * @return
     */
    public Page<SecretCustody> page(Integer pageSize, Integer pageNo, Long userId){
        Page page = new Page(pageNo * pageSize, pageSize);
        SecretCustody querySecretCustody = new SecretCustody();
        querySecretCustody.setUserId(userId);
        return secretCustodyMapper.selectPage(page, new QueryWrapper<>(querySecretCustody));
    }

}
