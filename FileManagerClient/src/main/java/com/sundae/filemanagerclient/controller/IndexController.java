package com.sundae.filemanagerclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author daijiyuan
 * @Email 948820549@qq.com
 * @Date 2019/11/18 下午4:58
 * @Description
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
