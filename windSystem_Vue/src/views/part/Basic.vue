<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>机组台账</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="formInline.wname" placeholder="输入风场名称"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search()">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button>
      </el-form-item>
    </el-form>
    <!--列表-->
    <el-table
      :data="tableData"
      border>
      <el-table-column prop="wid" label="风场编号" width="150"></el-table-column>
      <el-table-column prop="uid" label="机组编号" width="150"></el-table-column>
      <el-table-column prop="manufacturer" label="生产厂商" width="150"></el-table-column>
      <el-table-column prop="modle" label="设备型号" width="150"></el-table-column>
      <el-table-column prop="date" label="安装日期" width="150"></el-table-column>
      <el-table-column prop="time" label="使用年限" width="150"></el-table-column>
      <el-table-column prop="state" label="设备状态" width="150"></el-table-column>
      <el-table-column prop="unitEnergy" label="机组发电量" width="150"></el-table-column>
      <el-table-column align="center" label="操作" min-width="300">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
    <el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @click="closeDialog">
      <el-form label-width="120px" :model="editForm"  ref="editForm">
        <el-form-item  prop="wid" label="风场编号">
          <el-input size="small" v-model="editForm.wid" auto-complete="off" placeholder="请输入风场编号"></el-input>
        </el-form-item>
        <el-form-item prop="uid" label="机组编号">
          <el-input size="small" v-model="editForm.uid" auto-complete="off" placeholder="请输入机组编号"></el-input>
        </el-form-item>
        <el-form-item prop="manufacturer" label="生产厂商">
          <el-input size="small" v-model="editForm.manufacturer" auto-complete="off" placeholder="请输入生产厂商"></el-input>
        </el-form-item>
        <el-form-item prop="modle" label="设备型号">
          <el-input size="small" v-model="editForm.modle" auto-complete="off" placeholder="请输入设备型号"></el-input>
        </el-form-item>
        <el-form-item prop="date" label="安装日期">
          <el-input size="small" v-model="editForm.date" auto-complete="off" placeholder="请输入安装日期"></el-input>
        </el-form-item>
        <el-form-item prop="time" label="使用年限">
          <el-input size="small" v-model="editForm.time" auto-complete="off" placeholder="请输入使用年限"></el-input>
        </el-form-item>
        <el-form-item prop="state" label="设备状态">
          <el-input size="small" v-model="editForm.state" auto-complete="off" placeholder="请输入设备状态"></el-input>
        </el-form-item>
        <el-form-item prop="unitEnergy" label="机组发电量">
          <el-input size="small" v-model="editForm.unitEnergy" auto-complete="off" placeholder="请输入机组发电量"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { basicList, basicSave, basicDelete } from '../../api/api'
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
        wid:'',
        uid:'',
        manufacturer:'',
        modle:'',
        date:'',
        time:'',
        state:'',
        unitEnergy:'',
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
    this.getdata(this.formInline)
  },
  methods: {
    getdata(parameter) {
      basicList(parameter)
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
    search() {
      var that=this;
        axios.get(`http://localhost:8080/basicdatas/${that.formInline.wname}`
        )
        .then(function (response) {
 
          console.log(response); // 前台调试能够看到获取到数据了
          that.tableData=response.data.data;
          console.log(that.tableData);
         
        }).catch(function(error){
          console.log(error);
        });   
 
    },

    //显示编辑界面
    handleEdit: function(index, row) {
      this.editFormVisible = true
      if (row != undefined && row != 'undefined') {
        this.title = '修改'
        this.editForm.wid = row.wid
        this.editForm.uid = row.uid
        this.editForm.manufacturer = row.manufacturer
        this.editForm.modle = row.modle
        this.editForm.date = row.date
        this.editForm.time = row.time
        this.editForm.state = row.state
        this.editForm.unitEnergy = row.unitEnergy
        this.status = 'update'
      } else {
        this.title = '添加'
        this.editForm.wid = ''
        this.editForm.uid = ''
        this.editForm.manufacturer = ''
        this.editForm.modle = ''
        this.editForm.date = ''
        this.editForm.time = ''
        this.editForm.state = ''
        this.editForm.unitEnergy = ''
        this.status = 'add'
      }
    },
    // 编辑、增加页面保存方法
    submitForm(editData) {
      if(this.status === "update"){
        this.$refs[editData].validate(valid => {
        if (valid) {
                var that=this;
        axios.put('http://localhost:8080/basicdatas',
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
        axios.post('http://localhost:8080/basicdatas',
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
    }},

    // 删除
    handleDelete(index, row) {
      var that=this;
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          axios.delete(`http://localhost:8080/basicdatas/${row.uid}`).then(function (response) {
          console.log(response); // 前台调试能够看到获取到数据了
          let value=response.data.data;
          console.log(that.tableData);
          that.$message({
            message:'删除成功',
            type:'success'
          });
          that.getdata();
      
        })
        .catch((error) => {
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
