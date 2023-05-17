package com.example.stusys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.stusys.mapper.StudentCourseMapper;
import com.example.stusys.mapper.StudentTeacherMapper;
import com.example.stusys.mapper.TeacherCourseMapper;
import com.example.stusys.pojo.StudentCourse;
import com.example.stusys.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseMapper studentCourseMapper;

    @Autowired
    private StudentTeacherMapper studentTeacherMapper;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    /**
     * 根据id查找
     */
    public StudentCourse getById(int id){
        return studentCourseMapper.selectById(id);
    }

    /**
     * 学生获取该学生的所有课程成绩
     */
    public List<StudentCourse> getScoreByStudent(String studentId){
        QueryWrapper<StudentCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("student_id",studentId);
//        System.out.println(studentCourseMapper.selectList(wrapper).toString());
        return studentCourseMapper.selectList(wrapper);
    }

    /**
     * 学生自己根据学科名查询成绩
     */
    public List<StudentCourse> searchScoreByStudent(String studentId,String courseName){
        QueryWrapper<StudentCourse> wrapper = new QueryWrapper<>();

        wrapper.eq("student_id",studentId);
        if (courseName != null) wrapper.like("course_name",courseName);

        return studentCourseMapper.selectList(wrapper);

    }

    /**
     * 老师获取 所教学科的 所有学生的 该科目成绩
     */
    public List<StudentCourse> getScoreByTeacher(String teacherId){
        List<String> courseIds = studentCourseMapper.getCourseId(teacherId);
        List<String> studentIds = studentCourseMapper.getStudentId(teacherId);

        QueryWrapper<StudentCourse> wrapper = new QueryWrapper<>();
        wrapper.in("student_id",studentIds);
        wrapper.in("course_id",courseIds);
        return studentCourseMapper.selectList(wrapper);
    }

    /**
     * 老师查询所教学生的成绩
     */
    public List<StudentCourse> searchScoreByTeacher(String teacherId,String studentName,String studentId,String courseName){
        List<String> courseIds = studentCourseMapper.getCourseId(teacherId);
        List<String> studentIds = studentCourseMapper.getStudentId(teacherId);

        QueryWrapper<StudentCourse> wrapper = new QueryWrapper<>();

        wrapper.in("student_id",studentIds);
        wrapper.in("course_id",courseIds);

        if (studentName != null) wrapper.like("student_name",studentName);
        if (studentId != null) wrapper.like("student_id",studentId);
        if (courseName != null) wrapper.like("course_name",courseName);

        return studentCourseMapper.selectList(wrapper);
    }

    /**
     * 管理员获取所有学生成绩
     */
    public List<StudentCourse> getScoreByAdmin(){

        return studentCourseMapper.selectList(null);
    }

    /**
     * 管理员查询学生的成绩
     */
    public List<StudentCourse> searchScoreByAdmin(String studentName,String studentId,String courseName){

        QueryWrapper<StudentCourse> wrapper = new QueryWrapper<>();

        if (studentName != null) wrapper.like("student_name",studentName);
        if (studentId != null) wrapper.like("student_id",studentId);
        if (courseName != null) wrapper.like("course_name",courseName);

        return studentCourseMapper.selectList(wrapper);
    }

    /**
     * 添加学生课程信息
     */
    public int addStudentCourseInfo(StudentCourse studentCourse){
        return studentCourseMapper.insert(studentCourse);
    }

    /**
     * 修改学生成绩信息
     */
    public int editStudentScoreInformation(StudentCourse studentCourse){
        return studentCourseMapper.updateById(studentCourse);
    }

    /**
     * 删除学生成绩信息
     */
    public int deleteStudentScoreInformation(int id,String studentId,String courseId){
        String teacherId = teacherCourseMapper.getTeacherIdByCourseID(courseId);

        if (!teacherId.equals("")){
            studentTeacherMapper.deleteStudent_Teacher_ByStudentIdAndTeacherId(studentId,teacherId);
        }

        return studentCourseMapper.deleteById(id);
    }


}
