package com.houduan.controller;

import com.houduan.entity.User;
import com.houduan.service.IUserService;
import com.houduan.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    UserServiceImpl service = new UserServiceImpl();

    @Test
    void register() {
        User user = new User();
        user.setAge(10);
        user.setPassword("123");
        user.setEmail("qwe");
        user.setIsAvailable(1);
        user.setMobile("123");
        user.setUserName("wq");
        user.setPicImg("awda");
        user.setSex(1);
        user.setShowName("sad");
        service.register(user);
    }
}