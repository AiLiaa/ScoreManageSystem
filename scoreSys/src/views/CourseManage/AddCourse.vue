<template>
  <div style="margin-top: 3%">
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="课程名" prop="courseName">
        <el-input v-model="ruleForm.courseName"></el-input>
      </el-form-item>

      <el-form-item label="课程学分" prop="courseCredit">
        <el-input v-model="ruleForm.courseCredit"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')" style="width: 150px">提交</el-button>
        <el-button @click="resetForm('ruleForm')" style="width: 150px">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  name: "AddCourse",
  data() {
    return {
      ruleForm: {
        courseName: '',
        courseCredit:'',
      },
      rules: {
        courseName: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
        courseCredit: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
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
        url:'http://localhost:8989/addCourse',
        data:{
          courseName:this.ruleForm.courseName,
          courseCredit:this.ruleForm.courseCredit,
        }
      }).then(function (response){
        if(response.data.success){
          that.$message({
            message: response.data.message,
            type: 'success'
          });
          that.resetForm('ruleForm');
        }else {
          that.$message({
            message: response.data.message,
            type: 'error'
          });
        }
      }).catch(function (error){
        that.$message({
          message: '服务器错误，请稍后重试！',
          type: 'error'
        });
      })
    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

  }
}
</script>