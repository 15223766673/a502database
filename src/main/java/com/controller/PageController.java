package com.controller;


import com.service.UserService;
import com.util.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String getLoginPage()
    {
        return "login";
    }
    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    @ResponseBody
    public Resp loginUser(@RequestBody Map<String,String> map, HttpServletRequest request)
    {
        String tokenString = userService.queryUserByUserNameAndPassword(map.get("username"),map.get("password"));
        Resp<Object> resp = new Resp<>();
        Map<String,Object> resultMap = new HashMap<>();
        if(tokenString.equals("{message:账号或密码错误}"))
        {
            resp.setMessage("账号或密码错误");
            resp.setStatus("201");
        }
        else{
            resp.setMessage("成功");
            resp.setStatus("200");
            resultMap.put("token",tokenString);
            resp.setRtnMap(resultMap);
        }
        return resp;
    }

}
