package com.example.stusys.controller;

import com.example.stusys.pojo.Course;
import com.example.stusys.pojo.StudentCourse;
import com.example.stusys.pojo.User;
import com.example.stusys.service.CourseManageService;
import com.example.stusys.service.StudentManageService;
import com.example.stusys.service.UserService;
import com.example.stusys.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentManageController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentManageService studentManageService;

    @Autowired
    private CourseManageService courseManageService;

    /**
     * 学生信息展示
     */
    @GetMapping("/{type}/studentInformation")
    public R getAllStudentInformation(@PathVariable("type") int type){
        if (type != 3){
            return R.fail().message("非管理员账号,无法查询");
        }

        List<User> studentInfoList = studentManageService.getAllStudentInformation();

        return R.ok().data("studentInfoList",studentInfoList);
    }

    /**
     * 搜索学生信息
     */
    @PostMapping("/searchStudentInformation")
    public R searchStudentInformation(int type,String studentName,String studentId,String studentClass,String major){
        if (type != 3){
            return R.fail().message("非管理员账号,无法查询");
        }

        List<User> students = studentManageService.searchStudentInformation(studentName, studentId,studentClass,major);

        return R.ok().data("searchStudentInfo",students);
    }

    /**
     * 修改学生信息
     */
    @PostMapping("/updateStudent")
    public R editStudentInformation(int id,String studentName,String studentId,String studentClass,String major){

        User user = userService.getUserById(id);

        String oldStudentId = user.getUserId();

        if (user == null){
            return R.error().message("未找到用户");
        }

        //学号不唯一
        if (!studentId.equals(oldStudentId)){
            if (userService.getUserByUserId(studentId) != null){
                return R.fail().message("该学号已存在");
            }
        }

        user.setUserName(studentName);
        user.setUserId(studentId);
        user.setUserClass(studentClass);
        user.setMajor(major);

        //修改user,student_course, student_teacher表
        int i = studentManageService.editStudentInformation(user,oldStudentId);

        if (i == 1){
            return R.ok().message("修改成功");
        }else {
            return R.fail().message("服务器繁忙，修改失败");
        }
    }

    /**
     * 删除学生信息
     */
    @PostMapping("/deleteStudent")
    public R deleteStudentInformation(int id,int type,String studentId,String password){

        User user = userService.getUserByUserId(studentId);

        if (user == null){
            return R.error().message("未找到用户");
        }
        if (type != 3){
            return R.fail().message("非管理员账号");
        }
//        System.out.println("password:" + password);

        User admin = userService.getUserById(id);
        if (!admin.getPassword().equals(password)){
            return R.fail().message("密码错误");
        }

        // 删除三个表的学生信息,user,student_course,student_teacher
        int i = studentManageService.deleteStudentInformation(user.getId(), studentId);

        if (i == 1){
            return R.ok().message("删除成功");
        }else {
            return R.fail().message("服务器繁忙，请稍后重试");
        }

    }

    /**
     * 录入学生信息
     */
    @PostMapping("/addStudent")
    public R addStudent(String studentName,String studentId,String studentClass,String major){
        User user = userService.studentIsExist(studentId);

        if (user != null) {
            return R.fail().message("该学号已存在");
        }


        User addUser = new User();
        addUser.setUserName(studentName);
        addUser.setUserId(studentId);
        addUser.setUserClass(studentClass);
        addUser.setMajor(major);
        addUser.setType(1);

        int i = studentManageService.addStudent(addUser);

        if (i == 1){
            return R.ok().message("该学生信息录入成功");
        }else {
            return R.fail().message("服务器繁忙，请稍后重试");
        }
    }

    /**
     * 录入学生成绩
     */
    @PostMapping("/enterGrade")
    public R enterGrade(String studentId,String studentName,String courseName,
                        String teacherName,String teacherId,float dailyScore,
                        float examScore,float finalScore){
//        System.out.println("teacherName:" + teacherName);
//        System.out.println("teacherId:" + teacherId);
//        System.out.println(dailyScore);
//        System.out.println(examScore);
//        System.out.println(finalScore);

        //检查该学生信息是否存在
        User student = userService.getStudentByIdAndName(studentId, studentName);
        if (student == null){
            return R.fail().message("未查询该学生，或学生信息缺失，请先录入学生信息");
        }
        //检查课程信息是否存在
        Course course = courseManageService.getCourseByCourseName(courseName);
        if (course == null){
            return R.fail().message("未查询该课程，或课程信息缺失，请先录入课程信息");
        }
        //检查教师信息是否存在
        User teacher = userService.getTeacherByIdAndName(teacherId, teacherName);
        if (teacher == null){
            return R.fail().message("未查询该教师，或教师信息缺失，请先录入教师信息");
        }
        StudentCourse studentCourse = new StudentCourse(studentId,studentName,course.getId(),
                courseName,course.getCourseCredit(),dailyScore,examScore,finalScore);

        int i = studentManageService.enter(studentId, teacherId, course.getId(), studentCourse);

        if (i == 1){
            return R.ok().message("该学生成绩录入成功");
        }else if (i == 123){
            return R.fail().message("该学生课程成绩已录入");
        } else {
            return R.error().message("服务器繁忙，请稍后重试");
        }
    }

    @GetMapping("/reloadCredit")
    public R reloadCredit(){
        List<User> allStudent = userService.getAllStudent();

        if (allStudent == null) return R.fail().message("未录入学生");

        for (User student : allStudent) {
            studentManageService.reloadCredit(student.getUserId());
        }

        return R.ok().message("更新成功");
    }

}
