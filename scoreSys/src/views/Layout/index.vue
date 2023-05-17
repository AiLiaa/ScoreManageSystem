<template>
    <div class="content">
        <!-- 头部 -->
        <Header/>
        <div class="layout">
            <!-- 左侧导航区域 -->
            <Mymenu class="menu" />
            <!-- 右侧内容区域 -->
            <Content class="rightcontent" />
        </div>
    </div>
</template>

<script>
import Header from './MyHeader.vue'
import Mymenu from './Mymenu.vue'
import Content from './Content.vue'
    export default {
        components:{
            Header,
            Mymenu,
            Content
        },
        data(){
            return {
            }
        },
        methods:{

        }
    }
</script>

<style lang="less" scoped>
// .content{

// }
.layout{
    width: 100%;
    height: calc(100vh - 60px);
    .menu{
        width: 200px;
        height: 100%;
        background: #D3D3D3;
        float: left;
    }
    .content{
        margin-left:100%;
        overflow-y: auto;
        margin-left: 200px;
    }
    // display: flex;
    // .menu{
    //     background: #D3D3D3;
    //     position: fixed;
    //     top: 60px;
    //     bottom: 0;
    // }
    // .content{
    //     margin-left: 0px;
    //     width: 100%;
    // }
}
</style>