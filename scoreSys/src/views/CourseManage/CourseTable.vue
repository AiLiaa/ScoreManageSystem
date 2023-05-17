<template>
  <div class="" style="margin-top: 3%">

    <el-form :inline="true" :model="ruleForm" ref="ruleForm" class="demo-form-inline" style="float: left;margin-left: 2%">
      <el-form-item label="">
        <el-input type="text" v-model="ruleForm.courseName" placeholder="课程名" class="serch-input"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="submitForm('ruleForm')" style="float: left;margin-left: 2%">查询</el-button>
      </el-form-item>

    </el-form>

    <!-- 表格区域展示视图数据 -->
    <div class="" v-if="ruleForm.type==3" style="margin-left: 20px">
      <el-table :data="tableData" stripe border height="400" ref="accountTable" style="width:100%">
        <el-table-column type="index" prop="" align="center" label="" width="50"></el-table-column>
        <el-table-column prop="courseName" align="center" label="课程名" width="250"></el-table-column>
        <el-table-column prop="courseCredit" align="center" label="学分" width="250"></el-table-column>
        <el-table-column prop="" align="center" label="操作" width="300">
          <template slot-scope="scope">
            <el-button size="mini"  @click="edit(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="deleteCourse(scope.row)">删除</el-button>
          </template>
        </el-table-column>

      </el-table>
    </div>

  </div>
</template>

<script>
export default {
  name: "CourseTable",
  data() {
    return {
      ruleForm: {
        courseName:'',
        type:sessionStorage.getItem("type"),
      },
      tableData: this.$global_msg.courseInformation,
    };

  },

  methods: {

    submitForm(ruleForm){
      var url = 'http://localhost:8989/searchCourseInformation';
      this.axios({
        method: 'POST',
        url: url,
        data:{
          type:sessionStorage.getItem("type"),
          courseName:this.ruleForm.courseName,
        }
      }).then((resp) => {
        let data = resp.data;
        if (data.success) {
          this.tableData = data.data.searchCourseInfo;
        }
      })
    },

    edit(row) {
      this.$global_msg.editCourseInfo = row;
      this.$router.push({path:'/EditCourse'});
    },

    deleteCourse(row) {
      this.$prompt('请输入管理员密码', '确认是否删除该课程信息', {
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
        var url = 'http://localhost:8989/deleteCourse';
        var that = this;
        this.axios({
          method: 'POST',
          url: url,
          data:{
            id:sessionStorage.getItem("id"),
            type:sessionStorage.getItem("type"),
            courseId:row.id,
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
      var url = 'http://localhost:8989/'+ sessionStorage.getItem("type") +'/courseInformation';
      this.axios({
        method:'GET',
        url: url,
      }).then((resp)=>{
        let data = resp.data;
        if (data.success) {
          this.tableData = data.data.courseInfoList;
          this.$router.push({path:'/CourseTable'})
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