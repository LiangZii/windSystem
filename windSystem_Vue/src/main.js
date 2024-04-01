// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
// 引入element UI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App';
// 引入路由
import router from './router';
// 引入状态管理
import store from './vuex/store';
// 引入icon
import './assets/icon/iconfont.css'
// 
import axios from 'axios';

Vue.prototype.$axios = axios;
//引入echarts
import echarts from 'echarts'
//vue全局注入echarts
Vue.prototype.$echarts = echarts;

Vue.config.productionTip = false;

// 使用element UI
Vue.use(ElementUI);
// 过滤器
import * as custom from './utils/util'

Object.keys(custom).forEach(key => {
    Vue.filter(key, custom[key])
})

// 路由拦截器
router.beforeEach((to, from, next) => {
    if (to.matched.length != 0) {
        if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
            if (Boolean(localStorage.getItem("userInfo"))) { // 通过vuex state获取当前的user是否存在
                if(to.meta.requireAuth2){
                    console.log(localStorage.getItem("isAdminInfo"))
                    if(Boolean(localStorage.getItem("isAdminInfo"))){
                        
                        next();
                    }else{
                        next({
                            path: '/monitor/Monitor'
                        })
                    }
                }else{
                    next();
                }
            } else {
                next({
                    path: '/login',
                    query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
                })
            }
            //next();
        } else {
            if (Boolean(localStorage.getItem("userInfo"))) { // 判断是否登录
                console.log(localStorage.getItem("userInfo"))
                if (to.path != "/" && to.path != "/login") { //判断是否要跳到登录界面
                    next();
                } else {
                    next()
                }
            } else {
                console.log(5)
                next();
            }
        }
    } else {
        next({
            path: '/login',
            query: { redirect: to.fullPath } // 将跳转的路由path作为参数，登录成功后跳转到该路由
        })
    }
})

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    store, //使用store vuex状态管理
    components: { App },
    template: '<App/>',
    data: {
        // 空的实例放到根组件下，所有的子组件都能调用
        Bus: new Vue()
    }

})