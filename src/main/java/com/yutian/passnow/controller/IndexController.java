package com.yutian.passnow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yutian
 * @date 2022/6/20
 * @time 16:27
 */
@RestController
public class IndexController {
    @GetMapping("index")
    public String index(){
        return "hello world!";
    }
}
