import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
// 登录验证
export default new Vuex.Store({
    state: {
        user: false,
        isAdmin:false
    },
    mutations: {
        // 登录
        login(state, user) {
            state.user = user;
            localStorage.setItem("userInfo", user);
            console.log()
        },
        // 退出
        logout(state, user) {
            state.user = "";
            localStorage.setItem("userInfo", "");
        },
        saveIsAdmin(state, isAdmin) {
            state.isAdmin = isAdmin;
            localStorage.setItem("isAdminInfo",isAdmin);
            console.log("isAdminInfo1111111111111111111111"+localStorage.getItem("isAdminInfo"))
            console.log("isAdmin"+isAdmin)
        }
    }
})