package com.xugu.controller;

import com.xugu.config.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private Target target;
    @RequestMapping("/hello")
    @ResponseBody
    public String res(){
        System.out.println(target.toString());
        return target.toString();
    }
}
