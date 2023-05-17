<template>
  <div class="" style="margin-top: 3%">

    <el-form :inline="true" :model="ruleForm" ref="ruleForm" class="demo-form-inline" style="float: left;margin-left: 2%">
      <el-form-item label="">
        <el-input type="text" v-model="ruleForm.studentName" placeholder="姓名" class="serch-input"></el-input>
      </el-form-item>

      <el-form-item label="">
        <el-input type="text" v-model="ruleForm.studentId" placeholder="学号" class="serch-input"></el-input>
      </el-form-item>

      <el-form-item label="">
        <el-input type="text" v-model="ruleForm.studentClass" placeholder="班级" class="serch-input"></el-input>
      </el-form-item>

      <el-form-item label="">
        <el-input type="text" v-model="ruleForm.major" placeholder="专业" class="serch-input"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="submitForm('ruleForm')" style="float: left;margin-left: 2%">查询</el-button>
      </el-form-item>

      <el-form-item>
        <el-button @click="reload" style="float: left;margin-left: 2%">更新总学分</el-button>
      </el-form-item>

    </el-form>

    <!-- 表格区域展示视图数据 -->
    <div class="" v-if="ruleForm.type==3" style="margin-left: 20px">
      <el-table :data="tableData" stripe border height="400" ref="accountTable" style="width:100%">
        <el-table-column type="index" prop="" align="center" label="" width="50"></el-table-column>
        <el-table-column prop="userName" align="center" label="姓名" width="150"></el-table-column>
        <el-table-column prop="userId" align="center" label="学号" width="150"></el-table-column>
        <el-table-column prop="userClass" align="center" label="班级" width="150"></el-table-column>
        <el-table-column prop="major" align="center" label="专业" width="150"></el-table-column>
        <el-table-column prop="credit" align="center" label="总学分" width="150"></el-table-column>
        <el-table-column prop="" align="center" label="操作" width="250">
          <template slot-scope="scope">
            <el-button size="mini"  @click="edit(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="deleteStudent(scope.row)">删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </div>

  </div>
</template>

<script>
export default {
  name: "StudentTable",
  data() {
    return {
      ruleForm: {
        studentName:'',
        studentId: '',
        studentClass:'',
        major:'',
        type:sessionStorage.getItem("type"),
      },
      tableData: this.$global_msg.studentInformation,
    };

  },

  methods: {

    submitForm(ruleForm){
      var url = 'http://localhost:8989/searchStudentInformation';
      this.axios({
        method: 'POST',
        url: url,
        data:{
          type:sessionStorage.getItem("type"),
          studentName:this.ruleForm.studentName,
          studentId:this.ruleForm.studentId,
          studentClass:this.ruleForm.studentClass,
          major:this.ruleForm.major,
        }
      }).then((resp) => {
        let data = resp.data;
        if (data.success) {
          this.tableData = data.data.searchStudentInfo;
        }
      })
    },

    edit(row) {
      this.$global_msg.editStudentInfo = row;
      this.$router.push({path:'/EditStudent'});
    },

    deleteStudent(row) {
      this.$prompt('请输入管理员密码', '确认是否删除该学生信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({ value }) => {
        if(value == null){
          this.$message({
            type: 'info',
            message: "输入不能为空"
          });
          return;
        }
        var url = 'http://localhost:8989/deleteStudent';
        var that = this;
        this.axios({
          method: 'POST',
          url: url,
          data:{
            id:sessionStorage.getItem("id"),
            type:sessionStorage.getItem("type"),
            studentId:row.userId,
            password:value
          }
        }).then((resp) => {
          let data = resp.data;
          if (data.success) {
            that.refresh()
            this.$message({
              type: 'success',
              message: data.message
            });
          }else {
            this.$message({
              type: 'error',
              message: data.message
            });
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },

    refresh(){
      var url = 'http://localhost:8989/'+ sessionStorage.getItem("type") +'/studentInformation';
      this.axios({
        method:'GET',
        url: url,
      }).then((resp)=>{
        let data = resp.data;
        if (data.success) {
          this.tableData = data.data.studentInfoList;
          this.$router.push({path:'/StudentTable'})
        }
      })
    },

    reload() {
      var url = 'http://localhost:8989/reloadCredit';
      var that = this;
      this.axios({
        method: 'GET',
        url: url,
      }).then((resp)=>{
        let data = resp.data;
        if (data.success) {
          that.refresh();
          this.$message({
            type: 'success',
            message: data.message
          });
        }
      })
    },

  }
}
</script>

<style scoped>
  .serch-input{
    width: 150px;
    float: left;
    margin-left: 2%;
    margin-bottom: 5px;
  }

</style>