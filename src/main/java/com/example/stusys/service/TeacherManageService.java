package com.example.stusys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.stusys.mapper.StudentTeacherMapper;
import com.example.stusys.mapper.TeacherCourseMapper;
import com.example.stusys.mapper.UserMapper;
import com.example.stusys.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherManageService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Autowired
    private StudentTeacherMapper studentTeacherMapper;

    /**
     * 获取教师信息
     */
    public List<User> getAllTeacherInformation(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("type",2);

        return userMapper.selectList(wrapper);
    }

    /**
     * 搜索老师信息
     */
    public List<User> searchTeacherInformation(String teacherName,String teacherId,String major){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("type",2);

        if (teacherName != null) wrapper.like("user_name",teacherName);
        if (teacherId != null) wrapper.like("user_id",teacherId);
        if (major != null) wrapper.like("major",major);

        return userMapper.selectList(wrapper);
    }

    /**
     * 修改教师信息
     */
    public int editTeacherInformation(User user,String oldTeacherId){

        teacherCourseMapper.updateTeacher_Course(user.getUserId(),oldTeacherId);

        if (user.getUserId() != oldTeacherId){
            studentTeacherMapper.updateStudent_Teacher_ByOldTeacherId(user.getUserId(),oldTeacherId);
        }

        return userMapper.updateById(user);
    }

    /**
     * 删除教师信息
     */
    public int deleteTeacherInformation(int id,String teacherId){
        teacherCourseMapper.deleteTeacher_Course_ByTeacherId(teacherId);

        studentTeacherMapper.deleteStudent_Teacher_ByTeacherId(teacherId);

        return userMapper.deleteById(id);
    }

    /**
     * 添加教师
     */
    public int addTeacher(User user){
        return userMapper.insert(user);
    }

}
