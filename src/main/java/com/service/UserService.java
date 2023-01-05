package com.service;

import com.pojo.User;

import java.util.List;

public interface UserService {
    String queryUserByUserNameAndPassword(String userName,String password);
}
