<template>
  <div class="page flex-col">
    <!-- 页面顶部的部分 -->
    <div class="section_1 flex-row justify-between">
      <div class="box_1 flex-col">
        <span class="text_2">重庆八一五医院</span>
        <div class="section_2 flex-col">
          <div class="block_1 flex-col">
            <div class="section_3 flex-col"></div>
            <div class="section_4 flex-col">
              <img
                class="thumbnail_1"
                referrerpolicy="no-referrer"
                src="./assets/img/03fe70855abd4524bad5cf9d1229f60f_mergeImage.png"
              />
              <div class="box_2 flex-col"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="box_3 flex-col"></div>
      <img
        class="image_1"
        referrerpolicy="no-referrer"
        src="./assets/img/MasterDDSSlicePNGb1db302123ac8623f4098037eeb4265b.png"
      />
    </div>

    <!-- 验证码输入部分 -->
    <div class="section_5 flex-col">
      <span class="text_3">验证码</span>
      <div class="box_4 flex-row justify-between">
        <input
          v-model="code"
          type="text"
          maxlength="5"
          placeholder="请输入验证码"
          class="input-code"
        />
        <!-- 验证码图片 -->
        <img
		  class="yanzhengma"
          :src="captchaUrl" 
          alt="CAPTCHA" 
          @click="refreshCaptcha" 
          style="cursor: pointer;"
        >
      </div>
      <div class="box_6 flex-row">
        <!-- 其他内容 -->
        <div class="image-wrapper_1 flex-col">
          <img
            class="label_1"
            referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNGde3bf9797187020168cb472380dfd0b3.png"
          />
        </div>
        <span class="text_5">我已阅读并同意</span>
        <span class="text_6">《用户隐私政策》</span>
      </div>
      <!-- 登录按钮 -->
      <div class="text-wrapper_2 flex-col" @click="handleLogin">
        <span class="text_7">登陆</span>
      </div>
      <span class="text_8" @click="GoToSignUpPage">没有账号？注册账号</span>
    </div>

    <!-- 手机号和密码输入部分 -->
    <div class="section_6 flex-col">
      <span class="text_9">手机号</span>
      <input
        v-model="phone"
        type="tel"
        maxlength="11"
        placeholder="请输入手机号"
        class="input-phone"
		ref="phoneInput"
      />
      <span class="text_10">密码</span>
      <input
        v-model="password"
        type="password"
        placeholder="请输入密码"
        class="input-password"
      />
    </div>

    <!-- 底部图片或装饰 -->
    <img
      class="image_2"
      referrerpolicy="no-referrer"
      src="./assets/img/MasterDDSSlicePNGbb033cf614686a13a4ce0e36dcf87234.png"
    />

    <!-- 弹窗部分 -->
    <div v-if="showAlert" class="alert-popup">
      <div class="alert-content">
        <p>{{ alertMessage }}</p>
        <button @click="confirmAction">确定</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      phone: "",
      code: "",
      password: "",
      showAlert: false,
      alertMessage: "",
      captchaUrl: "/captcha?" + new Date().getTime(), // 初始化验证码URL
      nextAction: null, // 保存可能的后续操作
    };
  },
  methods: {
    async handleLogin() {
          if (!this.phone || !this.password || !this.code) {
            this.alertMessage = "手机号、密码或验证码不能为空";
            this.showAlert = true;
            return;
          }
      
          try {
            const formData = new URLSearchParams();
            formData.append('phone', this.phone);
            formData.append('password', this.password);
            formData.append('code', this.code);
      
            const response = await axios.post(
              '/login',
              formData,
              {
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                },
                withCredentials: true // 启用跨域携带凭证（如Cookie、Session）
              }
            );
      
            // 判断后端返回的code值
            if (response.data.code === 1) {
              const userRole = response.data.data.role;
              this.alertMessage = "登录成功";
              this.showAlert = true;
              this.nextAction = () => {
                if (userRole === 0) {
                  this.$router.push('/lanhu_index'); // 跳转到患者首页
                } else if (userRole === 1) {
                  this.$router.push('/lanhu_doctorindex'); // 跳转到医生首页
                }
              };
            } else {
              this.alertMessage = response.data.msg || "登录失败，请稍后再试";
              this.showAlert = true;
            }
          } catch (error) {
            this.alertMessage = "请求失败，请检查网络连接";
            this.showAlert = true;
          }
        },
    GoToSignUpPage() {
      this.$router.push('/lanhu_signuppage');
    },
    refreshCaptcha() {
      // 点击时重新生成带时间戳的URL，避免使用缓存
      this.captchaUrl = "/captcha?" + new Date().getTime();
    },
    confirmAction() {
      this.showAlert = false; // 点击“确定”按钮后关闭弹窗
      if (this.nextAction) {
        this.nextAction(); // 如果有后续操作，执行该操作
        this.nextAction = null; // 清空后续操作，避免重复执行
      }
      // 避免强制聚焦手机号输入框，只在用户初次进入页面时自动聚焦
    }
  }

};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
