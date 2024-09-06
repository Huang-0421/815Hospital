<template>
  <div class="page flex-col align-center">
    <div class="nav-bar_1 flex-row">
      <img
        @click="navigateTo('/lanhu_index')"
        class="icon_1"
        referrerpolicy="no-referrer"
        src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"
      />
      <span class="text_1">预约门诊</span>
    </div>
    <div class="content flex-row">
      <el-cascader
        :options="options"
        @change="handleChange"
        placeholder="请选择科室和医生"
        class="custom-cascader"
        style="width: 12rem; margin-top: 1rem;"
      ></el-cascader>
    </div>
    <div class="block">
      <el-date-picker
        v-model="timevalue1"
        type="datetime"
        placeholder="选择日期时间"
        style="width: 12rem; margin-top: 1rem;"
        :picker-options="pickerOptions"
      ></el-date-picker>
    </div>
    <div class="group_7 flex-col">
      <div class="text-wrapper_6 flex-col">
        <span class="text_13" @click="confirm">确认预约</span>
      </div>
    </div>

    <!-- 弹窗 -->
    <div v-if="showAlert" class="alert-popup">
      <div class="alert-content">
        <p>{{ alertMessage }}</p>
        <button @click="closeAlert">确定</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { Cascader, DatePicker } from 'element-ui';

export default {
  components: {
    'el-cascader': Cascader,
    'el-date-picker': DatePicker,
  },

  data() {
    return {
      timevalue1: '',
      selectedDoctor: '',
      selectedDepartment: '',
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7;
        },
      },
      options: [], // 动态加载的科室和医生数据
      showAlert: false,
      alertMessage: '',
    };
  },
  methods: {
    async fetchDoctorData() {
      try {
        const response = await axios.get('/patient/showdoc');
        if (response.data.code === 1) {
          const data = response.data.data;
          const departments = {};

          data.forEach((item) => {
            if (!departments[item.departmentName]) {
              departments[item.departmentName] = {
                value: item.departmentName,
                label: item.departmentName,
                children: [],
              };
            }
            departments[item.departmentName].children.push({
              value: item.doctorName,
              label: item.doctorName,
            });
          });

          this.options = Object.values(departments);
        } else {
          console.error('获取医生数据失败:', response.data.msg);
        }
      } catch (error) {
        console.error('请求失败:', error);
      }
    },
    handleChange(value) {
      this.selectedDepartment = value[0];
      this.selectedDoctor = value[1];
      console.log('Selected Department:', this.selectedDepartment);
      console.log('Selected Doctor:', this.selectedDoctor);
    },
    validateForm() {
      if (!this.selectedDepartment || !this.selectedDoctor || !this.timevalue1) {
        this.alertMessage = '请完整选择科室、医生和预约时间。';
        this.showAlert = true;
        return false;
      }
      return true;
    },
    formatDate(date) {
      const d = new Date(date);
      const year = d.getFullYear();
      const month = String(d.getMonth() + 1).padStart(2, '0');
      const day = String(d.getDate()).padStart(2, '0');
      const hours = String(d.getHours()).padStart(2, '0');
      const minutes = String(d.getMinutes()).padStart(2, '0');
      const seconds = String(d.getSeconds()).padStart(2, '0');
      return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
    },
    async confirm() {
      if (!this.validateForm()) {
        return;
      }

      const formattedDate = this.formatDate(this.timevalue1);

      const formData = new FormData();
      formData.append('department', this.selectedDepartment);
      formData.append('doctorName', this.selectedDoctor);
      formData.append('date', formattedDate);

      try {
        const response = await axios.post('http://192.168.1.191:8080/patient/appoint', formData, {
          withCredentials: true,
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });

        if (response.data.code === 1) {
          this.alertMessage = '预约成功！';
        } else {
          this.alertMessage = '预约失败: ' + response.data.msg;
        }
      } catch (error) {
        console.error('请求失败:', error);
        this.alertMessage = '请求失败，请稍后再试。';
      }

      this.showAlert = true;
    },
    closeAlert() {
      this.showAlert = false;
      this.alertMessage = '';
    },
    navigateTo(url) {
      if (url) {
        this.$router.push(url);
      }
    },
  },
  mounted() {
    this.fetchDoctorData(); // 页面加载时获取医生数据
  },
};
</script>

<style scoped>
.page {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.nav-bar_1 {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.content {
  display: flex;
  flex-direction: row;
}

.el-cascader {
  width: 200px;
}
.el-cascader-menu {
  size: 'mini';
}
/* 弹窗样式 */
.alert-popup {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.alert-content {
  background-color: white;
  padding: 1rem;
  border-radius: 10px;
  text-align: center;
  width: 80%;
  max-width: 300px;
}

.alert-content p {
  margin: 0 0 1rem;
  font-size: 1rem;
}

.alert-content button {
  padding: 0.5rem 1rem;
  background-color: #49b9ad;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>

<style scoped lang="css" src="./assets/index.rem.css" />
