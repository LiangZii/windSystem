<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>新件库存</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="formInline.nid" placeholder="输入新件编号"></el-input>
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
      <el-table-column prop="nid" label="新件编号" width="150"></el-table-column>
      <el-table-column prop="materialCode" label="厂家物料编码" width="150"></el-table-column>
      <el-table-column prop="spacode" label="SPA物料编码" width="150"></el-table-column>
      <el-table-column prop="stationCode" label="场站物料代码" width="150"></el-table-column>
      <el-table-column prop="nname" label="新件名称" width="150"></el-table-column>
      <el-table-column prop="modle" label="规格型号" width="150"></el-table-column>
      <el-table-column prop="num" label="数量" width="150"></el-table-column>
      <el-table-column prop="unit" label="单位" width="150"></el-table-column>
      <el-table-column prop="unitprice" label="单价" width="150"></el-table-column>
      <el-table-column prop="intime" label="入库时间" width="150"></el-table-column>
      <el-table-column prop="outtime" label="出库时间" width="150"></el-table-column>
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
        <el-form-item  prop="nid" label="新件编号">
          <el-input size="small" v-model="editForm.nid" auto-complete="off" placeholder="请输入新件编号"></el-input>
        </el-form-item>
        <el-form-item prop="materialCode" label="厂家物料编码">
          <el-input size="small" v-model="editForm.materialCode" auto-complete="off" placeholder="请输入厂家物料编码"></el-input>
        </el-form-item>
        <el-form-item prop="spacode" label="SPA物料编码" >
          <el-input size="small" v-model="editForm.spacode" auto-complete="off" placeholder="请输入SPA物料编码"></el-input>
        </el-form-item>
        <el-form-item prop="stationCode" label="场站物料代码" >
          <el-input size="small" v-model="editForm.stationCode" auto-complete="off" placeholder="请输入场站物料代码"></el-input>
        </el-form-item>
        <el-form-item prop="nname" label="新件名称">
          <el-input size="small" v-model="editForm.nname" auto-complete="off" placeholder="请输入新件名称"></el-input>
        </el-form-item>
        <el-form-item prop="modle" label="规格型号">
          <el-input size="small" v-model="editForm.modle" auto-complete="off" placeholder="请输入规格型号"></el-input>
        </el-form-item>
        <el-form-item prop="num" label="数量">
          <el-input size="small" v-model="editForm.num" auto-complete="off" placeholder="请输入数量"></el-input>
        </el-form-item>
        <el-form-item prop="unit" label="单位">
          <el-input size="small" v-model="editForm.unit" auto-complete="off" placeholder="请输入单位"></el-input>
        </el-form-item>
        <el-form-item prop="unitprice" label="单价">
          <el-input size="small" v-model="editForm.unitprice" auto-complete="off" placeholder="请输入单价"></el-input>
        </el-form-item>
        <el-form-item prop="intime" label="入库时间">
          <el-input size="small" v-model="editForm.intime" auto-complete="off" placeholder="请输入入库时间"></el-input>
        </el-form-item>
        <el-form-item prop="outtime" label="出库时间">
          <el-input size="small" v-model="editForm.outtime" auto-complete="off" placeholder="请输入出库时间"></el-input>
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
import { newpartList, newpartSave, newpartDelete } from '../../api/api'
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
        nid:'',
        materialCode:'',
        spacode:'',
        stationCode:'',
	      nname:'',
        modle:'',
        num:'',
        unit:'',
        unitprice:'',
        intime:'',
        outtime:'',
        status: '',
        token: localStorage.getItem('logintoken')
      },
      formInline: {
        nid:''
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
      newpartList(parameter)
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
        axios.get(`http://localhost:8080/newpartsmanages/${that.formInline.nid}`
        )
        .then(function (response) {
          that.tableData=[];
          console.log(response); // 前台调试能够看到获取到数据了 newpartsmanages
          that.tableData.push(response.data.data);
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
        this.editForm.nid = row.nid
        this.editForm.materialCode = row.materialCode
        this.editForm.spacode = row.spacode
        this.editForm.stationCode = row.stationCode
	      this.editForm.nname = row.nname
        this.editForm.modle = row.modle
        this.editForm.num = row.num
        this.editForm.unit = row.unit
        this.editForm.unitprice = row.unitprice
        this.editForm.intime = row.intime
        this.editForm.outtime = row.outtime
        this.status = 'update'
      } else {
        this.title = '添加'
        this.editForm.nid = ''
        this.editForm.materialCode = ''
        this.editForm.spacode = ''
        this.editForm.stationCode = ''
	      this.editForm.nname = ''
        this.editForm.modle = ''
        this.editForm.num = ''
        this.editForm.unit = ''
        this.editForm.unitprice = ''
        this.editForm.intime = ''
        this.editForm.outtime = ''
        this.status = 'add'
      }
    },
    // 编辑、增加页面保存方法
    submitForm(editData) {
      if(this.status === "update"){
        this.$refs[editData].validate(valid => {
        if (valid) {
                var that=this;
        axios.put('http://localhost:8080/newpartsmanages',
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
        axios.post('http://localhost:8080/newpartsmanages',
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
      var that=this
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          axios.delete(`http://localhost:8080/newpartsmanages/${row.nid}`).then(function (response) {
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
