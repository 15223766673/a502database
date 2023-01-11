package com.controller;

import com.service.UserService;
import com.util.Resp;
import com.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public Resp getUserInfo(@RequestBody Map map, @RequestHeader Map map2)
    {
        System.out.println(map2);
        String  userName = TokenUtil.getUserName((String) map2.get("token"));
        //初始化返回对象
        Resp<Object> res = new Resp<>();
        Map<String,Object> resMap = new HashMap<>();
        if(userName!=null)
        {
            //返回用户名
            resMap.put("userName",userName);
            res.setStatus("200");
            res.setMessage("成功获取用户名");
            res.setRtnMap(resMap);
        }
        else{
            //返回错误
            res.setStatus("300");
            res.setMessage("缓存无此用户，请重新登录");

        }
        return res;
    }
}
