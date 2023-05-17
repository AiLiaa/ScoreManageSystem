
<template>
    <div class="myScore" style="margin-top: 3%">
      <el-form :inline="true" :model="ruleForm" ref="ruleForm" class="demo-form-inline" style="float: left;margin-left: 2%">
        <el-form-item label="" v-if="ruleForm.type!=1">
          <el-input type="text" v-model="ruleForm.studentName" placeholder="姓名" class="serch-input"></el-input>
        </el-form-item>

        <el-form-item label="" v-if="ruleForm.type!=1">
          <el-input type="text" v-model="ruleForm.studentId" placeholder="学号" class="serch-input"></el-input>
        </el-form-item>

        <el-form-item label="">
          <el-input type="text" v-model="ruleForm.courseName" placeholder="课程" class="serch-input"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button @click="submitForm('ruleForm')" style="float: left;margin-left: 2%">查询</el-button>
        </el-form-item>

      </el-form>


      <!-- 表格区域展示视图数据 -->
        <div class="wrapper" v-if="ruleForm.type==2">
            <el-table :data="tableData" stripe border height="400" ref="accountTable" style="width:100%">
              <el-table-column type="index" prop="" align="center" label="" width="50"></el-table-column>
              <el-table-column prop="studentName" align="center" label="姓名" width="150"></el-table-column>
              <el-table-column prop="studentId" align="center" label="学号" width="150"></el-table-column>
              <el-table-column prop="courseName" align="center" label="课程名" width="170"></el-table-column>
              <el-table-column prop="courseCredit" align="center" sortable="sortable" label="课程学分" width="150"></el-table-column>
              <el-table-column prop="dailyScore" align="center" sortable="sortable" label="平时成绩" width="150"></el-table-column>
              <el-table-column prop="examScore" align="center" sortable="sortable" label="期末成绩" width="150"> </el-table-column>
              <el-table-column prop="finalScore" align="center" sortable="sortable" label="最终成绩" width="150"> </el-table-column>
            </el-table>
        </div>

      <div class="wrapper" v-if="ruleForm.type==1">
        <el-table :data="tableData" stripe border height="400" ref="accountTable" style="width:100%">
          <el-table-column type="index" prop="" align="center" label="" width="100"></el-table-column>
          <el-table-column prop="courseName" align="center" label="课程名" width="170"></el-table-column>
          <el-table-column prop="courseCredit" align="center" sortable="sortable" label="课程学分" width="200"></el-table-column>
          <el-table-column prop="dailyScore" align="center" sortable="sortable" label="平时成绩" width="200"></el-table-column>
          <el-table-column prop="examScore" align="center" sortable="sortable" label="期末成绩" width="200"> </el-table-column>
          <el-table-column prop="finalScore" align="center" sortable="sortable" label="最终成绩" width="200"> </el-table-column>
        </el-table>
      </div>

      <!-- 表格区域展示视图数据 -->
      <div class="wrapper" v-if="ruleForm.type==3">
        <el-table :data="tableData" stripe border height="400" ref="accountTable" style="width:100%">
          <el-table-column type="index" prop="" align="center" label="" width="50"></el-table-column>
          <el-table-column prop="studentName" align="center" label="姓名" width="100"></el-table-column>
          <el-table-column prop="studentId" align="center" label="学号" width="130"></el-table-column>
          <el-table-column prop="courseName" align="center" label="课程名" width="170"></el-table-column>
          <el-table-column prop="courseCredit" align="center" sortable="sortable" label="课程学分" width="120"></el-table-column>
          <el-table-column prop="dailyScore" align="center" sortable="sortable" label="平时成绩" width="120"></el-table-column>
          <el-table-column prop="examScore" align="center" sortable="sortable" label="期末成绩" width="120"> </el-table-column>
          <el-table-column prop="finalScore" align="center" sortable="sortable" label="最终成绩" width="120"> </el-table-column>
          <el-table-column prop="" align="center" label="操作" width="180">
            <template slot-scope="scope">
              <el-button size="mini"  @click="edit(scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="deleteScore(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
</template>

<script>
    export default {

      data() {
        return {
          ruleForm: {
            studentId:'',
            studentMame: '',
            courseName:'',
            type:sessionStorage.getItem("type"),
          },
          tableData: this.$global_msg.score,
        };
      },

      methods: {

        submitForm(ruleForm){
          var url = 'http://localhost:8989/searchScore';
          this.axios({
            method: 'POST',
            url: url,
            data:{
              userId:sessionStorage.getItem("userId"),
              type:sessionStorage.getItem("type"),
              studentName:this.ruleForm.studentName,
              studentId:this.ruleForm.studentId,
              courseName:this.ruleForm.courseName,
            }
          }).then((resp) => {
            let data = resp.data;
            // console.log(data)
            if (data.success) {
              this.tableData = data.data.scoreList;
            }
          })

        },

        edit(row) {
          this.$global_msg.editStudentScoreInfo = row;
          this.$router.push({path:'/EditStudentScore'});
        },

        deleteScore(row) {
          this.$prompt('请输入管理员密码', '确认是否删除该学生成绩信息', {
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
            var url = 'http://localhost:8989/deleteStudentScore';
            var that = this;
            this.axios({
              method: 'POST',
              url: url,
              data:{
                id:row.id,
                adminId:sessionStorage.getItem("id"),
                type:sessionStorage.getItem("type"),
                studentId:row.studentId,
                password:value,
                courseId:row.courseId,
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
          var url = 'http://localhost:8989/'+ sessionStorage.getItem("userId") + '/' + sessionStorage.getItem("type") +'/score';
          this.axios({
            method:'GET',
            url: url,
          }).then((resp)=>{
            let data = resp.data;
            if (data.success) {
              this.tableData = data.data.scoreList;
              this.$router.push({path:'/Score'})
            }
          })
        }

      }
    }
</script>

<style lang="less" scoped>
.myScore{
    margin-left: 0px;
}
.header{
    display: flex;
    margin-top: 10px;
    margin-left: 20px;
}

.bread{
  display: flex;
  margin-left: 20px;
  margin-top: 20px;
}

.wrapper{
    margin: 0px 20px;
}

.input1_ {
    width: 150px;
}
.btn1_ {
    background-color: #409EFF;
    border: 0px;
    height: 30px;
    border-radius: 8px;
    color: white;
    width: 50px;
}

.dialogImg{
  width: 80%;
  //margin-left: 5%;
  height: 60%;
  margin-bottom: 5%;
}
.serch-input{
  //width: 150px;
  //float: left;
  //margin-left: 2%;
  //margin-bottom: 20px;

}
</style>