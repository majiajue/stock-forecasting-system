const {
  defineConfig
} = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8081,
    proxy: {
      '/spring': {
        // 接口地址
        target: 'http://127.0.0.1:8080',
        // 是否跨域
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          // 忽略
          '^/spring': ''
        },
        //接受对方是https的接口
        secure: false
      },
    }
  }
})