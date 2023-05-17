package com.example.stusys.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("student_course")
public class StudentCourse {

    @TableId
    private Integer id;
    private String studentId;
    private String studentName;
    private int courseId;
    private String courseName;
    private float courseCredit;
    private float dailyScore;
    private float examScore;
    private float finalScore;

    public StudentCourse(String studentId,String studentName,int courseId,
                  String courseName,float courseCredit,float dailyScore,
                  float examScore,float finalScore){
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.dailyScore = dailyScore;
        this.examScore = examScore;
        this.finalScore = finalScore;

    }
}
