package com.example.stusys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.stusys.mapper.CourseMapper;
import com.example.stusys.mapper.StudentCourseMapper;
import com.example.stusys.mapper.TeacherCourseMapper;
import com.example.stusys.pojo.Course;
import com.example.stusys.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseManageService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;


    /**
     * 获取课程信息
     */
    public List<Course> getAllCourseInformation(){

        return courseMapper.selectList(null);
    }

    /**
     * 修改课程信息
     */
    public int editCourseInformation(Course course){

        studentCourseMapper.updateStudent_Course_ByCourseId(course.getId(), course.getCourseName(), course.getCourseCredit());

        return courseMapper.updateById(course);
    }

    /**
     *根据id 获取课程
     */
    public Course getCourseById(int id){
        return courseMapper.selectById(id);
    }

    /**
     *根据courseName获取课程
     */
    public Course getCourseByCourseName(String courseName){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("course_name",courseName);

        return courseMapper.selectOne(wrapper);
    }

    /**
     * 搜索课程信息
     */
    public List<Course> searchCourseInformation(String courseName){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        if (courseName != null) wrapper.like("course_name",courseName);

        return courseMapper.selectList(wrapper);
    }

    /**
     * 删除课程信息
     */
    public int deleteCourseInformation(int courseId){
        teacherCourseMapper.deleteTeacher_Course_ByCourseId(courseId);

        studentCourseMapper.deleteStudent_Course_ByCourseId(courseId);

        return courseMapper.deleteById(courseId);
    }

    /**
     * 添加课程
     */
    public int addCourse(Course course){
        return courseMapper.insert(course);
    }

}
