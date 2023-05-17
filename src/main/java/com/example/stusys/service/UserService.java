package com.example.stusys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.stusys.mapper.UserMapper;
import com.example.stusys.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据学号user_id 类型type获取用户
     */
    public User getUserByUserIdAndType(String userId, int type){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("type",type);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 根据学号user_id 获取用户
     */
    public User getUserByUserId(String userId){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 根据id 获取用户
     */
    public User getUserById(int id){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 注册账号
     */
    public int insertUser(User user){
        return userMapper.insert(user);
    }


    /**
     * 修改密码
     */
    public int updatePassword(User user){
        return userMapper.updateById(user);
    }

    /**
     * 获取密钥
     */
    public String getSecretKey(int id){
        return userMapper.getSecretKey(id);
    }

    /**
     * 根据studentId查询是否存在该学生
     */
    public User studentIsExist(String studentId){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",studentId);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 根据studentId和name查询是否存在该学生
     */
    public User getStudentByIdAndName(String userId, String userName){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("user_name",userName);
        wrapper.eq("type",1);

        return userMapper.selectOne(wrapper);
    }

    /**
     * 根据teacherId和name查询是否存在该教师
     */
    public User getTeacherByIdAndName(String userId, String userName){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("user_name",userName);
        wrapper.eq("type",2);

        return userMapper.selectOne(wrapper);
    }

    public List<User> getAllStudent(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("type",1);
        return userMapper.selectList(wrapper);
    }
}
