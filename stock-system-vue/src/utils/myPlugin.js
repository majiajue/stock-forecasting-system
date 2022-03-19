import { get, post, put, del } from './request'
// 定义一个插件
export default {
  // 插件中必须包含一个install方法
  install: function (Vue) {
    // 给vue混入成员
    Vue.mixin({
      methods: {
        // get请求方法
        $get (url, params) {
          return get(url, params)
        },
        // post请求方法
        $post (url, params) {
          return post(url, params)
        },
        $put (url, params) {
          return put(url, params)
        },
        $del (url, params) {
          return del(url, params)
        }
      }
    })
  }
}
