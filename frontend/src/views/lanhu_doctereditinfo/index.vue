<template>
  <div class="page">
    <div class="nav-bar_1 flex-row">
      <img
        class="icon_1"
        referrerpolicy="no-referrer"
        src="./assets/img/MasterDDSSlicePNG28f960f38b3d337ffa33b5e11cb2e44b.png"
        @click="goBack"
      />
      <span class="text_1">个人信息修改</span>
    </div>
    <div class="section_1 flex-col">
      <div class="input-group">
        <label>编号</label>
        <input v-model="formData.id" readonly class="input" />
      </div>
      <div class="input-group">
        <label>电话</label>
        <input v-model="formData.phone" readonly class="input" />
      </div>
      <div class="input-group">
        <label>职称</label>
        <input v-model="formData.title" placeholder="请输入职称" class="input" />
      </div>
      <div class="input-group">
        <label>科室</label>
        <select v-model="formData.department" class="input">
          <option value="内科">内科</option>
          <option value="外科">外科</option>
          <option value="儿科">儿科</option>
          <option value="妇科">妇科</option>
          <option value="骨科">骨科</option>
        </select>
      </div>
      <div class="input-group">
        <label>头像</label>
        <input type="file" @change="handleImageUpload" class="input" />
      </div>
    </div>
    <div class="section_2 flex-col">
      <button class="button_1 flex-col" @click="submitDoctorInfo">
        <span class="text_4">提交</span>
      </button>
    </div>

    <!-- 自定义弹窗 -->
    <div v-if="showAlert" class="alert-popup">
      <div class="alert-content">
        <p>{{ alertMessage }}</p>
        <button @click="closeAlert">确定</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      formData: {
        id: "", // 医生编号
        phone: "", // 医生电话
        title: "", // 职称
        department: "", // 科室中文名
        image: null, // 头像文件
      },
      showAlert: false, // 控制弹窗显示
      alertMessage: "", // 弹窗消息内容
      departmentMapping: {
        "内科": "1826793026329628674",
        "外科": "1826793027415953410",
        "儿科": "1826793027415953411",
        "妇科": "1826793027478867970",
        "骨科": "1826793027478867972",
      },
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    async fetchDoctorInfo() {
      try {
        const response = await axios.get("/doctor/showme");
        if (response.data.code === 1) {
          const doctorData = response.data.data;
          this.formData.id = doctorData.id;
          this.formData.phone = doctorData.phone;
        }
      } catch (error) {
        console.error("获取医生信息失败:", error);
      }
    },
    handleImageUpload(event) {
      const file = event.target.files[0];
      this.formData.image = file;
    },
    async submitDoctorInfo() {
      const departmentId = this.departmentMapping[this.formData.department];
      const formData = new FormData();
      formData.append("title", this.formData.title);
      formData.append("departmentId", departmentId);
      if (this.formData.image) {
        formData.append("image", this.formData.image);
      }

      try {
        const response = await axios.post("/doctor/setinfo", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });

        if (response.data.code === 1) {
          this.alertMessage = "信息修改成功";
          this.showAlert = true;
        } else {
          this.alertMessage = "信息修改失败：" + response.data.msg;
          this.showAlert = true;
        }
      } catch (error) {
        console.error("提交信息失败:", error);
        this.alertMessage = "提交信息失败，请重试";
        this.showAlert = true;
      }
    },
    closeAlert() {
      this.showAlert = false;
      this.$router.go(-1); // 返回上一页
    }
  },
  mounted() {
    this.fetchDoctorInfo(); // 获取医生信息
  },
};
</script>

<style scoped>
.page {
  padding: 20px;
}

.input-group {
  margin-bottom: 20px;
}

.input-group label {
  display: block;
  margin-bottom: 5px;
}

.input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

.button_1 {
  padding: 10px;
  background-color: #49b9ad;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  text-align: center;
}

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
