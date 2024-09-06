<template>
  <div class="page flex-col">
    <div class="box_1 flex-row justify-between">
      <div class="text-wrapper_1 flex-col">
        <span class="text_1">注册</span>
        <span class="text_2">重庆八一五医院</span>
      </div>
      <div class="box_2 flex-col">
        <div class="box_3 flex-col">
          <div class="box_4 flex-col"></div>
          <div class="box_5 flex-col">
            <img
              class="thumbnail_1"
              referrerpolicy="no-referrer"
              src="./assets/img/89346fb9c26642a8af2f63c2fe3d03a9_mergeImage.png"
            />
            <div class="section_1 flex-col"></div>
          </div>
        </div>
      </div>
      <img
        class="image_1"
        referrerpolicy="no-referrer"
        src="./assets/img/MasterDDSSlicePNGb1db302123ac8623f4098037eeb4265b.png"
      />
    </div>
    <div class="box_6 flex-col">
      <span class="text_3">密码</span>
      <input
        v-model="password"
        type="password"
        placeholder="请输入密码"
        class="input-password"
      />
      <span class="text_5">再次输入密码</span>
      <input
        v-model="confirmPassword"
        type="password"
        placeholder="再次输入密码"
        class="input-password"
      />
      <div class="box_7 flex-row">
        <div class="image-wrapper_1 flex-col">
          <img
            class="label_1"
            referrerpolicy="no-referrer"
            src="./assets/img/MasterDDSSlicePNGde3bf9797187020168cb472380dfd0b3.png"
          />
        </div>
        <span class="text_7">我已阅读并同意</span>
        <span class="text_8">《用户隐私政策》</span>
      </div>
      <div class="text-wrapper_4 flex-col" @click="handleRegistration">
        <span class="text_9">完成注册</span>
      </div>
    </div>
    <div class="box_8 flex-col">
      <span class="text_10">手机号</span>
      <div class="section_2 flex-row justify-between">
        <input
          v-model="phone"
          type="tel"
          maxlength="11"
          placeholder="请输入手机号"
          class="input-phone"
        />
        <button class="text-wrapper_5 flex-col" @click="sendMessage">
          <span class="text_11">发送验证码</span>
        </button>
      </div>
      <span class="text_12">验证码</span>
      <input
        v-model="code"
        type="text"
        maxlength="4"
        placeholder="请输入验证码"
        class="input-code"
      />
    </div>
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
      confirmPassword: "",
      showAlert: false, // 控制弹窗显示
      alertMessage: "" // 弹窗显示的内容
    };
  },
  methods: {
    async sendMessage() {
      if (!this.phone || this.phone.length !== 11) {
        this.alertMessage = "请输入正确的手机号";
        this.showAlert = true;
        return;
      }
    
      try {
        const formData = new URLSearchParams();
        formData.append('phoneNum', this.phone);
    
        const response = await axios.post(
          'http://192.168.1.191:8080/sendSms',
          formData, 
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          }
        );
    
        // 判断后端返回的code值
        if (response.data.code === 1) {
          this.alertMessage = "验证码已发送";
        } else if (response.data.code === 0) {
          this.alertMessage = "网络问题，验证码发送失败";
        } else {
          this.alertMessage = response.data.msg || "验证码发送失败";
        }
      } catch (error) {
        this.alertMessage = "请求失败，请检查网络连接";
      } finally {
        this.showAlert = true;
      }
    },
    async handleRegistration() {
      // 先检查两次密码是否一致
      if (this.password !== this.confirmPassword) {
        this.alertMessage = "两次输入的密码不一致";
        this.showAlert = true;
        return;
      }

      // 检查手机号和验证码是否填写
      if (!this.phone || !this.password || !this.code) {
        this.alertMessage = "请填写完整的注册信息";
        this.showAlert = true;
        return;
      }

      try {
        const formData = new URLSearchParams();
        formData.append('phone', this.phone);
        formData.append('password', this.password);
        formData.append('messageCode', this.code);

        const response = await axios.post(
          'http://192.168.1.191:8080/sign',
          formData,
          {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
              withCredentials: true // 允许跨域请求携带cookie/session
            }
          }
        );

        // 判断后端返回的code值
        if (response.data.code === 1) {
          this.alertMessage = "注册成功";
          this.showAlert = true;
          this.flag = 1; // 设置 flag 表示成功
        } else {
          this.alertMessage = response.data.msg || "注册失败，请稍后再试";
          this.showAlert = true;
        }
      } catch (error) {
        this.alertMessage = "请求失败，请检查网络连接";
        this.showAlert = true;
      }
    },
    confirmAction() {
      if (this.flag === 1) {
        // 注册成功，跳转到登录页面
        this.$router.push("/lanhu_signinpage");
      }
      // 无论成功或失败，关闭弹窗
      this.showAlert = false;
    },
  }
};
</script>

<style scoped lang="css" src="./assets/index.rem.css" />
