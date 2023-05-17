package com.example.stusys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.stusys.pojo.StudentCourse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {

    /**
     * 查询老师任教的所有课程的课程号
     */
    @Select("select course_id from teacher_course where teacher_id = #{teacherId}")
    List<String> getCourseId(String teacherId);

    /**
     * 查询老选修了该老师课程的所有学生的学号
     */
    @Select("select student_id from student_teacher where teacher_id = #{teacherId}")
    List<String> getStudentId(String teacherId);


    /**
     * 修改学生信息，根据oldStudentId
     */
    @Update("update student_course set student_id = #{studentId},student_name = #{studentName} where student_id = #{oldStudentId}")
    boolean updateStudent_Course(String studentName, String studentId, String oldStudentId);

    /**
     * 根据studentId删除
     */
    @Delete("delete from student_course where student_id = #{studentId}")
    boolean deleteStudent_Course_ByStudentId(String studentId);

    /**
     * 修改课程信息
     */
    @Update("update student_course set course_name = #{courseName}, course_credit = #{courseCredit} where course_id = #{courseId}")
    boolean updateStudent_Course_ByCourseId(int courseId,String courseName,float courseCredit);


    /**
     * 根据courseId删除
     */
    @Delete("delete from student_course where course_id = #{courseId}")
    boolean deleteStudent_Course_ByCourseId(int courseId);

    /**
     * 获取总学分
     * @param studentId
     * @return
     */
    @Select("select IFNULL(SUM(course_credit),0) as sumCredit from student_course where student_id = #{studentId}")
    float getCredit(String studentId);

}
