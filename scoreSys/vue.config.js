const { defineConfig } = require('@vue/cli-service')

//端口转发
let proxyObj ={};
proxyObj['/']={
  ws:false,
  target:'http://localhost:8989',
  changeOrigin:true,
  pathRewrite:{
    '^/':''
  }
}

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    host:'localhost',
    port:8888,//配置启动端口号
    proxy:proxyObj,
  }
})
