<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>故障维修</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input size="small" v-model="formInline.repairman" placeholder="请输入维修员姓名"></el-input>
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
      <el-table-column prop="fid"  label="故障编号" width="150"></el-table-column>
      <el-table-column prop="grade"  label="维修等级" width="150"></el-table-column>
      <el-table-column prop="category" label="维修类别" width="150"></el-table-column>
      <el-table-column prop="mid" label="工作票编号" width="150"></el-table-column>
      <el-table-column prop="action" label="维修动作" width="150"></el-table-column>
      <el-table-column prop="object" label="维修对象" width="150"></el-table-column>
      <el-table-column prop="date" label="维修时间" width="150"></el-table-column>
      <el-table-column prop="redate"  label="下次维修时间" width="150"></el-table-column>
      <el-table-column prop="repairman"  label="维修员" width="150"></el-table-column>
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
        <el-form-item prop="fid"  label="故障编号">
          <el-input size="small" v-model="editForm.fid" auto-complete="off" placeholder="请输入故障编号"></el-input>
        </el-form-item>
        <el-form-item prop="grade"  label="维修等级">
          <el-input size="small" v-model="editForm.grade" auto-complete="off" placeholder="请输入维修等级"></el-input>
        </el-form-item>
        <el-form-item prop="category" label="维修类别">
          <el-input size="small" v-model="editForm.category" auto-complete="off" placeholder="请输入维修类别"></el-input>
        </el-form-item>
        <el-form-item prop="mid" label="工作票编号">
          <el-input size="small" v-model="editForm.mid" auto-complete="off" placeholder="请输入工作票编号"></el-input>
        </el-form-item>
        <el-form-item prop="action" label="维修动作">
          <el-input size="small" v-model="editForm.action" auto-complete="off" placeholder="请输入维修动作"></el-input>
        </el-form-item>
        <el-form-item prop="object" label="维修对象">
          <el-input size="small" v-model="editForm.object" auto-complete="off" placeholder="请输入维修对象"></el-input>
        </el-form-item>
        <el-form-item prop="date" label="维修时间">
          <el-input size="small" v-model="editForm.date" auto-complete="off" placeholder="请输入维修时间"></el-input>
        </el-form-item>
        <el-form-item prop="redate"  label="下次维修时间">
          <el-input size="small" v-model="editForm.redate" auto-complete="off" placeholder="请输入下次维修时间"></el-input>
        </el-form-item>
        <el-form-item prop="repairman"  label="维修员">
          <el-input size="small" v-model="editForm.repairman" auto-complete="off" placeholder="请输入维修员"></el-input>
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
import { maintainList, maintainSave, maintainDelete } from '../../api/api'
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
        fid:'',
        grade:'',
        category:'',
        mid:'',
        action:'',
        object:'',
        date:'',
        redate:'',
        repairman:''
      },
      formInline: {
        repairman:''
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
      maintainList(parameter)
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
        axios.get('http://localhost:8080/maintenances/repairman',
        {params:{
        name:that.formInline.repairman
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
    handleEdit: function(index, row) {
      this.editFormVisible = true
      if (row != undefined && row != 'undefined') {
        this.title = '修改'
        this.editForm.fid = row.fid 
        this.editForm.grade = row.grade
        this.editForm.category = row.category
        this.editForm.mid = row.mid
        this.editForm.action = row.action
        this.editForm.object = row.object
        this.editForm.date = row.date
        this.editForm.redate = row.redate
        this.editForm.repairman = row.repairman
        this.status = 'update'
      } else {
        this.title = '添加'
        this.editForm.uid =  ''
        this.editForm.fid = ''
        this.editForm.grade = ''
        this.editForm.category =  ''
        this.editForm.mid = ''
        this.editForm.action =  ''
        this.editForm.object =  ''
        this.editForm.date = ''
        this.editForm.redate = ''
        this.editForm.repairman =  ''
        this.status = 'add'
      }
    },
    // 编辑、增加页面保存方法
    submitForm(editData) {
      if(this.status === "update"){
        this.$refs[editData].validate(valid => {
        if (valid) {
                var that=this;
        axios.put('http://localhost:8080/maintenances',
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
        axios.post('http://localhost:8080/maintenances',
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
    handleDelete(index, row) {var that=this
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          /* faultDelete(row.uid)
            .then(res => {
              if (res.success) {
                this.$message({
                  type: 'success',
                  message: '已删除!'
                })
                this.getdata(this.formInline)
              } else {
                this.$message({
                  type: 'info',
                  message: res.msg
                })
              }
            })
            .catch(err => {
              this.loading = false
              this.$message.error('删除失败，请稍后再试！')
            }) */
            axios.delete(`http://localhost:8080/maintenances/${row.mid}`).then(function (response) {
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
