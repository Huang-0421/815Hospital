<template>
  <div class="page flex-col align-center">
    <div class="group_1 flex-col">
      <div class="group_2 flex-col">
        <div class="box_1 flex-col">
          <div class="section_1 flex-col">
            <div class="group_3 flex-col">
              <div class="nav-bar_1 flex-col"><span class="text_1">个人信息</span></div>
              <div class="section_2 flex-col"></div>
            </div>
            <div class="group_4 flex-col">
              <div class="box_2 flex-row justify-between" @click="navigateToPage('lanhu_personalinformationmodificationpatientend')">
                <div class="image-text_1 flex-row justify-between">
                  <img
                    class="icon_1"
                    referrerpolicy="no-referrer"
                    src="./assets/img/MasterDDSSlicePNGabc4165e7301b2f9597cdb091430096b.png"
                  />
                  <span class="text-group_1">完善修改个人信息</span>
                </div>
                <img
                  class="icon_2"
                  referrerpolicy="no-referrer"
                  src="./assets/img/MasterDDSSlicePNG9bc9f6b084958f50d4f97bb14fcee135.png"
                />
              </div>
              <div class="box_3 flex-col"></div>
              <div class="box_4 flex-row justify-between" @click="navigateToPage('lanhu_outlist')">
                <div class="image-text_2 flex-row justify-between">
                  <img
                    class="icon_3"
                    referrerpolicy="no-referrer"
                    src="./assets/img/MasterDDSSlicePNGea5e9e1b15b0893ecbec735d3e071411.png"
                  />
                  <span class="text-group_2">门诊缴费</span>
                </div>
                <img
                  class="icon_4"
                  referrerpolicy="no-referrer"
                  src="./assets/img/MasterDDSSlicePNG9bc9f6b084958f50d4f97bb14fcee135.png"
                />
              </div>
              <div class="box_5 flex-col"></div>
              <div class="box_6 flex-row justify-between" @click="navigateToPage('lanhu_myclinic')">
                <div class="image-text_3 flex-row justify-between">
                  <img
                    class="icon_5"
                    referrerpolicy="no-referrer"
                    src="./assets/img/MasterDDSSlicePNGde4e6c0c833d43c38085d39c0578c6fb.png"
                  />
                  <span class="text-group_3">我的挂号</span>
                </div>
                <img
                  class="icon_6"
                  referrerpolicy="no-referrer"
                  src="./assets/img/MasterDDSSlicePNG9bc9f6b084958f50d4f97bb14fcee135.png"
                />
              </div>
              <div class="box_7 flex-col"></div>
              <div class="box_9 flex-col"></div>
            </div>
            <div class="block_5 flex-col">
              <div class="block_6 flex-row">
                <div
                  class="image-text_9 flex-col justify-between"
                  @click="navigateToPage('lanhu_index')"
                >
                  <img
                    class="label_3"
                    referrerpolicy="no-referrer"
                    src="./assets/img/MasterDDSSlicePNG476d10fdc6a5fb066f558eaec6e07bde.png"
                  />
                  <span class="text-group_11">首页</span>
                </div>
                <div
                  class="image-text_10 flex-col justify-between"
                  @click="navigateToPage('lanhu_myclinic')"
                >
                  <img
                    class="label_4"
                    referrerpolicy="no-referrer"
                    src="./assets/img/MasterDDSSlicePNGc82113e91e4dec445f2342ef8118824b.png"
                  />
                  <span class="text-group_12">我的诊室</span>
                </div>
                <div
                  class="image-text_11 flex-col justify-between"
                  @click="navigateToPage('lanhu_messagecenter')"
                >
                  <img
                    class="label_5"
                    referrerpolicy="no-referrer"
                    src="./assets/img/MasterDDSSlicePNG47b3ad6e970da7ba0f1d1d5bfbace999.png"
                  />
                  <span class="text-group_13">信息中心</span>
                </div>
                <div
                  class="image-text_12 flex-col justify-between"
                  @click="navigateToPage('lanhu_personalcenter')"
                >
                  <img
                    class="label_6"
                    referrerpolicy="no-referrer"
                    src="./assets/img/MasterDDSSlicePNGbf84a97dc08501bd87daa119d7827848.png"
                  />
                  <span class="text-group_14">个人中心</span>
                </div>
              </div>
            </div>
            <div class="group_5 flex-col">
              <div class="text-wrapper_1 justify-between">
                <span class="text_2">真实姓名</span>
                <span class="text_3">{{ patientInfo.username }}</span>
				        <img class="touxiang" :src="patientInfo.photo" @click="handleChangePhoto"/>
              </div>
              <div class="group_6 flex-col"></div>
              <div class="text-wrapper_2 flex-row justify-between">
                <span class="text_4">身份证号</span>
                <span class="text_5">{{ patientInfo.idCard }}</span>
              </div>
              <div class="group_7 flex-col"></div>
              <div class="text-wrapper_3 flex-row justify-between">
                <span class="text_6">性别</span>
                <span class="text_7">{{ genderText }}</span>
              </div>
              <div class="group_8 flex-col"></div>
              <div class="text-wrapper_4 flex-row justify-between">
                <span class="text_8">年龄</span>
                <span class="text_9">{{ age }}</span>
              </div>
              <div class="group_9 flex-col"></div>
              <div class="text-wrapper_5 flex-row justify-between">
                <span class="text_10">联系电话</span>
                <span class="text_11">{{ patientInfo.phone }}</span>
              </div>
              <div class="group_10 flex-col"></div>
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
      patientInfo: {
        username: '',
        idCard: '',
        gender: '',
        phone: '',
        photo: '',
      },
      genderText: '',
      age: ''
    };
  },
  methods: {
    async fetchPatientInfo() {
      try {
        const response = await axios.get('/patient/showpatient', {
          withCredentials: true,
        });

        if (response.data.code === 1) {
          const patient = response.data.data.patient;
          this.patientInfo = {
            username: patient.username,
            idCard: patient.idCard,
            gender: patient.gender,
            phone: patient.phone,
            photo: patient.photo,
          };
          this.genderText = patient.gender === 1 ? '男' : '女';
          this.age = this.calculateAge(patient.idCard);
        } else {
          console.error('获取个人信息失败: ', response.data.msg);
        }
      } catch (error) {
        console.error('请求失败: ', error);
      }
    },
    calculateAge(idCard) {
      const birthYear = parseInt(idCard.substr(6, 4));
      const birthMonth = parseInt(idCard.substr(10, 2));
      const birthDay = parseInt(idCard.substr(12, 2));
      const currentYear = 2024;
      const currentMonth = 8;
      const currentDay = 30;

      let age = currentYear - birthYear;
      if (currentMonth < birthMonth || (currentMonth === birthMonth && currentDay < birthDay)) {
        age--;
      }
      return age;
    },
    navigateToPage(pageName) {
      if (this.$route.name !== pageName) {
                  this.$router.push({ name: pageName });
        }   
    },
    handleChangePhoto() {
      const input = document.createElement('input');
      input.type = 'file';
      input.accept = 'image/*';

      input.onchange = async (event) => {
        const file = event.target.files[0];
        if (file) {
          const formData = new FormData();
          formData.append('image', file);

          try {
            const response = await axios.post('/patient/setimage', formData, {
              headers: {
                'Content-Type': 'multipart/form-data',
              },
            });

            if (response.data.code === 1) {
              this.patientInfo.photo = URL.createObjectURL(file);
            }
          } catch (error) {
            console.error('上传头像失败: ', error);
          }
        }
      };

      input.click();
    },
  },
  mounted() {
    this.fetchPatientInfo();
  },
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
