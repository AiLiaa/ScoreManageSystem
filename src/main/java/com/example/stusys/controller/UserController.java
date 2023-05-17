package com.example.stusys.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.stusys.pojo.User;
import com.example.stusys.service.UserService;
import com.example.stusys.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R login(String userId, String password, int type){
        User user = userService.getUserByUserId(userId);

        if(user == null || !userId.equals(user.getUserId())){
            return R.fail().message("登录失败，用户名或密码错误");
        }

        if (user.getType() != type){
            return R.fail().message("登录失败，用户名或密码错误");
        }

        if (password.equals(user.getPassword())){
            StpUtil.login(user.getUserId());//创建登录凭证
            return R.ok().data("user",user).data("satoken",StpUtil.getTokenValue()).message("登录成功");
        }else {
            return R.fail().message("登录失败，用户名或密码错误");
        }
    }

    @PostMapping("/register")
    public R register(String userId,String secretKey,String userName,String password){

        if (!secretKey.equals(userService.getSecretKey(1))){
            return R.fail().message("密钥错误");
        }

        if (userService.getUserByUserIdAndType(userId,3) != null){
            return R.fail().message("该账号已存在，请前往登录");
        }

        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        user.setType(3);

        if (userService.insertUser(user) == 1){
            return R.ok().message("注册成功");
        }else {
            return R.error().message("服务器繁忙");
        }
    }

    @PostMapping("/changePassword")
    public R changePassword(String userId,String password){
        User user = userService.getUserByUserId(userId);

        if(user == null || !userId.equals(user.getUserId())){
            log.error(String.format("userID=%d, user not fount", userId));
            throw new RuntimeException("user not fount");
        }
        user.setPassword(password);

        if (userService.updatePassword(user) == 1){
            return R.ok().message("修改成功");
        }else {
            return R.fail().message("修改失败");
        }
    }

}