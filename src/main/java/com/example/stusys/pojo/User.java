package com.example.stusys.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {

    @TableId
    private Integer id;
    private String userName;
    private String userId;//学号 教室工号
    private String userClass;//学生班级
    private float credit;//学生总学分
    private int type;// 用户类型。1:学生 2:老师 3:管理员
    private String password;
    private String major;//主修专业
}
