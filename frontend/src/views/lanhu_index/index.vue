<template>
  <div class="page flex-col">
    <!-- 页面内容保持不变 -->
    <div class="block_1 flex-row justify-end">
      <div class="block_2 flex-col">
        <img class="image_1" referrerpolicy="no-referrer"
          src="./assets/img/MasterDDSSlicePNG5e391ef1170d68611d100e493760a77d.png" />
        <span class="text_1">门诊服务</span>
        <div class="image-text_1 flex-col justify-between">
          <img class="image_2" referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNG260df46699c54c5872e4f4c6008a97dc.png" />
          <span class="text-group_1">住院服务</span>
        </div>
        <div class="image-text_2 flex-col justify-between">
          <img class="image_3" referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNG502d425397a57fa690a090d3ddc4c211.png" />
          <span class="text-group_2">便民服务</span>
        </div>
        <div class="image-wrapper_1 flex-col">
          <img class="image_4" referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNGe41f6d12d9f8deb3da212364040b9874.png" />
        </div>
        <span class="text_2">特色服务</span>
      </div>
      <div class="image-text_3 flex-col justify-between" @click="navigateToPage('lanhu_medicalknowledge')">
        <img class="image_5" referrerpolicy="no-referrer"
          src="./assets/img/MasterDDSSlicePNG7ed07b137e61c53f0da1d2cfb2b4f4d4.png" />
        <span class="text-group_3">知识学习</span>
      </div>
      <div class="image-text_4 flex-col justify-between" @click="navigateToPage('lanhu_outlist')">
        <img class="image_6" referrerpolicy="no-referrer"
          src="./assets/img/MasterDDSSlicePNG9f174a06568ecce02c6de86d5a504e76.png" />
        <span class="text-group_4">门诊清单</span>
      </div>
      <div class="block_3 flex-col">
        <div class="section_1 flex-row">
          <div class="image-text_5 flex-col justify-between" @click="navigateToPage('lanhu_bookcall')">
            <img class="image_7" referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNGd1da031def5ca3f463ee959318d9a5c3.png" />
            <span class="text-group_5">预约挂号</span>
          </div>
        </div>
        <div class="image-wrapper_2 flex-row" @click="navigateToPage('lanhu_nursingpropaganda')">
          <img class="image_8" referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNGef0693b7b4d3ca7744441e496e15e168.png" />
        </div>
        <div class="text-wrapper_1 flex-row">
          <span class="text_3">护理宣传</span>
        </div>
        <div class="section_2 flex-row">
          <div class="image-text_6 flex-col justify-between" @click="navigateToPage('lanhu_demstrateofhospital')">
            <img class="image_9" referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNGfe1c2aa3e27051a62a49ab832bb39e2c.png" />
            <span class="text-group_6">医院信息</span>
          </div>
          <div class="image-text_7 flex-col justify-between" @click="navigateToDestination">
            <img class="image_10" referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG80618b5bb3fb3f1cd03e15873a1a3c01.png" />
            <span class="text-group_7">来院导航</span>
          </div>
          <div class="image-text_8 flex-col justify-between" @click="navigateToPage('lanhu_feedback')">
            <img class="image_11" referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNGebf0105961b66f1075052e64a9006d09.png" />
            <span class="text-group_8">意见反馈</span>
          </div>
        </div>
        <div class="image-wrapper_3 flex-row justify-between">
          <img class="image_12" referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNG7e0fd322d748ba7b6037d2554b97818d.png" />
          <img class="image_13" referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNGd975ddfa5b0fc5f6d0e7611c59863870.png" />
        </div>
        <div class="text-wrapper_2 flex-row justify-between">
          <span class="text_4">特色制剂</span>
          <span class="text_5">复诊续方</span>
        </div>
        <div class="section_3 flex-col">
          <div class="box_1 flex-row">
            <div class="box_2 flex-col">
              <div class="box_3 flex-col"></div>
              <img class="label_1" referrerpolicy="no-referrer"
                src="./assets/img/fbd040b6696640c9ab29ac709671fc5a_mergeImage.png" />
            </div>
            <img class="image_14" referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG030a8a6fe4f31abcb95932630fe32fae.png" />
            <span class="text_6">重庆八一五医院</span>
          </div>
          <div class="box_4 ">
            <div class="group_1 flex-row">
              <div class="block_4 flex-col"></div>
              <span class="text_7">{{ patientInfo.username || '姓名' }}</span>
              <span class="text_8">Tel: {{ patientInfo.phone || '' }}</span>
            </div>
            <div class="group_2 flex-row justify-between">
              <div class="list_1 flex-row">
                <div class="text-group_9 flex-col justify-between">
                  <span class="text_9">{{ patientInfo.genderText || '男' }}</span>
                  <span class="text_10">性别</span>
                </div>
                <div class="text-group_9 flex-col justify-between">
                  <span class="text_9">{{ patientInfo.age || '0' }}</span>
                  <span class="text_10">年龄</span>
                </div>
                <div class="text-group_10 flex-col justify-between">
                  <span class="text_11">{{ patientInfo.inquiryTime || '未挂号' }}</span>
                  <span class="text_12">挂号</span>
                </div>
              </div>
            </div>
			<div class="imgbox">
				<img class="image_15"
              :src="patientInfo.photo || './assets/img/kongbai.png'"
              referrerpolicy="no-referrer" />
			</div>
            
          </div>
          <div class="image-wrapper_4 flex-row">
            <button @click="navigateToPage('lanhu_bookcall')">
              <img class="image_16" referrerpolicy="no-referrer"
                src="./assets/img/MasterDDSSlicePNGc19b5c78045d50a51b7740c96e94a665.png" />
            </button>

            <button @click="navigateToPage('lanhu_onlineask')">
              <img class="image_17" referrerpolicy="no-referrer"
                src="./assets/img/MasterDDSSlicePNG55c2b5eb3a82876422bebf61ba4527e8.png" />
            </button>

            <button @click="navigateToPage('lanhu_outpay')">
              <img class="image_18" referrerpolicy="no-referrer"
                src="./assets/img/MasterDDSSlicePNGc3447ad99920bdb7087e811ccb05e5d0.png" />
            </button>

          </div>
          <div class="text-wrapper_3  justify-between">
            <span class="text_13" @click="navigateToPage('lanhu_bookcall')">预约挂号</span>
            <span class="text_14" @click="navigateToPage('lanhu_onlineask')">在线问诊</span>
            <span class="text_15" @click="navigateToPage('lanhu_outpay')">门诊缴费</span>
          </div>
          <div class="box_5">
            <div class="group_3 flex-row">
              <span class="text_16">恭喜！重庆八一五医院被评为三甲医院！</span>
            </div>
          </div>
        </div>
        
      </div>
      <div class="block_5 flex-col">
        <div class="block_6 flex-row">
          <div class="image-text_9 flex-col justify-between" @click="navigateToPage('lanhu_index')">
            <img class="label_3" referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG2842f3f3a94e337935ea9437136b6b07.png" />
            <span class="text-group_11">首页</span>
          </div>
          <div class="image-text_10 flex-col justify-between" @click="navigateToPage('lanhu_myclinic')">
            <img class="label_4" referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG0c2614613e9e46d496069fa035db8fa2.png" />
            <span class="text-group_12">我的诊室</span>
          </div>
          <div class="image-text_11 flex-col justify-between" @click="navigateToPage('lanhu_messagecenter')">
            <img class="label_5" referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNGc2c269b95622ef5564b057c4b94bffa4.png" />
            <span class="text-group_13">信息中心</span>
          </div>
          <div class="image-text_12 flex-col justify-between" @click="navigateToPage('lanhu_personalcenter')">
            <img class="label_6" referrerpolicy="no-referrer"
              src="./assets/img/MasterDDSSlicePNG915a6b73b6430954e82fdb59ccebba61.png" />
            <span class="text-group_14">个人中心</span>
          </div>
        </div>
      </div>
      <img class="image_19" referrerpolicy="no-referrer"
        src="./assets/img/MasterDDSSlicePNGfc8a959c773a7e00b77cd7b6970eaf9a.png"
        @click="navigateToPage('lanhu_selectward')" />
      <span class="text_17">选取病房</span>
      <img class="image_20" referrerpolicy="no-referrer"
        src="./assets/img/MasterDDSSlicePNG73d5f3f18cba3def105dd2d9e3ae07ed.png" />
      <span class="text_18">自主开药</span>
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
          gender: null,
          phone: '',
          photo: '',
          age: null,
          inquiryTime: '',
          genderText: ''
        }
      };
    },
    methods: {
      navigateToPage(pageName) {
        // 实现页面跳转的逻辑
        // 这里的 pageName 可以根据传递的参数进行不同页面的跳转
        if (this.$route.name !== pageName) {
                    this.$router.push({ name: pageName });
        }
      },
      navigateToDestination() {
  
        // 构建高德地图导航 URL
        const url = `https://uri.amap.com/marker?position=106.29915, 29.592566`;
  
        // 跳转到高德地图
        window.location.href = url;
      },
      async fetchPatientInfo() {
        try {
          console.log("111111");
          const response = await axios.get('/patient/showpatient', {
            withCredentials: true
          });
          const data = response.data;

          if (data.code === 1 && data.data) {
            const patient = data.data.patient;

            this.patientInfo.username = patient.username;
            this.patientInfo.gender = patient.gender;
            this.patientInfo.phone = patient.phone;
            this.patientInfo.photo = patient.photo;
            this.patientInfo.inquiryTime = data.data.inquiryTime;

            // 设置性别文本
            if (patient.gender === 1) {
              this.patientInfo.genderText = '男';
            } else if (patient.gender === 0) {
              this.patientInfo.genderText = '女';
            } else {
              this.patientInfo.genderText = '';
            }

            // 计算年龄
            if (patient.idCard) {
              const birthYear = parseInt(patient.idCard.slice(6, 10), 10);
              const birthMonth = parseInt(patient.idCard.slice(10, 12), 10);
              const birthDay = parseInt(patient.idCard.slice(12, 14), 10);
              const currentDate = new Date('2024-08-30');
              let age = currentDate.getFullYear() - birthYear;

              // 判断是否已过生日
              const birthDateThisYear = new Date(currentDate.getFullYear(), birthMonth - 1, birthDay);
              if (currentDate < birthDateThisYear) {
                age--;
              }

              this.patientInfo.age = age;
            }
          } else {
            console.error('Failed to fetch patient data:', data.msg);
          }
        } catch (error) {
          console.error('Error fetching patient data:', error);
        }
      }
    },
    mounted() {
      this.fetchPatientInfo();
    }
  };
</script>

<style scoped lang="css" src="./assets/index.rem.css" />