<template>  
  <div class="block">  
    <div class="title">  
      <span class="demonstration">请选择消息接受的对象</span>  
    </div>  
    <div class="selectbox">  
      <el-cascader  
        placeholder="输入可搜索"  
        :options="options"  
        :props="{ multiple: true }"  
        v-model="selectedRecipients" 
        filterable>  
      </el-cascader>  
    </div>  
    <div class="inputbox">  
      <el-input  
        type="textarea"  
        :autosize="{ minRows: 35,maxRows: 40}" 
        placeholder="请输入消息内容"  
        v-model="textarea1">  
      </el-input>  
    </div>  
    
    <div class="bottom_button">  
      <el-button type="primary" @click="sendmsg" plain>发送消息</el-button>  
    </div>  
  </div>  
</template>  

<script>  
import axios from 'axios';  
import qs from 'qs'; // 引入qs库，用于序列化form表单数据

export default {  
  data() {  
    return {  
      textarea1: '',  
      options: [], // 初始化为空数组  
      selectedRecipients: [] // 用于存储选择的收件人  
    };  
  },  
  mounted() {  
    this.fetchOptions(); // 组件挂载后调用fetchOptions方法  
  },  
  methods: {  
    async fetchOptions() {  
      try {  
        const response = await axios.get('/admin/showalldoctor'); // 请求后端API  
        if (response.data.code === 1) {
          // 将获取到的数据转换为 Cascader 组件可识别的格式
          this.options = response.data.data.map(name => ({ label: name, value: name })); 
        }
      } catch (error) {  
        console.error('获取医生列表失败:', error); // 错误处理  
      }  
    },  
    async sendmsg() {  
      const payload = {
        doctorName: this.selectedRecipients.join(','), // 将选择的医生名称数组转换为逗号分隔的字符串
        content: this.textarea1 // 消息内容  
      };  
      
      try {  
        const response = await axios.post('/admin/sendmsg', qs.stringify(payload), {
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }); // 发送消息到后端，使用form表单格式

        if (response.data.code === 1) {
          // 弹出成功通知
          this.$notify({
            title: '成功',
            message: `成功向${this.selectedRecipients.join(', ')}发送消息`,
            type: 'success'
          });

          // 清除已选医生
          this.selectedRecipients = [];

          // 清空文本框内容
          this.textarea1 = '';
          
          console.log('消息发送成功:', response.data.msg); // 处理成功的数据  
        } else {
          console.error('发送消息失败:', response.data.msg); // 处理失败的情况
        }
      } catch (error) {  
        console.error('发送消息失败:', error); // 错误处理  
      }  
    }  
  }  
};  
</script>  

<style>  
.title{  
  margin-left:3px;  
  margin-bottom:10px;  
}  
.inputbox{  
  margin-top:10px;  
  margin-bottom:10px;  
}  
.bottom_button{  
  position:fixed;  
  left:55%;  
  transform: translateX(-50%);   
  bottom:10px;  
}  
</style>
