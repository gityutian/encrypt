package com.yutian.passnow.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.yutian.passnow.dto.GenerateCustomKeyDto;
import com.yutian.passnow.dto.LoginDto;
import com.yutian.passnow.dto.UserDto;
import com.yutian.passnow.entity.User;
import com.yutian.passnow.entity.UserSecret;
import com.yutian.passnow.mapper.UserMapper;
import com.yutian.passnow.mapper.UserSecretMapper;
import com.yutian.passnow.utils.RSAUtils;
import com.yutian.passnow.utils.RsaKeyPair;
import com.yutian.passnow.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;

/**
 * @author yutian
 * @date 2022/6/20
 * @time 16:27
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSecretMapper userSecretMapper;

    @PostMapping("add")
    public String add(@RequestBody UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreateTime(new Date());
        userMapper.insert(user);
        return user.toString();
    }

    @PostMapping("login")
    public String login(@RequestBody LoginDto loginDto){
        String name = loginDto.getName();
        String phone = loginDto.getPhone();
        if(StringUtils.isBlank(name) && StringUtils.isBlank(phone)){
            return "failed";
        }
        User user = new LambdaQueryChainWrapper<>(userMapper).eq(User::getName, name).or().eq(User::getPhone, phone).eq(User::getPassword, loginDto.getPassword()).one();
        if(Objects.isNull(user)){
            return "failed";
        }else{
            UserVo userVo = new UserVo();
            userVo.setEmail(user.getEmail());
            userVo.setId(user.getId());
            userVo.setName(user.getName());
            userVo.setPhone(user.getPhone());
            userVo.setCreateTime(user.getCreateTime());
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest().getSession();
            session.setAttribute(session.getId(), userVo);
            return "success";
        }
    }

    @PostMapping("generateSecret")
    public String generateSecret(@RequestBody GenerateCustomKeyDto generateCustomKeyDto){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(session.getId());
        if(Objects.isNull(userVo)){
            return "failed";
        }
        try {
            UserSecret queryUserSecret = new UserSecret();
            queryUserSecret.setUserId(userVo.getId());
            UserSecret userSecret = userSecretMapper.selectOne(new QueryWrapper<>(queryUserSecret));
            if(Objects.isNull(userSecret)){
                userSecret = new UserSecret();
                RsaKeyPair rsaKeyPair = RSAUtils.generateKeyPair();
                userSecret.setUserId(userVo.getId());
                userSecret.setCreateTime(new Date());
                userSecret.setPublicKey(rsaKeyPair.getPublicKey());
                userSecret.setPrivateKey(rsaKeyPair.getPrivateKey());
                userSecret.setCustomKey(generateCustomKeyDto.getCustomKey());
                userSecretMapper.insert(userSecret);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "success";
    }


}
