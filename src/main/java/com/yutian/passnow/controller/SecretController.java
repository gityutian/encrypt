package com.yutian.passnow.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yutian.passnow.domain.SecretCustodyService;
import com.yutian.passnow.dto.DecryptCustodyDto;
import com.yutian.passnow.dto.PageCustodyDto;
import com.yutian.passnow.dto.SecretCustodyDto;
import com.yutian.passnow.entity.SecretCustody;
import com.yutian.passnow.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author yutian
 * @date 2022/6/20
 * @time 16:27
 */
@RestController
@RequestMapping("/secret/")
public class SecretController {

    @Autowired
    private SecretCustodyService secretCustodyService;

    @PostMapping("add")
    public String generateSecret(@RequestBody SecretCustodyDto secretCustodyDto){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(session.getId());
        if(Objects.isNull(userVo)){
            return "failed";
        }
        secretCustodyService.encryptCustody(secretCustodyDto, userVo.getId());
        return "success";
    }

    @PostMapping("decrypt")
    public String decryptPassword(@RequestBody DecryptCustodyDto decryptCustodyDto){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(session.getId());
        if(Objects.isNull(userVo)){
            return "failed";
        }
        return secretCustodyService.decryptCustody(decryptCustodyDto.getPassword(), userVo.getId());
    }

    @GetMapping("page")
    public Page<SecretCustody> page(@RequestBody PageCustodyDto pageCustodyDto){
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getSession();
        UserVo userVo = (UserVo) session.getAttribute(session.getId());
        if(Objects.isNull(userVo)){
            return new Page<>();
        }
        return secretCustodyService.page(pageCustodyDto.getPageNo(), pageCustodyDto.getPageSize(), userVo.getId());
    }


}
