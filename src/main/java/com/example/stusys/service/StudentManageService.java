package com.example.stusys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.stusys.mapper.StudentCourseMapper;
import com.example.stusys.mapper.StudentTeacherMapper;
import com.example.stusys.mapper.TeacherCourseMapper;
import com.example.stusys.mapper.UserMapper;
import com.example.stusys.pojo.StudentCourse;
import com.example.stusys.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentManageService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Autowired
    private StudentTeacherMapper studentTeacherMapper;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    /**
     * 获取学生信息
     */
    public List<User> getAllStudentInformation(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("type",1);

        return userMapper.selectList(wrapper);
    }

    /**
     * 搜索学生信息
     */
    public List<User> searchStudentInformation(String studentName,String studentId,String studentClass,String major){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("type",1);

        if (studentName != null) wrapper.like("user_name",studentName);
        if (studentId != null) wrapper.like("user_id",studentId);
        if (studentClass != null) wrapper.like("user_class",studentClass);
        if (major != null) wrapper.like("major",major);


        return userMapper.selectList(wrapper);
    }

    /**
     * 修改学生信息
     */
    public int editStudentInformation(User user,String oldStudentId){

        studentCourseMapper.updateStudent_Course(user.getUserName(),user.getUserId(),oldStudentId);

        if (user.getUserId() != oldStudentId){
            studentTeacherMapper.updateStudent_Teacher_ByOldStudentId(user.getUserId(),oldStudentId);
        }

        return userMapper.updateById(user);
    }

    /**
     * 删除学生信息
     */
    public int deleteStudentInformation(int id,String studentId){
        studentCourseMapper.deleteStudent_Course_ByStudentId(studentId);

        studentTeacherMapper.deleteStudent_Teacher_ByStudentId(studentId);

        return userMapper.deleteById(id);
    }

    /**
     * 添加学生
     */
    public int addStudent(User user){
        return userMapper.insert(user);
    }

    /**
     * 添加 老师 学生 课程成绩 信息
     */
    public int enter(String studentId, String teacherId, int courseId, StudentCourse studentCourse){

        QueryWrapper<StudentCourse> wrapper = new QueryWrapper<>();

        wrapper.eq("student_id",studentCourse.getStudentId());
        wrapper.eq("course_id",studentCourse.getCourseId());

        //学生成绩已经录入
        if (studentCourseMapper.selectOne(wrapper) != null){
            return 123;
        }
        //避免重复添加
        if (studentTeacherMapper.getByStudentIdAndTeacherId(studentId,teacherId) == 0){
            studentTeacherMapper.addStudentTeacher(studentId,teacherId);
        }

        if (teacherCourseMapper.getByTeacherIdAndCourseId(courseId,teacherId) == 0){
            teacherCourseMapper.addTeacherCourse(courseId,teacherId);
        }

        return studentCourseMapper.insert(studentCourse);
    }

    /**
     * 更新学分
     * @param studentId
     * @return
     */
    public void reloadCredit(String studentId){
        float credit = studentCourseMapper.getCredit(studentId);

        userMapper.updateCredit(credit,studentId);
    }

}
