package com.service.impl;

import com.mapper.UserMapper;
import com.pojo.User;
import com.service.UserService;
import com.util.TokenUtil;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Resource(name = "loggedUsers")
    private Map userMap;
    @Override
    public String queryUserByUserNameAndPassword(String userName,String password) {
        User user = userMapper.queryUserByUserName(userName);
        //如果密码相等则返回User的字符串
        if(user!= null&&user.getPassword().equals(password))
        {
            return TokenUtil.sign(user.getUserName(),user.getPassword());
        }
        else{
            return "{message:账号或密码错误}";
        }

    }
}
