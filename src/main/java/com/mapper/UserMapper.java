package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    //根据UserName查找用户
    User queryUserByUserName(@Param(value = "userName") String userName);
}
