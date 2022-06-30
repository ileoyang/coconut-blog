import Vue from 'vue';
import axios from 'axios';
import Element from 'element-ui';
import store from '../store';

axios.defaults.baseURL = 'http://localhost:8000';

// request interceptor
axios.interceptors.request.use((config) => {
  if (store.getters.jwt) {
    config.headers['Authorization'] = store.getters.jwt;
  }
  return config;
});

// response interceptor
axios.interceptors.response.use(
  (response) => {
    const data = response.data;
    if (data.code == 200) {
      return data;
    }
    Element.Message({
      message: data.message,
      type: 'error',
      duration: 2000,
    });
    return Promise.reject(data.message);
  },
  (error) => {
    return Promise.reject(error);
  }
);

Vue.prototype.$http = axios;
