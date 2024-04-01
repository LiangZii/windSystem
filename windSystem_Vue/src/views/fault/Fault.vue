<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>人工故障检测</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="formInline.uid" placeholder="输入风机编号"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search()">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit2()">添加</el-button>
      </el-form-item>
    </el-form>
    <!--列表-->
    <el-table
      :data="tableData"
      border>
      <el-table-column prop="uid" label="风机编号" width="150"></el-table-column>
      <el-table-column prop="fid" label="故障编号" width="150"></el-table-column>
      <el-table-column prop="fname" label="故障名称" width="150"></el-table-column>
      <el-table-column prop="fcode" label="故障代码" width="150"></el-table-column>
      <el-table-column prop="description" label="故障描述" width="150"></el-table-column>
      <el-table-column prop="category" label="故障类别" width="150"></el-table-column>
      <el-table-column prop="reportTime" label="故障报出时间" width="150"></el-table-column>
      <el-table-column prop="handleTime" label="开始处理时间" width="150"></el-table-column>
      <el-table-column prop="resetTime" label="复位运行时间" width="150"></el-table-column>
      <el-table-column prop="firstPosition" label="故障位置一级" width="150"></el-table-column>
      <el-table-column prop="secondPosition" label="故障位置二级" width="150"></el-table-column>
      <el-table-column prop="thirdPosition" label="故障位置三级" width="150"></el-table-column>
      <el-table-column prop="checkItem" label="排查项目" width="150"></el-table-column>
      <el-table-column prop="reason" label="故障原因" width="150"></el-table-column>
      <el-table-column align="center" label="操作" min-width="300">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit1(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
    <el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @click="closeDialog">
      <el-form label-width="120px" :model="editForm"  ref="editForm">
        <el-form-item prop="uid" label="风机编号">
          <el-input size="small" v-model="editForm.uid" auto-complete="off" placeholder="请输入风机编号"></el-input>
        </el-form-item>
        <el-form-item prop="fid" label="故障编号">
          <el-input size="small" v-model="editForm.fid" auto-complete="off" placeholder="请输入故障编号"></el-input>
        </el-form-item>
        <el-form-item prop="fname" label="故障名称">
          <el-input size="small" v-model="editForm.fname" auto-complete="off" placeholder="请输入故障名称"></el-input>
        </el-form-item>
        <el-form-item prop="fname" label="故障代码">
          <el-input size="small" v-model="editForm.fcode" auto-complete="off" placeholder="请输入故障代码"></el-input>
        </el-form-item>
        <el-form-item prop="description" label="故障描述">
          <el-input size="small" v-model="editForm.description" auto-complete="off" placeholder="请输入故障描述"></el-input>
        </el-form-item>
        <el-form-item prop="category" label="故障类别">
          <el-input size="small" v-model="editForm.category" auto-complete="off" placeholder="请输入故障类别"></el-input>
        </el-form-item>
        <el-form-item prop="reportTime" label="故障报出时间">
          <el-input size="small" v-model="editForm.reportTime" auto-complete="off" placeholder="请输入故障报出时间"></el-input>
        </el-form-item>
        <el-form-item prop="handleTime" label="开始处理时间" >
          <el-input size="small" v-model="editForm.handleTime" auto-complete="off" placeholder="请输入开始处理时间"></el-input>
        </el-form-item>
        <el-form-item prop="resetTime" label="复位运行时间" >
          <el-input size="small" v-model="editForm.resetTime" auto-complete="off" placeholder="请输入复位运行时间"></el-input>
        </el-form-item>
        <el-form-item  prop="firstPosition" label="故障位置一级" >
          <el-input size="small" v-model="editForm.firstPosition" auto-complete="off" placeholder="请输入故障位置一级"></el-input>
        </el-form-item>
        <el-form-item prop="secondPosition" label="故障位置二级" >
          <el-input size="small" v-model="editForm.secondPosition" auto-complete="off" placeholder="请输入故障位置二级"></el-input>
        </el-form-item>
        <el-form-item prop="thirdPosition" label="故障位置三级" >
          <el-input size="small" v-model="editForm.thirdPosition" auto-complete="off" placeholder="请输入故障位置三级"></el-input>
        </el-form-item>
        <el-form-item prop="checkItem" label="排查项目" >
          <el-input size="small" v-model="editForm.checkItem" auto-complete="off" placeholder="请输入排查项目"></el-input>
        </el-form-item>
        <el-form-item prop="reason" label="故障原因" >
          <el-input size="small" v-model="editForm.reason" auto-complete="off" placeholder="请输入故障原因"></el-input>
        </el-form-item>
        
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script >
import { faultSearch,faultList, faultSave, faultDelete } from '../../api/api'
import Pagination from '../../components/Pagination'
import axios from 'axios';

export default {
  data() {
    return {
      tableData: [],
      loading: false, //是显示加载
      editFormVisible: false, //控制编辑页面显示与隐藏
      title: '添加',
      status: '',
      editForm: {
        uid:'',
        fid:'',
        fname:'',
        fcode:'',
        description:'',
        category:'',
        reportTime:'',
        handleTime:'',
        resetTime:'',
        firstPosition:'',
        secondPosition:'',
        thirdPosition:'',
        checkItem:'',
        reason:'',
        token: localStorage.getItem('logintoken')
      },
      formInline: {
        uid:''
      },
      
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
  created() {
    this.getdata(this.formInline);
    //this.search();
  },
 
  methods: {
    getdata(parameter) {
     faultList(parameter)
        .then(res => {
          this.loading = false
          if (res.success == false) {
          this.$message({
              type: 'info',
              message: res.msg
            })
          } else {
            this.tableData = res.data
          }
        })
        .catch(err => {
          this.loading = false
          this.$message.error('菜单加载失败，请稍后再试！')
        }) 
   
        },
    
    // 分页插件事件
    callFather(parm) {
      this.formInline.page = parm.currentPage
      this.formInline.limit = parm.pageSize
      this.getdata(this.formInline)
    },
    // 搜索事件
    // 搜索
    search() {
      
      
      var that=this;
        axios.get('http://localhost:8080/faultmanages/select',
        {params:{
        id:that.formInline.uid
        }})
        .then(function (response) {
          console.log(response); // 前台调试能够看到获取到数据了
          that.tableData=response.data.data;
          console.log(that.tableData);
         
        }).catch(function(error){
          console.log(error);
        });   
    },
  
    //显示编辑界面
    handleEdit1: function(index, row) {
      this.editFormVisible = true
    
        this.title = '修改'
        this.editForm.uid = row.uid
        this.editForm.fid = row.fid
        this.editForm.fname = row.fname
        this.editForm.fcode = row.fcode
        this.editForm.description = row.description
        this.editForm.category = row.category
        this.editForm.reportTime = row.reportTime
        this.editForm.handleTime = row.handleTime
        this.editForm.resetTime = row.resetTime
        this.editForm.firstPosition = row.firstPosition
        this.editForm.secondPosition = row.secondPosition
        this.editForm.thirdPosition = row.thirdPosition
        this.editForm.checkItem = row.checkItem
        this.editForm.reason = row.reason
        this.status = 'update'
    },
    handleEdit2: function(index, row) {
      this.editFormVisible = true
        this.title = '添加'
        this.editForm.uid = ''
        this.editForm.fid = ''
        this.editForm.fname = ''
        this.editForm.fcode = ''
        this.editForm.description = ''
        this.editForm.category = ''
        this.editForm.reportTime = ''
        this.editForm.handleTime = ''
        this.editForm.resetTime = ''
        this.editForm.firstPosition = ''
        this.editForm.secondPosition = ''
        this.editForm.thirdPosition = ''
        this.editForm.checkItem = ''
        this.editForm.reason = ''
        this.status = 'add'
      },
    // 编辑、增加页面保存方法
    submitForm(editData) {
      if(this.status === "update"){
        this.$refs[editData].validate(valid => {
        if (valid) {
                var that=this;
        axios.put('http://localhost:8080/faultmanages',
        this.editForm)
        .then(function (response) {
          console.log(response); // 前台调试能够看到获取到数据了
          that.tableData=response.data.data
          that.$message({
          message: '修改成功',
          type: 'success'
          });
          that.getdata();
        })
        .catch(function (error) {
          console.log(error);
          that.$message.error('修改失败');
        });
        this.dialogFormVisible1=false;
      }
          else {
          return false
        }
      })
      }
      if(this.status === "add"){
        this.$refs[editData].validate(valid => {
        if (valid) { var that=this;
        axios.post('http://localhost:8080/faultmanages',
        this.editForm)
        .then(function (response) {
          console.log(response); // 前台调试能够看到获取到数据了
          that.tableData=response.data.data
          that.$message({
          message: '添加成功',
          type: 'success'
          });
          that.getdata();
        })
        .catch(function (error) {
          console.log(error);
          that.$message.error('添加失败');
        });
        this.dialogFormVisible1=false;
        }
        else {
          return false
        }
    })
      }
     
    },

    // 删除
    handleDelete(index, row) {
      var that=this
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
         
            axios.delete(`http://localhost:8080/faultmanages/${row.fid}`).then(function (response) {
          console.log(response); // 前台调试能够看到获取到数据了
          let value=response.data.data;
          console.log(that.tableData);
          that.$message({
            message:'删除成功',
            type:'success'
          });
          that.getdata();
      
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    })},
    
    // 关闭编辑、增加弹出框
    closeDialog() {
      this.editFormVisible = false
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
