<template>
    <div class="content">
        <!-- 内容区域--路由出口 -->
        <router-view/>
    </div>
</template>
<script>
    export default {
        methods:{
             loginout(){
                 this.$router.push({path:'/'})
             }
        }
    }
</script>
<style lang="less" scoped>
// .header{
//     // display: flex;
//     height: 60px;
//     background-color:#4682B4;
//     // width: 100%;
//     line-height: 60px;
//     color: #fff;
//     display: flex;
// }
// .title{
//         font-size: 35px;
//         font-family: 楷体;
//         float: left;
//     }

// #left1,#right1{
//         display: flex;
//         height: 60px;
//         width: 60px;
//         line-height: 60px;
//         flex: 1;
//         font-size: 30px;
// }
// .header-right{
//     float: right;
//     // padding-right: 20px;
//     display: flex;
//     font-size: 20px;
//     .user{
//         margin-left: 20px;
//         // font-size: 20px;
//     };
// #return{
//         display: flex;
//         height: 60px;
//         width: 60px;
//         line-height: 60px;
//         font-size: 30px;
//     }

// .icon {
//   width: 1.5rem;
//   height: 1.5em;
//   line-height: 1.5rem;
//   vertical-align: -0.15em;
//   fill: currentColor;
//   overflow: hidden;
// }
// }

</style>