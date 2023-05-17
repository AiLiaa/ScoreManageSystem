<template>
  <div class="" style="margin-top: 3%">

    <el-form :inline="true" :model="ruleForm" ref="ruleForm" class="demo-form-inline" style="float: left;margin-left: 2%">
      <el-form-item label="">
        <el-input type="text" v-model="ruleForm.teacherName" placeholder="姓名" class="serch-input"></el-input>
      </el-form-item>

      <el-form-item label="">
        <el-input type="text" v-model="ruleForm.teacherId" placeholder="工号" class="serch-input"></el-input>
      </el-form-item>

      <el-form-item label="">
        <el-input type="text" v-model="ruleForm.major" placeholder="专业" class="serch-input"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="submitForm('ruleForm')" style="float: left;margin-left: 2%">查询</el-button>
      </el-form-item>

    </el-form>

    <!-- 表格区域展示视图数据 -->
    <div class="" v-if="ruleForm.type==3" style="margin-left: 20px">
      <el-table :data="tableData" stripe border height="400" ref="accountTable" style="width:100%">
        <el-table-column type="index" prop="" align="center" label="" width="50"></el-table-column>
        <el-table-column prop="userName" align="center" label="姓名" width="200"></el-table-column>
        <el-table-column prop="userId" align="center" label="工号" width="200"></el-table-column>
        <el-table-column prop="major" align="center" label="专业" width="200"></el-table-column>
        <el-table-column prop="" align="center" label="操作" width="300">
          <template slot-scope="scope">
            <el-button size="mini"  @click="edit(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="deleteTeacher(scope.row)">删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </div>

  </div>
</template>

<script>
export default {
  name: "TeacherTable",
  data() {
    return {
      ruleForm: {
        teacherName:'',
        teacherId: '',
        major:'',
        type:sessionStorage.getItem("type"),
      },
      tableData: this.$global_msg.teacherInformation,
    };

  },

  methods: {

    submitForm(ruleForm){
      var url = 'http://localhost:8989/searchTeacherInformation';
      this.axios({
        method: 'POST',
        url: url,
        data:{
          type:sessionStorage.getItem("type"),
          teacherName:this.ruleForm.teacherName,
          teacherId:this.ruleForm.teacherId,
          major:this.ruleForm.major,
        }
      }).then((resp) => {
        let data = resp.data;
        if (data.success) {
          this.tableData = data.data.searchTeacherInfo;
        }
      })
    },

    edit(row) {
      this.$global_msg.editTeacherInfo = row;
      this.$router.push({path:'/EditTeacher'});
    },

    deleteTeacher(row) {
      this.$prompt('请输入管理员密码', '确认是否删除该教师信息', {
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
        var url = 'http://localhost:8989/deleteTeacher';
        var that = this;
        this.axios({
          method: 'POST',
          url: url,
          data:{
            id:sessionStorage.getItem("id"),
            type:sessionStorage.getItem("type"),
            teacherId:row.userId,
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
      var url = 'http://localhost:8989/'+ sessionStorage.getItem("type") +'/teacherInformation';
      this.axios({
        method:'GET',
        url: url,
      }).then((resp)=>{
        let data = resp.data;
        if (data.success) {
          this.tableData = data.data.teacherInfoList;
          this.$router.push({path:'/TeacherTable'})
        }
      })
    }

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