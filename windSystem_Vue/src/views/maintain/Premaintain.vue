<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>预防性检修</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="formInline.uid" placeholder="请输入风机编号"></el-input>
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
      <el-table-column prop="id" label="记录编号" width="150"></el-table-column>
      <el-table-column prop="uid" label="风机编号" width="150"></el-table-column>
      <el-table-column prop="work" label="工作任务" width="150"></el-table-column>
      <el-table-column prop="person" label="人员" width="150"></el-table-column>
      <el-table-column prop="content" label="检修内容" width="150"></el-table-column>
      <el-table-column prop="periodicRepair"  label="定期检修" width="150"></el-table-column>
      <el-table-column prop="time"  label="时间" width="150"></el-table-column>
      <el-table-column prop="comment"  label="备注" width="150"></el-table-column>
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
        <el-form-item prop="id" label="记录编号">
          <el-input size="small" v-model="editForm.id" auto-complete="off" placeholder="记录编号"></el-input>
        </el-form-item>
        <el-form-item prop="uid" label="风机编号">
          <el-input size="small" v-model="editForm.uid" auto-complete="off" placeholder="请输入风机编号"></el-input>
        </el-form-item>
        <el-form-item prop="work" label="工作任务">
          <el-input size="small" v-model="editForm.work" auto-complete="off" placeholder="请输入工作任务"></el-input>
        </el-form-item>
        <el-form-item prop="person" label="人员">
          <el-input size="small" v-model="editForm.person" auto-complete="off" placeholder="请输入人员"></el-input>
        </el-form-item>
        <el-form-item prop="content" label="检修内容">
          <el-input size="small" v-model="editForm.content" auto-complete="off" placeholder="请输入检修内容"></el-input>
        </el-form-item>
        <el-form-item prop="periodicRepair"  label="定期检修">
          <el-input size="small" v-model="editForm.periodicRepair" auto-complete="off" placeholder="请输入定期检修"></el-input>
        </el-form-item>
        <el-form-item prop="time"  label="时间">
          <el-input size="small" v-model="editForm.time" auto-complete="off" placeholder="请输入时间"></el-input>
        </el-form-item>
        <el-form-item prop="comment"  label="备注">
          <el-input size="small" v-model="editForm.comment" auto-complete="off" placeholder="请输入备注"></el-input>
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
import { premaintainList, premaintainSave, premaintainDelete } from '../../api/api'
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
        id:'',
        uid:'',
        work:'',
        person:'',
        content:'',
        periodicRepair:'',
        time:'',
        comment:''
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
      premaintainList(parameter)
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
        axios.get(`http://localhost:8080/preventmaintenances/${that.formInline.uid}`
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
        this.editForm.id = row.id
        this.editForm.uid = row.uid
        this.editForm.work = row.work
        this.editForm.person = row.person
        this.editForm.content = row.content
        this.editForm.periodicRepair = row.periodicRepair
        this.editForm.time = row.time
        this.editForm.comment = row.comment
        this.status = 'update'
      } else {
        this.title = '添加'
        this.editForm.id = ''
        this.editForm.uid = ''
        this.editForm.work = ''
        this.editForm.person = ''
        this.editForm.content = ''
        this.editForm.periodicRepair = ''
        this.editForm.time=''
        this.editForm.comment=''
        this.status = 'add'
      }
    },
    // 编辑、增加页面保存方法
    submitForm(editData) {
      if(this.status === "update"){
        this.$refs[editData].validate(valid => {
        if (valid) {
                var that=this;
        axios.put('http://localhost:8080/preventmaintenances',
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
        axios.post('http://localhost:8080/preventmaintenances',
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
          axios.delete(`http://localhost:8080/preventmaintenances/${row.id}`).then(function (response) {
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
