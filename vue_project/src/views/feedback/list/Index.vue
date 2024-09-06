<template>  
  <div>  
    <el-table :data="tableData" style="width: 100%">  
      <el-table-column type="expand">  
        <template slot-scope="props">  
          <el-form label-position="left" inline class="demo-table-expand">  
            <el-form-item label="反馈内容">  
              <span>{{ props.row.content }}</span>  
            </el-form-item>  
            <el-form-item label="反馈照片">  
              <a :href="props.row.photoUrl" download>点击下载照片</a>  
            </el-form-item>  
          </el-form>  
        </template>  
      </el-table-column>  

      <el-table-column label="反馈用户" prop="name"></el-table-column>  
      <el-table-column label="反馈时间" prop="time"></el-table-column>  
      <el-table-column label="电话号码" prop="phone"></el-table-column>  
      <el-table-column label="类型" prop="kind"></el-table-column>  
    </el-table>  
    <Pagination :total="total" :pageSize="pageSize" @getPagination="getPagination"></Pagination>  
  </div>  
</template>  

<script>  
import Pagination from "@/components/pagination/Pagination.vue";  
import axios from 'axios';  

export default {  
  components: {  
    Pagination  
  },  
  data() {  
    return {  
      pageSize: 10,  
      total: 0,  
      pageNum: 1,  
      tableData: [] // 初始为空数组，数据将由后端返回  
    }  
  },  
  created() {  
    this.$httpUrl = 'your_api_base_url';  // 请替换为你的API基础URL
    this.loadUserList(1);  // 页面加载时请求第一页的数据
  },  
  methods: {  
    getPagination(page) {  
      this.loadUserList(page);  
    },  
    loadUserList(page) {  
      this.pageNum = page;

      // 使用 URLSearchParams 发送 form 表单格式的数据
      const params = new URLSearchParams();
      params.append('page', this.pageNum);
      params.append('pageSize', this.pageSize);

      this.$axios.post('/admin/showfeedback', params)
        .then(res => res.data)
        .then(res => {  
          if (res.code === 1) {  
            // 将后端返回的数据映射到前端展示的数据结构
            this.tableData = res.data.records.map(item => ({
              name: item.patientName,
              time: item.createTime,
              phone: item.phone,
              kind: item.type,
              content: item.content,
              photoUrl: item.image || "https://web-huang-ruijie.oss-cn-shanghai.aliyuncs.com/e7195fae-ca61-449f-8c47-7bfe364c6a6b.png" // 默认照片链接
            }));
            this.total = res.data.total;  
            this.pageSize = this.pageSize;  // 保持与当前pageSize一致
          } else {  
            this.$message({  
              message: '列表查询失败！',  
              type: 'error'  
            });  
          }  
        }).catch(error => {
          console.error(error);
          this.$message({
            message: '请求失败，请检查网络或后端服务！',
            type: 'error'
          });
        });
    }  
  }  
}  
</script>  

<style>  
.demo-table-expand {  
  font-size: 0;  
}  
.demo-table-expand label {  
  width: 90px;  
  color: #99a9bf;  
}  
.demo-table-expand .el-form-item {  
  margin-left: 15px;  
  margin-bottom: 0;  
  width: 100%;  
}  
</style>
