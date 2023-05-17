<template>
  <div id="poster">
    <el-form :model="ruleForm"  label-position="left" status-icon :rules="rules" ref="ruleForm" label-width="0px" class="register-container">
      <h3 class="register_title" style="color:#505458">
        <i class="el-icon-service"></i>
        修改密码
      </h3>

      <el-form-item label="" prop="password">
        <el-input type="password" v-model="ruleForm.password" autocomplete="off" placeholder="密码" prefix-icon="el-icon-lock"></el-input>
      </el-form-item>
      <el-form-item label="" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" placeholder="确认密码"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" style="float:left;width: 40%;background:blue;border:none;color:white" @click="back" icon="el-icon-left" class="button2">取消</el-button>
        <el-button type="primary" style="background:blue;border:none;color:white" @click="submitForm('ruleForm')" icon="el-icon-right" class="button2">确认修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

export default {
  name: 'ChangePassword',
  data() {
    var validatePass = (rule, value, callback) => {//检查密码是否合法
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {//检查重复密码是否合法
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        password:'',
        checkPass:''
      },
      rules: {
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(ruleForm) {
      //检查用户名，密码等是否已经输入完成
      if(this.ruleForm.password==="" ||this.ruleForm.checkPass===""){
        this.$message({
          message: '填写不完整',
          type: 'error'
        });
        return;
      }
      //此时表单内容已经输入完全

      var that = this;
      this.$axios({
        method:'POST',
        url:'http://localhost:8989/changePassword',
        data:{
          userId:sessionStorage.getItem("userId"),
          password:this.ruleForm.password,
        }
      }).then(function (response){
        if(response.data.message==="修改失败" || !response.data.success){
          that.$message({
            message: response.data.message,
            type: 'error'
          });
          return;
        }
        if(response.data.message==="修改成功"|| response.data.success){
          that.$message({
            message: response.data.message,
            type: 'success'
          });
          that.$router.push({path:'/Persondata'})
        }
      }).catch(function (error){
        that.$message({
          message: '服务器错误，请稍后重试！',
          type: 'error'
        });
      })
    },

    back(){
      this.$message({
        message: '取消修改',
        type: 'info'
      });
      this.$router.push({path:'/PersonData'})
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

.register-container{
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.register_title{
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

.button1{
  float:left ;
}
.button2{
  float: right;
  width: 200px;
}
</style>
