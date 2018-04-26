import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home'
import About from '@/components/About'

import login from '../components/login/login.vue';
import signUp from '../components/signup/signup.vue';
import admin from '../components/admin/admin.vue';
import user from '../components/user/user.vue';
import userTimeline from '../components/user/timeline.vue';
import userProfile from '../components/user/profile.vue';
import edit from '../components/user/edit';

import error from '../components/user/user.vue';

import { Navbar } from 'bootstrap-vue/es/components';

Vue.use(VueRouter);
Vue.use(Navbar);


 const routes = [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
     path: '/home',
     name: 'home',
     component: Home
    },
    {
      name:'login',
      path:'/login',
      component:login
    },
    {
      name:'signUp',
      path:'/signUp',
      component:signUp
    },
    {
      path: '/about',
      name: 'about',
      component: About
    },
    {
      name:'admin',
      path:'/admin',
      component:admin,
      meta: { requiresAuth: true , adminAuth:true, userAuth : false}
    },
    {
      name:'user',
      path:'/user',
      component:user,
      meta: { requiresAuth: true , adminAuth:false, userAuth:true},
      children: [
        {
          // UserProfile will be rendered inside User's <router-view>
          // when /user/:id/profile is matched
          path: '/profile',
          component: userProfile
        },
        {
          // UserTimeline will be rendered inside User's <router-view>
          // when /user/:id/timeline is matched
          path: '/timeline',
          component: userTimeline
        },
        {
          path: '/edit',
          component: edit
        }
      ]
    },
   {
     name:'error',
     path:'/error',
     component:error
   }
  ];

const router = new VueRouter({routes,mode:'history'})

router.beforeEach((to, from, next) => {
  if(to.meta.requiresAuth) {
    const authUser = JSON.parse(window.localStorage.getItem('lbUser'))
    next();
    //todo: when add user token should refactor this code
/*    if(!authUser || !authUser.token) {
      next({name:'login'})
    }
    else if(to.meta.adminAuth) {
      if(authUser.data.role_id === 'admin') {
        next()
      }else {
        next('/user')
      }
    } else if(to.meta.userAuth) {
      if(authUser.data.role_id === 'user') {
        console.log('Im in user');
        next()
      }else {
        console.log('Im in admin');
        next('/admin')
      }
    }*/
  }else {
    next()
  }
});


export default router;

