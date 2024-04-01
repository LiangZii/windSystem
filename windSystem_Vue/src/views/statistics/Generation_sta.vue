<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>发电统计</el-breadcrumb-item>
    </el-breadcrumb>
    <br>
    <br>
    <!--列表-->
    <el-table
      :data="tableData"
      border>
      <el-table-column prop="wname" label="风场名称" width="180"></el-table-column>
      <el-table-column prop="number" label="机组数量" width="180"></el-table-column>
      <el-table-column prop="count" label="正常运行设备数量" width="180"></el-table-column>
      <el-table-column prop="allEnergy" label="风场发电量" width="180"></el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
    <div>
        <div ref="myChart" style="width: 400px; height: 400px; background-color: #ffffff; padding: 20px; border-radius: 20px;"></div>
    </div>
  </div>
</template>

<script >
import Pagination from '../../components/Pagination'
import axios from 'axios';
import * as echarts from 'echarts'
export default {
data() {
  return {
    tableData: [],
    echartdata:[],
    loading: false, //是显示加载
    pageparm: {
      currentPage: 1,
      pageSize: 10,
      total: 10
    }
  }
},
// 注册组件
components: {
  Pagination
},
mounted() {
  this.getdata();
},
methods: {
  getdata() {
      var that=this;
      //表格数据
      axios.get('http://localhost:8080/basicstatistics')
      .then(function (response) {
        console.log(response); // 前台调试能够看到获取到数据了
        that.tableData=response.data.data;
        
        for(var i=0;i<that.tableData.length;i++){
          console.log(that.tableData[i].wname)
          var tempdata = {
            name: '',
            value: '',
          }
          tempdata.name=that.tableData[i].wname
          tempdata.value=that.tableData[i].allEnergy
          that.echartdata.push(tempdata)
        }

        that.myChart = echarts.init(that.$refs.myChart)
        that.myChart.setOption(
        {
          title:{
            text: '发电统计',
            x: 'center'
          },
          color: ['#fc8251', '#5470c6', '#91cd77', '#ef6567', '#f9c956', '#75bedc'],
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: [1,2]
          },
          series: [
          {
            name: '风场名称',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: that.echartdata,
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            },
            }
          ]
        }
      );
      console.log(that.tableData);
      }).catch(function(error){
        console.log(error);
      });  
  },
  
  // 分页插件事件
  callFather(parm) {
    this.formInline.page = parm.currentPage
    this.formInline.limit = parm.pageSize
    this.getdata(this.formInline)
  }

}}
</script>

<style scoped>
.user-search {
margin-top: 20px;
}
.userRole {
width: 100%;
}
</style>
