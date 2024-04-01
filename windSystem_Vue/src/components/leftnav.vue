/**
* 左边菜单
*/ 
<template>
  <el-menu default-active="2" :collapse="collapsed" collapse-transition router :default-active="$route.path" unique-opened class="el-menu-vertical-demo" background-color="#334157" text-color="#fff" active-text-color="#ffd04b">
    <div class="logobox">
      <img class="logoimg" src="../assets/img/logo.png" alt="">
    </div>
    <el-submenu v-for="menu in allmenu" :key="menu.menuid" :index="menu.menuname">
      <template slot="title">
        <i class="iconfont" :class="menu.icon"></i>
        <span>{{menu.menuname}}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item v-for="chmenu in menu.menus" :index="'/'+chmenu.url" :key="chmenu.menuid">
          <i class="iconfont" :class="chmenu.icon"></i>
          <span>{{chmenu.menuname}}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>
<script>
import { menu } from '../api/userMG'
export default {
  name: 'leftnav',
  data() {
    return {
      collapsed: false,
      allmenu: []
    }
  },
  // 创建完毕状态(里面是操作)
  created() {
    // 获取图形验证码
    let res = {
      success: true,
      data: [
        {
          menuid: 1,
          icon: 'icon-promotion-manage',
          menuname: '实时监测',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 2,
              icon: 'icon-cat-skuQuery',
              menuname: '风机监测',
              hasThird: 'N',
              url: 'monitor/Monitor',
              menus: null
            }
          ]
        },
        {
          menuid: 33,
          icon: 'li-icon-dingdanguanli',
          menuname: '故障检测',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 34,
              icon: 'icon-order-manage',
              menuname: '人工故障检测',
              hasThird: 'N',
              url: 'fault/Fault',
              menus: null
            }
          ]
        },
        {
          menuid: 71,
          icon: 'li-icon-xitongguanli',
          menuname: '设备维修',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 72,
              icon: 'icon-cus-manage',
              menuname: '故障维修',
              hasThird: 'N',
              url: 'maintain/Maintain',
              menus: null
            },
            {
              menuid: 174,
              icon: 'icon-cms-manage',
              menuname: '预防性检修',
              hasThird: 'N',
              url: 'maintain/Premaintain',
              menus: null
            }
            ]
        },
        {
          menuid: 71,
          icon: 'li-icon-xitongguanli',
          menuname: '数据管理',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 75,
              icon: 'icon-promotion-manage',
              menuname: '机组台账',
              hasThird: 'N',
              url: 'part/Basic',
              menus: null
            },
            {
              menuid: 76,
              icon: 'icon-cms-manage',
              menuname: '新件库存',
              hasThird: 'N',
              url: 'part/Newpart',
              menus: null
            },
            {
              menuid: 77,
              icon: 'icon-cms-manage',
              menuname: '返修件库存',
              hasThird: 'N',
              url: 'part/Oldpart',
              menus: null
            }]
        },
         
        {
          menuid: 128,
          icon: 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91',
          menuname: '分析与统计',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 129,
              icon: 'icon-provider-manage',
              menuname: '故障统计',
              hasThird: 'N',
              url: 'statistics/Fault_sta',
              menus: null
            },
            {
              menuid: 175,
              icon: 'icon-provider-manage',
              menuname: '发电统计',
              hasThird: 'N',
              url: 'statistics/Generation_sta',
              menus: null
            },
            {
              menuid: 175,
              icon: 'icon-provider-manage',
              menuname: '备件消耗统计',
              hasThird: 'N',
              url: 'statistics/Sparepart_sta',
              menus: null
            }
          ]
        },{
        menuid: 128,
          icon: 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91',
          menuname: '系统设置',
          hasThird: null,
          url: null,
          menus: [
            {
              menuid: 129,
              icon: 'icon-provider-manage',
              menuname: '用户权限',
              hasThird: 'N',
              url: 'users/Users',
              menus: null
            },
            {
              menuid: 129,
              icon: 'icon-provider-manage',
              menuname: '人员管理',
              hasThird: 'N',
              url: 'users/Person',
              menus: null
            }

      ]}],
      msg: 'success'
    }
          this.allmenu = res.data
    
    // menu(localStorage.getItem('logintoken'))
    //   .then(res => {
    //     console.log(JSON.stringify(res))
    //     if (res.success) {
    //       this.allmenu = res.data
    //     } else {
    //       this.$message.error(res.msg)
    //       return false
    //     }
    //   })
    //   .catch(err => {
    //     this.$message.error('菜单加载失败，请稍后再试！')
    //   })
    // 监听
    this.$root.Bus.$on('toggle', value => {
      this.collapsed = !value
    })
  }
}
</script>
<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 240px;
  min-height: 400px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  border: none;
  text-align: left;
}
.el-menu-item-group__title {
  padding: 0px;
}
.el-menu-bg {
  background-color: #1f2d3d !important;
}
.el-menu {
  border: none;
}
.logobox {
  height: 40px;
  line-height: 40px;
  color: #9d9d9d;
  font-size: 20px;
  text-align: center;
  padding: 20px 0px;
}
.logoimg {
  height: 40px;
}
</style>