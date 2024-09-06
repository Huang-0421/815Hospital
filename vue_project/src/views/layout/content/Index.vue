<template>
    <div>
        <!-- 顶部 -->
        <div class="content-header">
            <div class="open">
                <span v-if="!isCollapse" class="iconfont icon-right-indent" @click="changeMenu"></span>
                <span v-else class="iconfont icon-left-indent" @click="changeMenu"></span>
            </div>
             <!-- 右侧内容--时间 登录信息等 -->
            <div class="right">
                <span>{{nowTime}}</span> | 
                <span>欢迎 管理员</span> | 
                <span class="el-icon-switch-button icon" @click="loginOut"></span>
            </div>
        </div>
       

        <!-- 内容 -->
        <div class="wrapper" style="height: 760px;">
            <router-view></router-view>
        </div>
    </div>
</template>


<script>

//导入dayjs
import dayjs from 'dayjs'
export default {
    props:['isCollapse'],
    data(){
        time:''
        username:'admin'
        nowTime:''
    },
    methods:{
        changeMenu(){
            this.$emit('changeShow')
        },
        loginOut(){
            //进入登录界面
            this.$router.replace('/login')
            //清空菜单导航
            console.log("退出登录")
        }
    },
    created(){
        //通过dayjs获取当前时间并格式化成自己需要的格式
        this.nowTime = dayjs(new Date()).format('YYYY-MM-DD HH:mm:ss')
    }
}
</script>
<style>
.wrapper{
    padding: 10px;
    background: #eee;
}

.content-header{
    height: 50px;
    background: #1e78bf;
    color: #fff;
    line-height: 50px;
    display: flex;
    .open{
        .iconfont{
            font-size: 22px;
            cursor: pointer !important;
        }
       
    }
    .right {
        flex: 1;
        text-align: right;
        width: 380px;
        font-size: 12px;
        padding-right: 20px;
        span{
        padding:0 4px;
        }
        .icon{
        font-size: 16px;
        }
  }
}
</style>