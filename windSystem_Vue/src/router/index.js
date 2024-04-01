// 导入组件
import Vue from 'vue';
import Router from 'vue-router';
// 登录
import login from '@/views/login';
// 首页
import index from '@/views/index';
// 风场监测
import Monitor from '@/views/monitor/Monitor';
// 人工故障检测
import Fault from '@/views/fault/Fault';
// 故障维修
import Maintain from '@/views/maintain/Maintain';
// 预防性检修
import Premaintain from '@/views/maintain/Premaintain';
// 机组台账
import Basic from '@/views/part/Basic';
// 新件库存
import Newpart from '@/views/part/Newpart';
// 返修件件库存
import Oldpart from '@/views/part/Oldpart';
// 故障统计
import Fault_sta from '@/views/statistics/Fault_sta';
// 发电统计
import Generation_sta from '@/views/statistics/Generation_sta';
// 维修统计
import Sparepart_sta from '@/views/statistics/Sparepart_sta';
// 用户管理
import Users from '@/views/users/Users';
import Person from '@/views/users/Person';

// 启用路由
Vue.use(Router);

// 导出路由 
export default new Router({
    routes: [{
        path: '/',
        name: '',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false,
            requireAuth2:false//是否需要验证管理员或者普通工作人员
        }
    }, {
        path: '/login',
        name: '登录',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false,
            requireAuth2:false//是否需要验证管理员或者普通工作人员
        }
    }, {
        path: '/index',
        name: '首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [{
            path: '/monitor/Monitor',
            name: '风场监测',
            component: Monitor,
            meta: {
                requireAuth: true,
                requireAuth2:false//是否需要验证管理员或者普通工作人员
            }
        }, 
        {
            path: '/fault/Fault',
            name: '人工故障检测',
            component: Fault,
            meta: {
                requireAuth: true,
                requireAuth2:false//是否需要验证管理员或者普通工作人员
            }
        },
        {
            path: '/maintain/Maintain',
            name: '故障维修',
            component: Maintain,    
            meta: {
                requireAuth: true,
                requireAuth2:false//是否需要验证管理员或者普通工作人员
            }
        }, {
            path: '/maintain/Premaintain',
            name: '预防性检修',
            component: Premaintain,    
            meta: {
                requireAuth: true,
                requireAuth2:false//是否需要验证管理员或者普通工作人员
            }
        }, {
            path: '/part/Basic',
            name: '机组台账',
            component: Basic,
            meta: {
                requireAuth: true,
                requireAuth2:false//是否需要验证管理员或者普通工作人员
            }
        }, {
            path: '/part/Newpart',
            name: '新件库存',
            component: Newpart,
            meta: {
                requireAuth: true,
                requireAuth2:false//是否需要验证管理员或者普通工作人员
            }
        },
        {
            path: '/part/Oldpart',
            name: '返修件库存',
            component: Oldpart,
            meta: {
                requireAuth: true,
                requireAuth2:false//是否需要验证管理员或者普通工作人员
            }
        }, {
            path: '/statistics/Fault_sta',
            name: '故障统计',
            component: Fault_sta,
            meta: {
                requireAuth: true,
                requireAuth2:false//是否需要验证管理员或者普通工作人员
            }
        }, {
            path: '/statistics/Generation_sta',
            name: '发电统计',
            component: Generation_sta,
            meta: {
                requireAuth: true,
                requireAuth2:false//是否需要验证管理员或者普通工作人员
            }
        }, {
            path: '/statistics/Sparepart_sta',
            name: '备件消耗统计',
            component: Sparepart_sta,
            meta: {
                requireAuth: true,
                requireAuth2:false//是否需要验证管理员或者普通工作人员
            }
        },  {
            path: '/users/Users',
            name: '用户权限',
            component: Users,
            meta: {
                requireAuth: true,
                requireAuth2:true//是否需要验证管理员或者普通工作人员
            }
        },
            {
                path: '/users/Person',
                name: '人员管理',
                component: Person,
                meta: {
                    requireAuth: true,
                    requireAuth2:true//是否需要验证管理员或者普通工作人员
                }
        }]
    }]
})