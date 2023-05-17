package com.example.stusys.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface TeacherCourseMapper {

    /**
     * 修改老师信息，根据oldTeacherId
     */
    @Update("update teacher_course set teacher_id = #{teacherId} where teacher_id = #{oldTeacherId}")
    boolean updateTeacher_Course(String teacherId, String oldTeacherId);

    /**
     * 根据teacherId删除
     */
    @Delete("delete from teacher_course where teacher_id = #{teacherId}")
    boolean deleteTeacher_Course_ByTeacherId(String teacherId);

    /**
     * 根据courseId删除
     */
    @Delete("delete from teacher_course where course_id = #{courseId}")
    boolean deleteTeacher_Course_ByCourseId(int courseId);

    /**
     * 添加老师课程信息
     */
    @Insert("insert into teacher_course (course_id,teacher_id) values (#{courseId},#{teacherId})")
    boolean addTeacherCourse(int courseId,String teacherId);

    @Select("select count(*) from teacher_course where course_id = #{courseId} and teacher_id = #{teacherId}")
    int getByTeacherIdAndCourseId(int courseId,String teacherId);

    @Select("select teacher_id from teacher_course where course_id = #{courseId}")
    String getTeacherIdByCourseID(String courseId);
}
