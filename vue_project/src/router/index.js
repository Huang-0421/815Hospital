import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/views/layout/Index.vue'
import Login from '@/views/login/Index.vue'
import Home from '@/views/home/Home.vue'
// 订单管理
const User =()=>import('@/views/user/Index.vue')
const User_list =()=>import('@/views/user/list/Index.vue')


// 消息管理
const Advert =()=>import('@/views/advert/Index.vue')
const Advert_list =()=>import('@/views/advert/list/Index.vue')
// 消息管理
const Feedback =()=>import('@/views/feedback/Index.vue')
const Feedback_list =()=>import('@/views/feedback/list/Index.vue')
//仓库管理
const Storage =()=>import('@/views/storage/Index.vue')
const Storage_list =()=>import('@/views/storage/list/Index.vue')
//
const Income =()=>import('@/views/income/Index.vue')
const Income_list =()=>import('@/views/income/list/Index.vue')
// 病例管理
import Cottoms from "../views/cottoms/index.vue";
import cottomsList from "../views/cottoms/list/cottoms_list.vue";
import cottomsDetail from "../views/cottoms/detail/cottoms_detail.vue";
import cottomsAdd from "../views/cottoms/add/cottoms_add.vue";
//引入电子病例的组件

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login,
  },
  {
    path: '/home',
    component:Layout,
    children:[
      {
        path:'/',
        name:'home',
        component:Home
      },
      {
        path:'/user',  //用户管理
        name:'user',
        component:User,
        children:[
          {
            path:'list',
            name:'list',
            component:User_list
          }
        ]
      },
      {
        path:'/advert',  //消息管理
        name:'advert',
        component:Advert,
        children:[
          {
            path:'list',
            name:'list',
            component:Advert_list
          }
        ]
      },
      {
        path:'/feedback',  //消息管理
        name:'feedback',
        component:Feedback,
        children:[
          {
            path:'list',
            name:'list',
            component:Feedback_list
          }
        ]
      },

      {
        path:'/storage',
        name:'storage',
        component:Storage,
        children:[
          {
            path:'list',
            name:'list',
            component:Storage_list
          }
        ]
      },
      {
          path: "/cottoms",
          component: Cottoms,
          redirect: "/cottoms/list",
          children: [{
                  path: "list",
                  name:'list',
                  component: cottomsList,
              },
              {
                  path: "add",
                  name:'add',
                  component: cottomsAdd,
              },
              {
                  path: "detail",
                  name:'detail',
                  component: cottomsDetail,
              },
          ],
      },
	  {
	    path:'/income',
	    name:'income',
	    component:Income,
	    children:[
	      {
	        path:'list',
	        name:'list',
	        component:Income_list
	      },
	    ]
	  },
    ]
  },
  {
      path:'/login',
      name:'login',
      component:Login
  }
   
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router