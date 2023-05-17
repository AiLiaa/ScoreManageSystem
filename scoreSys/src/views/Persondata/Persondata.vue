
<template>
  <el-descriptions class="margin-top" :column="1" :size="size" border style="margin-top: 5%">

    <el-descriptions-item>
      <template slot="label">
        <div style="text-align: center">
          姓名
        </div>
      </template>
      <div style="text-align: center">
        {{userName}}
      </div>
    </el-descriptions-item>

    <el-descriptions-item>
      <template slot="label" >
        <div style="text-align: center">
          学号/工号
        </div>
      </template>
      <div style="text-align: center">
        {{userId}}
      </div>
    </el-descriptions-item>

    <el-descriptions-item >
      <template slot="label" >
        <div style="text-align: center">
          用户类型
        </div>
      </template>
      <div style="text-align: center" v-if="type==1">
        <el-tag>学生</el-tag>
      </div>
      <div style="text-align: center" v-if="type==2">
        <el-tag>教师</el-tag>
      </div>
      <div style="text-align: center" v-if="type==3">
        <el-tag>管理员</el-tag>
      </div>
    </el-descriptions-item>

    <el-descriptions-item v-if="type!=3">
      <template slot="label" >
        <div style="text-align: center">
          专业
        </div>
      </template>
      <div style="text-align: center">
        {{major}}
      </div>
    </el-descriptions-item>

    <el-descriptions-item v-if="type==1">
      <template slot="label">
        <div style="text-align: center">
          班级
        </div>
      </template>
      <div style="text-align: center">
        {{userClass}}
      </div>
    </el-descriptions-item>

    <el-descriptions-item v-if="type==1">
      <template slot="label" >
        <div style="text-align: center">
          总学分
        </div>
      </template>
      <div style="text-align: center">
        {{credit}}
      </div>

    </el-descriptions-item>

    <el-descriptions-item>
      <template slot="label" >
        <div style="text-align: center">
          密码
        </div>
      </template>
      <div style="text-align: center">
        <el-button type="text" @click="toChange">修改密码</el-button>
      </div>

    </el-descriptions-item>
  </el-descriptions>
</template>

<script>
export default {
  data () {
    return {
      size: '',
      type: sessionStorage.getItem("type"),
      userName: sessionStorage.getItem("userName"),
      userId: sessionStorage.getItem("userId"),
      credit: sessionStorage.getItem("credit"),
      userClass: sessionStorage.getItem("userClass"),
      major: sessionStorage.getItem("major"),

    };
  },

  methods: {
    toChange(){
      this.$router.push({path:'/ChangePassword'})
    },

  }
}
</script>

<style>

</style>