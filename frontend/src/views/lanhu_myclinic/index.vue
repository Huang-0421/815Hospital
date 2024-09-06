<template>
  <div class="page flex-col">
    <div class="group_1 flex-col">
      <div class="group_2 flex-col">
        <div class="section_1 flex-col">
          <div class="group_3 flex-col">
            <div class="nav-bar_1 flex-row">
              <span class="text_1">我的诊室</span>
            </div>
            <div class="section_2 ">
              <div
                class="box_1 flex-col justify-between"
                v-for="(item, index) in clinicList"
                :key="index"
              >
                <div class="section_4 flex-col justify-end">
                  <div class="block_4 flex-row justify-between">
                    <span class="text_10">{{ item.department }}</span>
                    <div class="tag_1 flex-col">
                      <span class="text_11">{{ item.flag === 1 ? '已完成' : '未完成' }}</span>
                    </div>
                  </div>
                  <div class="block_5 flex-col"></div>
                  <div class="block_6 flex-row justify-between">
                    <div class="text-wrapper_2 flex-col justify-between">
                      <span class="text_12">医生</span>
                      <span class="text_13">看病地点</span>
                      <span class="text_14">看病时间</span>
                    </div>
                    <div class="text-group_2 flex-col justify-between">
                      <span class="text_15">{{ item.doctorName }}</span>
                      <span class="text_16">{{ item.location }}</span>
                      <span class="text_17">{{ item.inquiryTime }}</span>
                    </div>
                  </div>
                  <div class="block_7 flex-col"></div>
                  <div class="icon_3 flex-col">
                    <div class="section_5 flex-col">
                      <div class="group_5 flex-col"></div>
                    </div>
                  </div>
                </div>
              </div>

              <div class="block_8 flex-col">
                <div class="block_9 flex-row">
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
                      src="./assets/img/img_v3_02e3_681167e4-be70-4b34-a1a0-f09b26098e1g.png"
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
                      src="./assets/img/MasterDDSSlicePNGcb29cb81cdff45f814dcea4c115387cb.png"
                    />
                    <span class="text-group_14">个人中心</span>
                  </div>
                </div>
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
        clinicList: [], // 存储挂号清单
      };
    },
    methods: {
      navigateToPage(pageName) {
       if (this.$route.name !== pageName) {
                   this.$router.push({ name: pageName });
                 }
      },
      fetchClinicList() {
        axios.get('/patient/showreg')
          .then(response => {
            if (response.data.code === 1) {
              this.clinicList = response.data.data.map(item => {
                return {
                  department: item.department,
                  flag: item.flag,
                  doctorName: item.doctorName,
                  inquiryTime: item.inquiryTime,
                  location: this.getLocationByDepartment(item.department),
                };
              });
            } else {
              console.error('获取挂号清单失败', response.data.msg);
            }
          })
          .catch(error => {
            console.error('请求挂号清单时出错', error);
          });
      },
      getLocationByDepartment(department) {
        const locationMap = {
          '内科': '101',
          '外科': '201',
          '妇科': '301',
          '儿科': '401',
          '骨科': '501'
        };
        return locationMap[department] || '未知地点';
      },
    },
    mounted() {
      this.fetchClinicList();
    }
  };
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
