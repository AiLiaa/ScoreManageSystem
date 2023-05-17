package com.example.stusys.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentTeacherMapper {

    /**
     * 根据oldStudentId更新student_teacher表
     */
    @Update("update student_teacher set student_id = #{studentId} where student_id = #{oldStudentId}")
    boolean updateStudent_Teacher_ByOldStudentId(String studentId, String oldStudentId);

    /**
     * 根据studentId删除
     */
    @Delete("delete from student_teacher where student_id = #{studentId}")
    boolean deleteStudent_Teacher_ByStudentId(String studentId);

    /**
     * 根据studentId和teacherId删除
     */
    @Delete("delete from student_teacher where student_id = #{studentId} and teacher_id = #{teacherId}")
    boolean deleteStudent_Teacher_ByStudentIdAndTeacherId(String studentId,String teacherId);

    /**
     * 根据oldTeacherId更新student_teacher表
     */
    @Update("update student_teacher set teacher_id = #{teacherId} where teacher_id = #{oldTeacherId}")
    boolean updateStudent_Teacher_ByOldTeacherId(String teacherId, String oldTeacherId);

    /**
     * 根据teacherId删除
     */
    @Delete("delete from student_teacher where teacher_id = #{teacherId}")
    boolean deleteStudent_Teacher_ByTeacherId(String teacherId);

    /**
     * 添加
     */
    @Insert("insert into student_teacher (student_id,teacher_id) values (#{studentId},#{teacherId})")
    boolean addStudentTeacher(String studentId,String teacherId);


    @Select("select count(*) from student_teacher where student_id = #{studentId} and teacher_id = #{teacherId}")
    int getByStudentIdAndTeacherId(String studentId,String teacherId);

}
