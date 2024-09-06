<template>
    <div>
        <div class="listContent">
            <el-table :data="tableData" style="width: 100%">
                <el-table-column label="编号" prop="id"></el-table-column>
                <el-table-column label="药品名称" prop="drugName"></el-table-column>
                <el-table-column label="价格" prop="price"></el-table-column>
                <el-table-column label="库存" prop="inventory"></el-table-column>
                <el-table-column label="药品照片">
                    <template slot-scope="scope">
                        <el-image
                            style="width: 200px"
                            :src="scope.row.imageUrl"
                            :alt="scope.row.drugName"
                            fit="cover">
                        </el-image>
                    </template>
                </el-table-column>
                <el-table-column align="right">
                    <template slot="header" slot-scope="scope">
                        <el-input v-model="search" size="mini" placeholder="输入关键字搜索" @input="handleSearch" />
                    </template>
                </el-table-column>
            </el-table>
            <Pagination :total="total" :pageSize="pageSize" @getPagination="getPagination"></Pagination>
        </div>
    </div>
</template>

<script>
import Pagination from "@/components/pagination/Pagination.vue";
import qs from 'qs';

export default {
    components:{
        Pagination
    },
    data() {
      return {
        pageSize: 10,
        total: 100,
        pageNum: 1, // 当前页
        tableData: [], // 初始化为空，数据将从后台获取
        search: '' // 搜索条件
      }
    },
    methods: {
      // 加载药品列表信息
      loadStorageList() {
        this.$axios.post('/admin/showmedi', qs.stringify({
            page: this.pageNum,
            pageSize: this.pageSize
        })).then(res => res.data)
        .then(res => {
            if(res.code == 1) {
                this.tableData = res.data.records.map(record => ({
                    id: record.id,
                    drugName: record.name,
                    price: record.price,
                    inventory: record.stock,
                    imageUrl: record.image
                }));
                this.total = res.data.total;
            } else {
               this.$message({
                   message: '列表查询失败！',
                   type: 'error'
               });
            }
        });
      },
      // 搜索药品
      handleSearch: _.debounce(function () {
        this.pageNum = 1;
        if (this.search.trim() === '') {
          // 如果搜索框为空，则加载所有药品
          this.loadStorageList();
        } else {
          this.$axios.post('/admin/searchmedi', qs.stringify({
              medicationName: this.search
          })).then(res => res.data)
          .then(res => {
              if(res.code == 1) {
                  this.tableData = res.data.medications.map(medication => ({
                      id: medication.id,
                      drugName: medication.name,
                      price: medication.price,
                      inventory: medication.stock,
                      imageUrl: medication.image
                  }));
                  this.total = res.data.total;
              } else {
                 this.$message({
                     message: '搜索失败！',
                     type: 'error'
                 });
              }
          });
        }
      }, 300),
      // 获取分页页码
      getPagination(page) {
        this.pageNum = page;
        this.loadStorageList();
      }
    },
    beforeMount() {
      this.loadStorageList();
    }
}
</script>

<style lang="less" scoped>
.header{
    background: #fff;
    padding: 10px;
    margin-bottom: 20px;
    .group{
        border: 1px solid #eee;
        padding: 8px;
    }
}
.listContent{
    background: pink;
    height: 100px;
    .active-header{
        color: #333;
        text-align: center;
    }
}
.el-form-item{
    margin-bottom: 10px;
}
.el-input__inner{
    height:35px;
}
</style>
