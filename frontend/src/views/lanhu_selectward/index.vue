<template>
  <div class="page flex-col">
    <div class="block_1 flex-col">
      <div class="box_1 flex-col">
        <div class="box_2 flex-col">
          <div class="group_1 flex-row">
            <div class="group_2 flex-col">
              <span class="text_1">病房选择</span>
              <div class="block_2 flex-col">
                <div class="block_3 flex-row">
                  <div class="text-wrapper_21 flex-col">
                    <span class="text_2">{{ remaining }}</span>
                    <span class="text_3">病房剩余</span>
                  </div>
                  <div class="text-wrapper_21 flex-col">
                    <span class="text_4">{{ history }}</span>
                    <span class="text_6">历史住院人数</span>
                  </div>
                  <div class="text-wrapper_21 flex-col">
                    <span class="text_99">{{ rate }}</span>
                    <span class="text_5">满房率</span>
                  </div>
                </div>
              </div>
              <div class="box_6 flex-row justify-between">
                <!-- 五个科室选择按钮 -->
                <div
                  class="image-text_6 flex-col justify-between department-box dept-color-1"
                  :class="{ 'selected-department': selectedDepartment === '内科' }"
                  @click="selectDepartment('内科')"
                >
                  <img
                    class="image_1"
                    referrerpolicy="no-referrer"
                    src="./assets/img/neike.svg"
                  />
                  <span class="text-group_1">内科</span>
                </div>
                <div
                  class="image-text_6 flex-col justify-between department-box dept-color-2"
                  :class="{ 'selected-department': selectedDepartment === '外科' }"
                  @click="selectDepartment('外科')"
                >
                  <img
                    class="image_1"
                    referrerpolicy="no-referrer"
                    src="./assets/img/waike.svg"
                  />
                  <span class="text-group_1">外科</span>
                </div>
                <div
                  class="image-text_6 flex-col justify-between department-box dept-color-3"
                  :class="{ 'selected-department': selectedDepartment === '妇科' }"
                  @click="selectDepartment('妇科')"
                >
                  <img
                    class="image_1"
                    referrerpolicy="no-referrer"
                    src="./assets/img/fuke.svg"
                  />
                  <span class="text-group_1">妇科</span>
                </div>
                <div
                  class="image-text_6 flex-col justify-between department-box dept-color-4"
                  :class="{ 'selected-department': selectedDepartment === '儿科' }"
                  @click="selectDepartment('儿科')"
                >
                  <img
                    class="image_1"
                    referrerpolicy="no-referrer"
                    src="./assets/img/erke.svg"
                  />
                  <span class="text-group_1">儿科</span>
                </div>
                <div
                  class="image-text_6 flex-col justify-between department-box dept-color-5"
                  :class="{ 'selected-department': selectedDepartment === '骨科' }"
                  @click="selectDepartment('骨科')"
                >
                  <img
                    class="image_1"
                    referrerpolicy="no-referrer"
                    src="./assets/img/guke.svg"
                  />
                  <span class="text-group_1">骨科</span>
                </div>
              </div>
              <button class="text-wrapper_3 flex-col" @click="goBack"><span class="text_9">返回</span></button>
            </div>
            <div class="group_6 flex-col large-room-container">
              <!-- 动态病房列表，根据选择的科室更新 -->
              <div
                class="section_2 flex-row justify-between room-box"
                v-for="(room, index) in filteredRooms"
                :key="index"
                @click="selectRoom(room)"
                :class="{ 'selected-room': selectedRoom === room }"
              >
                <div class="image-text_7 flex-row justify-between">
                  <img
                    class="label_1"
                    referrerpolicy="no-referrer"
                    src="./assets/img/MasterDDSSlicePNGac02c83018e5f07581ddfda3111810e6.png"
                  />
                  <span class="text-group_2">{{ room.name }}</span>
                </div>
                <button class="text-wrapper_4 flex-col"><span class="text_10">{{ room.status }}</span></button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 提交按钮，固定在屏幕底部边缘 -->
    <button class="text-wrapper_20 bottom-submit" @click="onSubmit"><span class="text_38">提交</span></button>

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

export default {
  data() {
    return {
      selectedDepartment: '内科',
      selectedRoom: null,
      filteredRooms: [],
      remaining: 0,
      history: 0,
      rate: '',
      showAlert: false,
      alertMessage: '',
    };
  },
  methods: {
    async fetchInitialData() {
      try {
        const response = await axios.get('http://192.168.1.191:8080/patient/showfirstward', {
          withCredentials: true,
        });
        if (response.data.code === 1) {
          this.updateRoomData(response.data.data);
        }
      } catch (error) {
        console.error('Failed to fetch initial data:', error);
      }
    },
    async selectDepartment(department) {
      this.selectedDepartment = department;
      this.selectedRoom = null;
      try {
        const formData = new FormData();
        formData.append('department', department);

        const response = await axios.post(
          'http://192.168.1.191:8080/patient/showspecward',
          formData,
          { withCredentials: true }
        );
        if (response.data.code === 1) {
          this.filteredRooms = Object.keys(response.data.data).map((key) => ({
            name: `房间${key}`,
            status: response.data.data[key] === 0 ? '可选' : '已满',
            id: key,
            full: response.data.data[key] === 1,
          }));
        }
      } catch (error) {
        console.error('Failed to fetch department data:', error);
      }
    },
    selectRoom(room) {
      if (room.full) {
        this.showAlertPopup('该房间已满，请选择其他房间');
        return;
      }
      this.selectedRoom = room;
    },
    async onSubmit() {
      if (!this.selectedRoom) {
        this.showAlertPopup('请先选择一个房间');
        return;
      }
      try {
        const formData = new FormData();
        formData.append('roomNumber', this.selectedRoom.id);

        const response = await axios.post(
          'http://192.168.1.191:8080/patient/selectward',
          formData,
          { withCredentials: true }
        );
        if (response.data.code === 1) {
          this.updateRoomData(response.data.data);
          this.showAlertPopup('房间选择成功');
        }
      } catch (error) {
        console.error('Failed to submit room selection:', error);
        this.showAlertPopup('房间选择失败，请稍后重试');
      }
    },
    updateRoomData(data) {
      this.remaining = data.remaining;
      this.history = data.history;
      this.rate = data.rate;
      this.filteredRooms = Object.keys(data)
        .filter((key) => !isNaN(Number(key)))
        .map((key) => ({
          name: `房间${key}`,
          status: data[key] === 0 ? '可选' : '已满',
          id: key,
          full: data[key] === 1,
        }));
    },
    showAlertPopup(message) {
      this.alertMessage = message;
      this.showAlert = true;
    },
    closeAlert() {
      this.showAlert = false;
    },
    goBack() {
      this.$router.push('/lanhu_index');
    },
  },
  mounted() {
    this.fetchInitialData();
  },
};
</script>

<style scoped>
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

.flex-col {
  display: flex;
  flex-direction: column;
}

.flex-row {
  display: flex;
  flex-direction: row;
}

.justify-between {
  justify-content: space-between;
}

.selected-department {
  border: 2px solid #49b9ad;
}

.selected-room {
  background-color: #e0f7fa;
}

.bottom-submit {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #49b9ad;
  color: white;
  text-align: center;
  padding: 1rem;
  cursor: pointer;
}
</style>

<style scoped lang="css" src="./assets/index.rem.css" />
