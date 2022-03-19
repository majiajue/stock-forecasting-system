import axios from 'axios'
// 导入nprogress加载进度条
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

// 初始化一个axios对象
var instance = axios.create({
  // 接口地址根路径
  baseURL: '/api/',
  // 超时时间
  timeout: 3000
})
// 添加请求拦截器
instance.interceptors.request.use(
  function (config) {
    // 在发送请求之前做些什么
    NProgress.start()
    config.headers.Authorization = sessionStorage.getItem('token')
    return config
  }, function (error) {
    // 对请求错误做些什么
    NProgress.done()
    return Promise.reject(error)
  })
// 添加响应拦截器
instance.interceptors.response.use(
  function (response) {
    // 对响应数据做点什么
    NProgress.start()
    return response
  }, function (error) {
    // 对响应错误做点什么
    NProgress.done()
    return Promise.reject(error)
  })
// 创建一个GET请求方法
const get = async function (url, params) {
  const { data } = await instance.get(url, { params })
  return data
}
// 创建一个post请求方法
const post = async function (url, params) {
  const { data } = await instance.post(url, params)
  return data
}
// 创建一个put请求方法
const put = async function (url, params) {
  const { data } = await instance.put(url, params)
  return data
}
// 创建一个deconste请求方法
const del = async function (url, params) {
  const { data } = await instance.deconste(url, { params })
  return data
}
// 创建一个将token信息保存到请求头的方法
// const setToken = function (token) {
//   axios.defaults.headers.common['token'] = sessionStorage.getItem('token')
// }
// 导出get和post方法
export {
  get,
  post,
  put,
  del
}
