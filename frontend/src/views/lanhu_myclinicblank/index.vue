<template>
  <div class="page flex-col">
    <div class="section_1 flex-row">
      <div class="group_1 flex-col">
        <div class="group_2 flex-row">
          <div class="image-text_1 flex-col justify-between" @click="navigateTo('/lanhu_index')">
                      <img
                        class="label_1"
                        referrerpolicy="no-referrer"
                        src="./assets/img/MasterDDSSlicePNG9f3d68b1f1d142a593ba918208195e93.png"
                      />
                      <span class="text-group_1">首页</span>
                    </div>
           <div class="image-text_2 flex-col justify-between" @click="checkFlagAndNavigate">
                                <img
                                  class="label_2"
                                  referrerpolicy="no-referrer"
                                  src="./assets/img/MasterDDSSlicePNG604e7dab168dd2e6b62d4dbdaf93263b.png"
                                />
                                <span class="text-group_2">我的诊室</span>
                  </div>
                    <div class="image-text_3 flex-col justify-between" @click="navigateTo('/lanhu_messagecenter')">
                      <img
                        class="label_3"
                        referrerpolicy="no-referrer"
                        src="./assets/img/MasterDDSSlicePNG47b3ad6e970da7ba0f1d1d5bfbace999.png"
                      />
                      <span class="text-group_3">信息中心</span>
                    </div>
          <div class="image-text_4 flex-col justify-between" @click="navigateTo('/lanhu_personalcenter')">
                      <img
                        class="label_4"
                        referrerpolicy="no-referrer"
                        src="./assets/img/MasterDDSSlicePNGcb29cb81cdff45f814dcea4c115387cb.png"
                      />
                      <span class="text-group_4">个人中心</span>
                    </div>
                  </div>
                </div>
                <div class="group_3 flex-col">
                  <span class="text_1">我的诊室</span>
                  <img
                    class="image_1"
                    referrerpolicy="no-referrer"
                    src="./assets/img/web__暂无记录.png"
                  />
                  <span class="text_2">暂无门诊</span>
                </div>
              </div>
            </div>
          </template>
          
          <script>
          export default {
            data() {
              return {
                constants: {}
              };
            },
			methods: {
			    navigateTo(url) {
			      if (url) {
			        this.$router.push(url);
			      }
			    },
				checkFlagAndNavigate() {
				      // 假设从后端获取的flag值已经存储在data中
					  this.flag=true;
				      if (this.flag) {
				        this.navigateTo('./lanhu_myclinic');
				      } else {
				        this.navigateTo('./lanhu_myclinicblank');
				      }
				    }
			  }
			};
			</script>
<style scoped lang="css" src="./assets/index.rem.css" /><style scoped lang="css" src="./assets/index.rem.css" />