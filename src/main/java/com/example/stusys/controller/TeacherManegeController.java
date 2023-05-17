package com.example.stusys.controller;

import com.example.stusys.pojo.User;
import com.example.stusys.service.StudentManageService;
import com.example.stusys.service.TeacherManageService;
import com.example.stusys.service.UserService;
import com.example.stusys.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherManegeController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherManageService teacherManageService;

    /**
     * 老师信息展示
     */
    @GetMapping("/{type}/teacherInformation")
    public R getAllTeacherInformation(@PathVariable("type") int type){
        if (type != 3){
            return R.fail().message("非管理员账号,无法查询");
        }

        List<User> teacherInfoList = teacherManageService.getAllTeacherInformation();

        return R.ok().data("teacherInfoList",teacherInfoList);
    }

    /**
     * 搜索教师信息
     */
    @PostMapping("/searchTeacherInformation")
    public R searchTeacherInformation(int type,String teacherName,String teacherId,String major){
        if (type != 3){
            return R.fail().message("非管理员账号,无法查询");
        }

        List<User> teachers = teacherManageService.searchTeacherInformation(teacherName, teacherId, major);

        return R.ok().data("searchTeacherInfo",teachers);
    }

    /**
     * 修改教师信息
     */
    @PostMapping("/updateTeacher")
    public R editTeacherInformation(int id,String teacherName,String teacherId,String major){

        User user = userService.getUserById(id);

        String oldTeacherId = user.getUserId();

        if (user == null){
            return R.error().message("未找到用户");
        }

        //工号不唯一
        if (!teacherId.equals(oldTeacherId)){
            if (userService.getUserByUserId(teacherId) != null){
                return R.fail().message("该工号已存在");
            }
        }

        user.setUserName(teacherName);
        user.setUserId(teacherId);
        user.setMajor(major);

        //修改user,student_course, student_teacher表
        int i = teacherManageService.editTeacherInformation(user,oldTeacherId);

        if (i == 1){
            return R.ok().message("修改成功");
        }else {
            return R.fail().message("服务器繁忙，修改失败");
        }
    }

    /**
     * 删除教师信息
     */
    @PostMapping("/deleteTeacher")
    public R deleteStudentInformation(int id,int type,String teacherId,String password){

        User user = userService.getUserByUserId(teacherId);

        if (user == null){
            return R.error().message("未找到用户");
        }
        if (type != 3){
            return R.fail().message("非管理员账号");
        }

        User admin = userService.getUserById(id);
        if (!admin.getPassword().equals(password)){
            return R.fail().message("密码错误");
        }

        // 删除三个表的学生信息,user,teacher_course,student_teacher
        int i = teacherManageService.deleteTeacherInformation(user.getId(), teacherId);

        if (i == 1){
            return R.ok().message("删除成功");
        }else {
            return R.fail().message("服务器繁忙，请稍后重试");
        }

    }

    /**
     * 录入教师信息
     */
    @PostMapping("/addTeacher")
    public R addStudent(String teacherName,String teacherId,String major){
        User user = userService.studentIsExist(teacherId);

        if (user != null) {
            return R.fail().message("该工号已存在");
        }

        User addUser = new User();
        addUser.setUserName(teacherName);
        addUser.setUserId(teacherId);
        addUser.setMajor(major);
        addUser.setType(2);

        int i = teacherManageService.addTeacher(addUser);

        if (i == 1){
            return R.ok().message("该教师信息录入成功");
        }else {
            return R.fail().message("服务器繁忙，请稍后重试");
        }
    }

}
