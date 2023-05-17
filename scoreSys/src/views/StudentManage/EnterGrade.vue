<template>
  <div style="margin-top: 3%;margin-left: 5%">
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="学生姓名" prop="studentName">
        <el-input v-model="ruleForm.studentName"></el-input>
      </el-form-item>

      <el-form-item label="学生学号" prop="studentId">
        <el-input v-model="ruleForm.studentId"></el-input>
      </el-form-item>
      <el-form-item label="课程名" prop="courseName">
        <el-input v-model="ruleForm.courseName"></el-input>
      </el-form-item>

      <el-form-item label="教师" prop="teacherName">
        <el-input v-model="ruleForm.teacherName"></el-input>
      </el-form-item>

      <el-form-item label="教师工号" prop="teacherId">
        <el-input v-model="ruleForm.teacherId"></el-input>
      </el-form-item>

      <el-form-item label="平时成绩" prop="dailyScore">
        <el-input v-model="ruleForm.dailyScore"></el-input>
      </el-form-item>

      <el-form-item label="期末成绩" prop="examScore">
        <el-input v-model="ruleForm.examScore"></el-input>
      </el-form-item>

      <el-form-item label="最终成绩" prop="finalScore" >
        <el-input v-model="ruleForm.finalScore"></el-input>
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
  name: "EnterGrade",
  data() {
    return {
      ruleForm: {
        studentName: '',
        studentId:'',
        courseName:'',
        teacherName:'',
        teacherId:'',
        dailyScore:'',
        examScore:'',
        finalScore:'',
      },
      rules: {
        studentName: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
        studentId: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
        courseName: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
        teacherName: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
        teacherId: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
        dailyScore: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
        examScore: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
        finalScore: [
          { required: true, message: '不能为空', trigger: 'blur' },
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      //检查用户名，密码等是否已经输入完成
      if(this.ruleForm.studentName==="" ||this.ruleForm.studentId===""
          ||this.ruleForm.courseName===""||this.ruleForm.teacherName===""
          ||this.ruleForm.teacherId===""||this.ruleForm.dailyScore===""
          ||this.ruleForm.examScore===""||this.ruleForm.finalScore===""){
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
        url:'http://localhost:8989/enterGrade',
        data:{
          studentId:this.ruleForm.studentId,
          studentName:this.ruleForm.studentName,
          courseName:this.ruleForm.courseName,
          teacherName:this.ruleForm.teacherName,
          teacherId:this.ruleForm.teacherId,
          dailyScore:this.ruleForm.dailyScore,
          examScore:this.ruleForm.examScore,
          finalScore:this.ruleForm.finalScore,
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