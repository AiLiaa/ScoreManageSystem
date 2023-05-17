package com.example.stusys.controller;

import com.example.stusys.pojo.StudentCourse;
import com.example.stusys.pojo.User;
import com.example.stusys.service.StudentCourseService;
import com.example.stusys.service.UserService;
import com.example.stusys.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @Autowired
    private UserService userService;

    /**
     * 学生成绩展示
     */
    @GetMapping("/{id}/{type}/score")
    public R getStudentScore(@PathVariable("id") String studentId, @PathVariable("type") int type){
//        System.out.println(type);
        List<StudentCourse> scoreList = null;
        //学生
        if (type == 1) {
            scoreList = studentCourseService.getScoreByStudent(studentId);
        }else if (type == 2){
            //教师
            scoreList = studentCourseService.getScoreByTeacher(studentId);
        }else if (type == 3){
            // 管理员
            scoreList = studentCourseService.getScoreByAdmin();
        }

        return R.ok().data("scoreList", scoreList);
    }

    /**
     * 查询成绩
     */
    @PostMapping("/searchScore")
    public R getStudentScoreByStudent(String userId, int type, String studentName,String studentId,String courseName){
        List<StudentCourse> scoreList = null;
        //学生
        if (type == 1){
            scoreList = studentCourseService.searchScoreByStudent(userId,courseName);
        }else if (type == 2){
            //老师
            scoreList = studentCourseService.searchScoreByTeacher(userId,studentName,studentId,courseName);
        }else if (type == 3){
            // 管理员
            scoreList = studentCourseService.searchScoreByAdmin(studentName,studentId,courseName);
        }

        return R.ok().data("scoreList", scoreList);
    }

    /**
     * 修改学生成绩信息
     */
    @PostMapping("/updateStudentScore")
    public R editStudentInformation(int id,float dailyScore,float examScore,float finalScore){

        StudentCourse sc = studentCourseService.getById(id);

        if (sc == null){
            return R.fail().message("未查询到学生成绩");
        }

        sc.setDailyScore(dailyScore);
        sc.setExamScore(examScore);
        sc.setFinalScore(finalScore);

        int i = studentCourseService.editStudentScoreInformation(sc);

        if (i == 1){
            return R.ok().message("修改成功");
        }else {
            return R.fail().message("服务器繁忙，修改失败");
        }
    }

    /**
     * 删除学生成绩信息
     */
    @PostMapping("/deleteStudentScore")
    public R deleteStudentInformation(int id,int adminId,int type,String studentId,String courseId,String password){

        if (type != 3) {
            return R.fail().message("非管理员账号");
        }

        User admin = userService.getUserById(adminId);
        if (!admin.getPassword().equals(password)){
            return R.fail().message("密码错误");
        }

        int i = studentCourseService.deleteStudentScoreInformation(id, studentId,courseId);

        if (i == 1){
            return R.ok().message("删除成功");
        }else {
            return R.fail().message("服务器繁忙，请稍后重试");
        }

    }

}
