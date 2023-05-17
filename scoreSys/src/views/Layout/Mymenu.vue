<template>
<div>
    <el-row class="tac">
      <el-col :span="24">
        <el-menu default-active="/Score" class="el-menu-vertical-demo">

          <el-menu-item  @click="personData">
            <span slot="title">个人中心</span>
          </el-menu-item>

          <el-menu-item  @click="myScore">
            <span slot="title">学生成绩</span>
          </el-menu-item>

          <el-submenu index="1" v-if="type==3">
            <template slot="title">
              <span>学生管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click="StudentTable">学生列表</el-menu-item>
              <el-menu-item index="1-2" @click="AddStudent">添加学生</el-menu-item>
              <el-menu-item index="1-3" @click="EnterGrade">成绩录入</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="2" v-if="type==3">
            <template slot="title">
              <span>教师管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="2-1" @click="TeacherTable">教师列表</el-menu-item>
              <el-menu-item index="2-2" @click="AddTeacher">添加教师</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="3" v-if="type==3">
            <template slot="title">
              <span>课程管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="3-1" @click="CourseTable">课程列表</el-menu-item>
              <el-menu-item index="3-2" @click="AddCourse">课程录入</el-menu-item>
            </el-menu-item-group>
          </el-submenu>


          <el-menu-item  @click="myScore" style="margin-top: 10%;float: bottom">
            <span slot="title">
              <div class="user" @click="loginout()" style="">退出登录</div>
            </span>
          </el-menu-item>

        </el-menu>
  </el-col>
</el-row>

    </div>
</template>

<script>
    export default {
      data(){
        return {
          type:sessionStorage.getItem("type"),
        };
      },

        methods: {
          myScore() {

            var url = 'http://localhost:8989/'+ sessionStorage.getItem("userId") + '/' + sessionStorage.getItem("type") +'/score';
            this.axios({
              method:'GET',
              url: url,
            }).then((resp)=>{
              let data = resp.data;
              // console.log(data)
              if (data.success) {
                this.$global_msg.score = data.data.scoreList;
                this.$router.push({path:'/Score'})
              }
            })
          },

          loginout(){
            this.$message({
              message: '退出登录',
              type: 'info'
            });
            this.$router.push({path:'/'})
          },

          personData(){
            this.$router.push({path:'/Persondata'})
          },

          StudentTable(){
            var url = 'http://localhost:8989/'+ sessionStorage.getItem("type") +'/studentInformation';
            this.axios({
              method:'GET',
              url: url,
            }).then((resp)=>{
              let data = resp.data;
              if (data.success) {
                this.$global_msg.studentInformation = data.data.studentInfoList;
                this.$router.push({path:'/StudentTable'})
              }
            })
          },

          AddStudent(){
            this.$router.push({path:'/AddStudent'})
          },

          EnterGrade(){
            this.$router.push({path:'/EnterGrade'})
          },

          TeacherTable(){
            var url = 'http://localhost:8989/'+ sessionStorage.getItem("type") +'/teacherInformation';
            this.axios({
              method:'GET',
              url: url,
            }).then((resp)=>{
              let data = resp.data;
              if (data.success) {
                this.$global_msg.teacherInformation = data.data.teacherInfoList;
                this.$router.push({path:'/TeacherTable'})
              }
            })
          },

          AddTeacher(){
            this.$router.push({path:'/AddTeacher'})
          },

          CourseTable(){
            var url = 'http://localhost:8989/'+ sessionStorage.getItem("type") +'/courseInformation';
            this.axios({
              method:'GET',
              url: url,
            }).then((resp)=>{
              let data = resp.data;
              if (data.success) {
                this.$global_msg.courseInformation = data.data.courseInfoList;
                this.$router.push({path:'/CourseTable'})
              }
            })
          },

          AddCourse(){
            this.$router.push({path:'/AddCourse'})
          },

      },
    }
</script>

<style lang="less" scoped>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 100%;
    min-height: 700px;
    text-align: center;
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
</style>