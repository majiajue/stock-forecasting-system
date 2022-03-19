const {
  defineConfig
} = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:5000', // 接口地址
        changeOrigin: true, // 是否跨域
        ws: true,
        pathRewrite: {
          // 转发
          '^/api': ''
        },
        secure: false
      },
    }
  }
})
