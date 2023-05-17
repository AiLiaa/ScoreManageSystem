<template>
  <div style="margin-top: 5%;margin-left: 5%">
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="教师姓名" prop="teacherName">
        <el-input v-model="ruleForm.teacherName"></el-input>
      </el-form-item>

      <el-form-item label="教师工号" prop="teacherId">
        <el-input v-model="ruleForm.teacherId"></el-input>
      </el-form-item>
      <el-form-item label="教师专业" prop="major">
        <el-input v-model="ruleForm.major"></el-input>
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
  name: "AddTeacher",
  data() {
    return {
      ruleForm: {
        teacherName: '',
        teacherId:'',
        major:'',
      },
      rules: {
        teacherName: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
        teacherId: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
        major: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      //检查用户名，密码等是否已经输入完成
      if(this.ruleForm.teacherName==="" ||this.ruleForm.teacherId===""
          ||this.ruleForm.major===""){
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
        url:'http://localhost:8989/addTeacher',
        data:{
          teacherName:this.ruleForm.teacherName,
          teacherId:this.ruleForm.teacherId,
          major:this.ruleForm.major,
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