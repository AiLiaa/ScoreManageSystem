package com.example.stusys.controller;

import com.example.stusys.pojo.Course;
import com.example.stusys.pojo.User;
import com.example.stusys.service.CourseManageService;
import com.example.stusys.service.UserService;
import com.example.stusys.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseManageContorller {

    @Autowired
    private CourseManageService courseManageService;

    @Autowired
    private UserService userService;

    /**
     * 课程信息展示
     */
    @GetMapping("/{type}/courseInformation")
    public R getAllCourseInformation(@PathVariable("type") int type){
        if (type != 3){
            return R.fail().message("非管理员账号,无法查询");
        }

        List<Course> courseInfoList = courseManageService.getAllCourseInformation();

        return R.ok().data("courseInfoList",courseInfoList);
    }

    /**
     * 修改课程信息
     */
    @PostMapping("/updateCourse")
    public R editCourseInformation(int id,String courseName,float courseCredit){

        Course course = courseManageService.getCourseById(id);

        if (course.getCourseName().equals(courseName) && course.getCourseCredit() == courseCredit){
            return R.ok().message("课程信息未改变");
        }

        String oldCourseName = course.getCourseName();

        if (course == null){
            return R.error().message("未找到该课程");
        }

        //课程名不唯一
        if (!courseName.equals(oldCourseName)){
            if (courseManageService.getCourseByCourseName(courseName) != null){
                return R.fail().message("该课程已存在");
            }
        }

        course.setCourseCredit(courseCredit);
        course.setCourseName(courseName);

        //修改course,student_course表
        int i = courseManageService.editCourseInformation(course);

        if (i == 1){
            return R.ok().message("修改成功");
        }else {
            return R.fail().message("服务器繁忙，修改失败");
        }
    }

    /**
     * 搜索课程信息
     */
    @PostMapping("/searchCourseInformation")
    public R searchCourseInformation(int type,String courseName){
        if (type != 3){
            return R.fail().message("非管理员账号,无法查询");
        }

        List<Course> courses = courseManageService.searchCourseInformation(courseName);

        return R.ok().data("searchCourseInfo",courses);
    }

    /**
     * 删除课程信息
     */
    @PostMapping("/deleteCourse")
    public R deleteCourseInformation(int id,int type,int courseId,String password){

        Course course = courseManageService.getCourseById(courseId);

        if (course == null){
            return R.error().message("未找到该课程");
        }
        if (type != 3){
            return R.fail().message("非管理员账号");
        }

        User admin = userService.getUserById(id);
        if (!admin.getPassword().equals(password)){
            return R.fail().message("密码错误");
        }

        // 删除三个表的学生信息,course,teacher_course,student_course
        int i = courseManageService.deleteCourseInformation(courseId);

        if (i == 1){
            return R.ok().message("删除成功");
        }else {
            return R.fail().message("服务器繁忙，请稍后重试");
        }

    }

    /**
     * 录入课程信息
     */
    @PostMapping("/addCourse")
    public R addCourse(String courseName,float courseCredit){

        Course course = courseManageService.getCourseByCourseName(courseName);

        if (course != null) {
            return R.fail().message("该课程已存在");
        }

        Course addCourse = new Course();
        addCourse.setCourseName(courseName);
        addCourse.setCourseCredit(courseCredit);

        int i = courseManageService.addCourse(addCourse);

        if (i == 1){
            return R.ok().message("该课程信息录入成功");
        }else {
            return R.fail().message("服务器繁忙，请稍后重试");
        }
    }
}
