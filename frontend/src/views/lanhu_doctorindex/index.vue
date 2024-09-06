<template>
  <div class="page flex-col">
    <div class="section_4 flex-col">
      <div class="box_3 flex-row">
        <img
          class="image_1"
          referrerpolicy="no-referrer"
          :src="doctor.img"
        />
        <div class="text-wrapper_6 flex-col">
          <span class="text_18">{{ doctor.name }}</span>
          <span class="text_18">医院编号:{{ doctor.ID }}</span>
          <span class="text_18">联系电话:{{ doctor.Pnumber }}</span>
          <span class="text_18">待诊人数:{{ doctor.waitnum }}</span>
        </div>
      </div>
      <div class="box_2 flex-row justify-end">
        <div class="tab-bar_1 flex-row">
          <div class="tab-bar-item_1 flex-col justify-between" @click="navigateToPage('lanhu_doctorindex')">
            <img
              class="icon_1"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG2842f3f3a94e337935ea9437136b6b07.png"
            />
            <span class="text_1">首页</span>
          </div>
          <div class="tab-bar-item_2 flex-col justify-between" @click="navigateToPage('lanhu_doctormessage')">
            <img
              class="icon_2"
              referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG47b3ad6e970da7ba0f1d1d5bfbace999.png"
            />
            <span class="text_2">消息</span>
          </div>
        </div>
        <div class="block_1 flex-row" @click="navigateToPage('lanhu_doctereditinfo')">
          <span class="text_16">修改个人信息</span>
          <img
            class="icon_3"
            referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNG5b6e607f15bf3044d6d9ce12c02b435f.png"
          />
        </div>
        <div class="list_1 flex-col">
          <div class="list-items_1 flex-col" v-for="(item, index) in loopData0" :key="index">
            <div class="section_2 flex-row">
              <span class="text_3">{{ item.name }}</span>
              <span class="text_4"></span>
              <button class="button_1 flex-col" @click="navigateToPatientPage(item.id)">
                <span class="text_5">就诊</span>
              </button>
            </div>
    
            <div class="section_3 flex-row justify-between">
              <div class="text-wrapper_2 flex-col justify-between">
                <span class="text_9">就诊ID</span>
                <span class="text_9">联系电话:</span>
                <span class="text_9">申请时间</span>
              </div>
              <div class="text-wrapper_3 flex-col justify-between">
                <span class="text_11">{{ item.id }}</span>
                <span class="text_11">{{ item.phone }}</span>
                <span class="text_11">{{ item.inquiryTime }}</span>
              </div>
            </div>
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
      doctor: {
        name: '',
        ID: '',
        Pnumber: '',
        waitnum: '',
        img: ''
      }
    };
  },
  methods: {
    async fetchDoctorInfo() {
      try {
        const response = await axios.get('/doctor/showme');
        if (response.data.code === 1) {
          const doctorData = response.data.data;
          this.doctor = {
            name: doctorData.name,
            ID: doctorData.id,
            Pnumber: doctorData.phone,
            waitnum: doctorData.todo,
            img: doctorData.image
          };
        }
      } catch (error) {
        console.error('获取医生信息失败:', error);
      }
    },
    async fetchPatients() {
      try {
        const response = await axios.get('/doctor/showpatients');
        if (response.data.code === 1) {
          this.loopData0 = response.data.data;
        }
      } catch (error) {
        console.error('获取病人列表失败:', error);
      }
    },
    navigateToPage(pageName) {
      if (this.$route.name !== pageName) {
        this.$router.push({ name: pageName });
      }
    },
    navigateToPatientPage(patientId) {
      // 使用路由传递参数
      this.$router.push({ name: 'lanhu_doctorpatientedit', query: { patientId } });
    }
  },
  mounted() {
    this.fetchDoctorInfo();
    this.fetchPatients();
  }
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
