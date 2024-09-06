<template>
  <div class="clearfix" id="login_wrap">
    <div id="login">
      <h2 class="title">登录页</h2>
      <div class="login--account">
        <span>账号：</span>
        <input type="text" placeholder="输入用户名" name="account" v-model.trim="username" />
      </div>
      <div class="login--password">
        <span>密码：</span>
        <input type="password" placeholder="输入密码" name="password" v-model.trim="password" />
      </div>
      <p class="login--btn">
        <el-button :plain="true" id="loginBtn" type="primary" @click="login">登录</el-button>
      </p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    login() {
      // 发送 POST 请求到指定的 URL
      axios
        .post(
          "/admin/login",
          new URLSearchParams({
            username: this.username,
            password: this.password,
          })
        )
        .then((response) => {
          if (response.data.code === 1) {
            this.$message('这是一条消息提示');
            if (this.$route.path !== "/home") {
              this.$router.push("/home");
            };
            location.reload();
          } else {
            // alert(response.data.msg);
            this.$message(response.data.msg);
          }
        });
    },
  },
};
</script>

<style scoped lang="less">
.title {
  text-align: center;
  font-size: 22px;
}

#login_wrap {
  position: relative;
  background-image: url('./bg.png');
  background-size: cover;
  height: 100%;

  >div {
    background: #fff;
    border-radius: 15px;
    width: 420px;
    height: 300px;
    padding: 30px 40px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    >div {
      padding: 10px 0;
      border-bottom: 1px solid #ddd;

      &.login--account {
        margin: 25px 0 30px;
      }

      span {
        color: #666;
        display: inline-block;
        width: 84px;
        font-size: 20px;
      }

      input {
        background: none;
        font-size: 16px;
        border: none;
        height: 30px;
        width: 280px;
        padding-left: 12px;
        box-sizing: border-box;
        color: #666;

        &.error {
          border: 1px solid #f00;
        }

        &::-webkit-input-placeholder {
          color: #aaa;
        }
      }
    }

    p {
      text-align: right;

      &.login--btn {
        button {
          width: 100%;
          height: 50px;
          font-size: 18px;
          // border: none;
          margin-top: 30px;
          // color: #fff;
          border-radius: 6px;
          // cursor: pointer;
        }
      }

      a {
        color: #fff;
        display: inline-block;
        padding: 0 15px;
        font-size: 14px;
      }
    }
  }
}

.info {
  color: #999;
  margin-top: 8px;
  text-align: center !important;
}
</style>