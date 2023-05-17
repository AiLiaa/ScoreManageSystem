<template>
  <div id="poster">
    <el-form :model="ruleForm"  label-position="left" status-icon ref="ruleForm" :rules="rules" label-width="0px" class="container">
      <h3 class="title" style="color:#505458">
        编辑课程信息
      </h3>

      <el-form-item label="" prop="courseName">
        <el-input type="text" v-model="ruleForm.courseName"placeholder="课程名"></el-input>
      </el-form-item>

      <el-form-item label="" prop="courseCredit">
        <el-input type="text" v-model="ruleForm.courseCredit" autocomplete="off" placeholder="学分"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="cancel" class="button1">取消</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" class="button2">确认</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
export default {
  name: "EditCourse",
  data(){
    return {
      ruleForm: {
        courseName: this.$global_msg.editCourseInfo.courseName,
        courseCredit:this.$global_msg.editCourseInfo.courseCredit,
      },
      rules: {
        courseName: [
          { required: true,message: '不能为空', trigger: 'blur'}
        ],
        courseCredit: [
          { required: true, message: '不能为空', trigger: 'blur' }
        ],
      }
    };
  },

  methods:{
    cancel(){
      this.$message({
        message: '取消修改',
        type: 'info'
      });
      this.$router.push({path:'/CourseTable'})
    },

    submitForm(ruleForm) {
      //检查用户名，密码等是否已经输入完成
      if(this.ruleForm.courseName==="" ||this.ruleForm.courseCredit===""){
        this.$message({
          message: '信息填写不完整',
          type: 'error'
        });
        return;
      }
      //此时表单内容已经输入完全
      var that = this;

      this.$axios({
        method:'POST',
        url:'http://localhost:8989/updateCourse',
        data:{
          id:that.$global_msg.editCourseInfo.id,
          courseName:this.ruleForm.courseName,
          courseCredit:this.ruleForm.courseCredit,
        }
      }).then(function (response){
        if(!response.data.success){
          that.$message({
            message: response.data.message,
            type: 'error'
          });
          return;
        }
        if(response.data.success){
          that.$message({
            message: response.data.message,
            type: 'success'
          });
          that.skipCourseTable()
        }
      }).catch(function (error){
        that.$message({
          message: '服务器错误，请稍后重试！',
          type: 'error'
        });
      })
    },

    skipCourseTable(){
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
    }

  }

}
</script>

<style>
#poster{
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
  margin: 0px;
  padding: 0px;
}

.container{
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 450px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.title{
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

.button1{
  float:left ;
  width: 40%;

}
.button2{
  float: right;
  width: 40%;
}
</style>