<template>
    <div>
        <el-menu :default-active="active" class="el-menu-vertical-demo"
      background-color="#112f50" text-color="#fff" active-text-color="#ffd04b" router :collapse="isCollapse"> 
      <el-menu-item>
        <span slot="title">后台管理系统</span>
      </el-menu-item>
<!-- 111 -->
      <el-menu-item index="/home">
        <i class="el-icon-menu"></i>
        <span slot="title">首页</span>
      </el-menu-item>
<!-- 222 -->
      <el-submenu index="/user">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span>医护人员管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/user/list" >
            <i class="el-icon-s-custom"></i>
            <span slot="title">医护人员列表</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <!-- 333 -->
      <!-- <el-submenu index="/storage">
        <template slot="title">
          <i class="el-icon-office-building"></i>
          <span>科室管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/product/list" >
            <i class="el-icon-office-building"></i>
            <span slot="title">科室列表</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu> -->
<!-- 444 -->
      <!-- 消息通知 -->
      <el-submenu index="/advert">
        <template slot="title">
          <i class="el-icon-document"></i>
          <span>消息管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/advert/list" >
            <i class="el-icon-document"></i>
            <span slot="title">消息通知</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>

<!-- 反馈查看 -->
<el-submenu index="/feedback">
        <template slot="title">
          <i class="el-icon-document"></i>
          <span>反馈查看</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/feedback/list" >
            <i class="el-icon-document"></i>
            <span slot="title">反馈列表</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
<!-- 555 -->
      <el-submenu index="/icon">
        <template slot="title">
          <i class="el-icon-s-management"></i>
          <span>药品管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/storage/list" >
            <i class="el-icon-s-management"></i>
            <span slot="title">药品列表</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>
<!-- 666 -->
      <el-submenu index="/income">
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span>收支管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/income/list" >
            <i class="el-icon-s-order"></i>
            <span slot="title">流水记录</span>
          </el-menu-item>
        </el-menu-item-group>
      </el-submenu>

    </el-menu>
    </div>
</template>
<script>
export default {
   data(){
      return{
        active:''
      }
   },
   props:['isCollapse'],
   created(){
      // console.log(this.$route);
      //首次进去页面处理路由
      if(this.$route.meta.activeMenu){
        this.active = this.$route.meta.activeMenu;
      }else{
        this.active = this.$route.path;
      }
   },
   watch:{//监听路由
    //监听路由的变化---切换页面组件
      $route(to,from){
          // console.log("to---",to);
          //判断当前的路由里面 的meta:{activeMenu:'/product/list'}
          let {meta,path} = to;
          console.log('meta',meta);
          console.log('path',path);
          if(meta.activeMenu){
              this.active = meta.activeMenu;
          }else{
              this.active = path;
          }
      }
   }

}
</script>
<style lang="less" scoped>
.el-menu{
    border-right: 0;
    .is-active{
        background: #1e78bf !important;
        color: #fff !important;
    }
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
</style>