package com.example.stusys.service;

import com.example.stusys.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserById(){
        User user = userService.getUserByUserIdAndType("01001",1);
        System.out.println(user);
    }
}
