// src/api/axios.js
import axios from 'axios';
import router from '@/router'
// 创建axios实例
const instance = axios.create({
    baseURL: 'http://192.168.1.191:8082/',
    timeout: 5000,
});

// 添加响应拦截器
instance.interceptors.response.use(response => {
    if (response.data.msg === 'NOT_LOGIN'){
        router.push("/login")
    }
    return response;
}, error => {
    // 处理请求错误
    return Promise.reject(error);
});
export default instance;