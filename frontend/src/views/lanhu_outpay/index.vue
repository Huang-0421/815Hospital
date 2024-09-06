<template>  
  <div class="page flex-col justify-end">  
    <div class="group_1 flex-col">  
      <span class="text_1">门诊缴费</span>  
      <img @click="goBack()" 
        class="label_1"  
        referrerpolicy="no-referrer"  
        src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"  
      />  
    </div>  

    <div class="group_2 flex-col">  
      <div v-for="(item, index) in paymentData" :key="index" class="block_3 flex-row" :class="{ selected: selectedItems[item.id] }">  
        <div class="section_1 flex-col">  
          <div class="section_2 flex-col">  
            <div class="text-wrapper_2 flex-col">  
              <span class="text_5" @click="toggleSelection(item.id)">{{ item.statusText }}</span>  
            </div>  
          </div>  
        </div>  
        <div class="text-group_3 flex-col justify-between">  
          <span class="text_10">{{ item.medicationName }}</span>  
          <span class="text_11">{{ item.amountDue }} 元</span>  
        </div>  
        <div class="section_3 flex-col"></div>  
        <div class="text-wrapper_5 flex-col">  
          <el-switch
            v-model="selectedItems[item.id]"
            active-text="已选"
            inactive-text="未选"
            v-if="item.flag === 0">
          </el-switch>
        </div>  
      </div>  
    </div>  

    <div class="group_7 flex-col">  
      <div class="text-wrapper_6 flex-col">  
        <span class="text_13" @click="confirmPayment">确认缴费</span>  
      </div>  
      <div class="box_4 flex-col"></div>  
    </div>  

    <div v-if="showAlert" class="alert-popup">
      <div class="alert-content">
        <p>缴费成功</p>
        <p>总金额：{{ totalAmount }} 元</p>
        <button @click="closeAlert">确定</button>
      </div>
    </div>
  </div>  
</template>  

<script>
import axios from 'axios';

export default {  
  data() {  
    return { 
      paymentData: [],
      selectedItems: {}, 
      showAlert: false,
      totalAmount: 0,
    };  
  },
  methods: {  
    async fetchPaymentData() {
      try {
        const response = await axios.get('/patient/showpayment');
        if (response.data.code === 1) {
          const data = response.data.data.map(item => ({
            ...item,
            statusText: item.flag === 0 ? '待缴费' : '已缴费',
          }));
          this.paymentData = data.sort((a, b) => a.flag - b.flag);  // 在这里进行排序
        } else {
          console.error('获取缴费信息失败:', response.data.msg);
        }
      } catch (error) {
        console.error('请求失败:', error);
      }
    },
    goBack() {  
      this.$router.go(-1); // 返回到上一个页面  
    },  
    toggleSelection(id) {  
      this.$set(this.selectedItems, id, !this.selectedItems[id]);  
    },  
    async confirmPayment() {  
      const selectedIds = Object.keys(this.selectedItems).filter(id => this.selectedItems[id]);
      if (selectedIds.length === 0) {
        alert('请选择至少一个待缴费项');
        return;
      }
      // 计算总金额
      this.totalAmount = this.paymentData
        .filter(item => selectedIds.includes(item.id.toString()))
        .reduce((sum, item) => sum + item.amountDue, 0);

      try {
        const payload = {
          ids: selectedIds
        };

        const response = await axios.post('/patient/dopayment', payload, {
          headers: {
            'Content-Type': 'application/json',
          },
        });

        if (response.data.code === 1) {
          this.showAlert = true;  // 显示弹窗
          this.fetchPaymentData(); // 重新获取并展示缴费信息
        } else {
          console.error('缴费失败:', response.data.msg);
        }
      } catch (error) {
        console.error('缴费请求失败:', error);
      }
    },
    closeAlert() {
      this.showAlert = false;
    },
  },
  mounted() {
    this.fetchPaymentData(); // 初始化时获取缴费信息
  }
};  
</script>  

<style scoped lang="css" src="./assets/index.rem.css">
</style>
