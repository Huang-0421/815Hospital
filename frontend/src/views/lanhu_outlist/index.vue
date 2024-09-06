<template>
  <div class="page">
    <div class="group_1 flex-col"> 
      <div class="list_1">
        <div class="list-items_1 flex-col" v-for="(item, index) in filteredData" :key="index">
          <div class="group_2 flex-col">
            <div class="box_1 flex-col" :style="{ backgroundImage: `url(${require('./assets/img/yiliaobao.png')})`, backgroundSize: 'cover' }">      
              <div class="text-wrapper_1 flex-col">
                <span class="text_1" v-if="item.status === 'pending'">待缴费</span>
                <span class="text_1" v-else-if="item.status === 'paid'">已缴费</span>
              </div>
            </div>
          </div>
          <div class="text-group_1 flex-col justify-between">
            <span class="text_2" :style="{ color: item.lanhufontColor0 }">{{ item.medicationName }}</span>
            <span class="text_3" :style="{ color: item.lanhufontColor1 }">{{ `￥${item.price * item.quantity}` }}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="group_3 flex-col">
      <div class="group_4 flex-row">
        <img @click="goBack"
          class="label_1"
          referrerpolicy="no-referrer"
          src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"
        />
        <span class="text_4">门诊清单</span>
        <div class="box_2 flex-col">
          <div class="block_1 flex-col"></div>
          <div class="text-wrapper_2 flex-col" @click="filterStatus = 'pending'">  
              <span class="text_7">待缴费</span>  
              <div class="block_2 flex-col" v-if="filterStatus === 'pending'"></div>  
          </div>
          <div class="text-wrapper_3 flex-col" @click="filterStatus = 'paid'">
			  <span class="text_7">已缴费</span>
			  <div class="block_2 flex-col" v-if="filterStatus === 'paid'"></div>  
			  </div>
			  <div class="group_5 flex-col">
			    <span class="text_7" @click="filterStatus = 'all'">全部</span>
			    <div class="block_2 flex-col" v-if="filterStatus === 'all'"></div>  
			  </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      loopData0: [],
      filterStatus: 'all',
    };
  },
  computed: {
    filteredData() {
      if (this.filterStatus === 'all') {
        return this.loopData0;
      }
      return this.loopData0.filter(item => item.status === this.filterStatus);
    }
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    async fetchPaymentData() {
      try {
        const response = await axios.get('/patient/showpayment');
        if (response.data.code === 1) {
          this.loopData0 = response.data.data.map(item => ({
            status: item.flag === 1 ? 'paid' : 'pending',
            medicationName: item.medicationName,
            price: item.price,
            quantity: item.quantity,
            lanhufontColor0: item.flag === 1 ? 'rgba(197,200,203,1.000000)' : 'rgba(28,32,35,1.000000)',
            lanhufontColor1: item.flag === 1 ? 'rgba(197,200,203,1.000000)' : 'rgba(96,105,114,1.000000)',
          }));
        }
      } catch (error) {
        console.error('获取门诊清单失败:', error);
      }
    }
  },
  mounted() {
    this.fetchPaymentData();
  }
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
