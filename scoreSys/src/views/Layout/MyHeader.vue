<template>
    <div>
        <div class="header">
            <span class="title" style="margin-left: 10px;">学生成绩管理系统</span>
<!--            <div class="header-right">-->
<!--&lt;!&ndash;              <div class="">{{this.$global_msg.username}}</div>&ndash;&gt;-->
<!--              <div class="user" @click="loginout()" style="">退出登录</div>-->
<!--            </div>-->
        </div>
    </div>
</template>

<script>
    export default {
        methods:{

        }
    }
</script>
<style lang="less" scoped>
.header{
    // display: flex;
    height: 60px;
    width: 100%;
    background-color:#4682B4;
    // width: 100%;
    line-height: 60px;
    color: #fff;
    display: flex;
}
.title{
  font-size: 35px;
  font-family: 楷体;
  float: left;
  color: white
    }

#left1,#right1{
        display: flex;
        height: 60px;
        width: 60px;
        line-height: 60px;
        flex: 1;
        font-size: 30px;
}
.header-right{
    float: left;
    display: flex;
    font-size: 20px;
    .user{
        margin-left: 40px;
        // font-size: 20px;
    };
#return{
        display: flex;
        height: 60px;
        width: 60px;
        line-height: 60px;
        font-size: 30px;
    }

.icon {
  width: 1.5rem;
  height: 1.5em;
  line-height: 1.5rem;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
}

</style>