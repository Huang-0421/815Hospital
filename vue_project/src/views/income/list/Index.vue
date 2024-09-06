<template>
  <!-- 收支记录分页列表页 -->
  <div id="cottomsList">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="formInline.itemName" placeholder="请输入项目名称"></el-input>
      </el-form-item>

      <el-form-item>
        <el-date-picker v-model="formInline.date" type="date" placeholder="选择日期" value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button icon="el-icon-search" circle @click="handleSearch"></el-button>
      </el-form-item>
    </el-form>

    <!-- 筛选列表内容 -->
    <el-table :data="cottomsList" style="width: 100%" row-key="transactionId">
      <!-- 展开行 -->
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="table-expand">
            <el-form-item label="支付方式" class="zhankai">
              <span>{{ props.row.paymentMethod }}</span>
            </el-form-item>
            <el-form-item label="支付状态" class="zhankai">
              <span>{{ props.row.paymentStatus }}</span>
            </el-form-item>
            <el-form-item label="科室/部门" class="zhankai">
              <span>{{ props.row.department }}</span>
            </el-form-item>
            <el-form-item label="操作人员" class="zhankai">
              <span>{{ props.row.handledBy }}</span>
            </el-form-item>
            <el-form-item label="项目描述" class="zhankai">
              <span>{{ props.row.description }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <!-- 基本信息 -->
      <el-table-column label="流水编号" width="200" prop="transactionId">
      </el-table-column>

      <el-table-column label="日期" width="150" prop="date">
      </el-table-column>

      <!-- 收支类型 -->
      <el-table-column label="收支类型" width="150" prop="transactionType">

      </el-table-column>

      <el-table-column label="收支分类" width="180" prop="category">

      </el-table-column>

      <!-- 详细信息 -->
      <el-table-column label="项目名称" width="220" prop="itemName">

      </el-table-column>

      <el-table-column label="数量" width="120" prop="quantity">

      </el-table-column>

      <el-table-column label="单价" width="145" prop="unitPrice">

      </el-table-column>

      <el-table-column label="总金额" width="145" prop="totalAmount">

      </el-table-column>
    </el-table>

    <el-pagination class="page" @size-change="handleSizeChange" @current-change="handleCurrentChange"
      :current-page.sync="currentPage" :page-size="pageSize" background layout="prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
import axios from 'axios';
import qs from 'qs';

export default {
  data() {
    return {
      formInline: {
        itemName: '',
        date: '',
      },
      cottomsList: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
      departmentMap: {
        1826793026329628674: '内科',
        1826793027415953410: '外科',
        1826793027415953411: '儿科',
        1826793027478867970: '妇产科',
        1826793027478867972: '骨科',
      },
    };
  },
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      this.getCottomsList();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getCottomsList();
    },
    getCottomsList() {
      axios.post('/admin/showrun', qs.stringify({
        page: this.currentPage,
        pageSize: this.pageSize,
      })).then(res => {
        if (res.data.code === 1) {
          const records = res.data.data.records.map(record => ({
            transactionId: record.id,
            date: record.date,
            transactionType: record.sort,
            category: record.type,
            itemName: record.name,
            quantity: record.quantity,
            unitPrice: record.price,
            totalAmount: record.total,
            paymentMethod: record.payway,
            paymentStatus: record.status,
            department: this.departmentMap[record.department] || '未知部门',
            handledBy: record.operateman,
            description: record.description,
          }));
          this.cottomsList = records;
          this.total = res.data.data.total;
        } else {
          this.$message.error('加载数据失败');
        }
      }).catch(() => {
        this.$message.error('请求失败');
      });
    },
    handleSearch() {
      const { itemName, date } = this.formInline;
      axios.post('/admin/searchrun', qs.stringify({
        runName: itemName,
        date: date,
      })).then(res => {
        if (res.data.code === 1) {
          const records = res.data.data.map(record => ({
            transactionId: record.id,
            date: record.date,
            transactionType: record.sort,
            category: record.type,
            itemName: record.name,
            quantity: record.quantity,
            unitPrice: record.price,
            totalAmount: record.total,
            paymentMethod: record.payway,
            paymentStatus: record.status,
            department: this.departmentMap[record.department] || '未知部门',
            handledBy: record.operateman,
            description: record.description,
          }));
          this.cottomsList = records;
          this.total = records.length;
        } else {
          this.$message.error('搜索失败');
        }
      }).catch(() => {
        this.$message.error('请求失败');
      });
    },
  },
  mounted() {
    this.getCottomsList();
  },
};
</script>

<style lang="scss">
#cottomsList {
  position: relative;

  .el-input__inner {
    border-radius: 20px;
  }

  .page {
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    bottom: 0;
    margin-top: 20px;
  }
}

.table-expand {
  padding: 10px 0;
}

.table-expand .el-form-item {
  margin-bottom: 5px;
}

.zhankai {
  width: 25%;
  margin-left: 5%;
}
</style>
