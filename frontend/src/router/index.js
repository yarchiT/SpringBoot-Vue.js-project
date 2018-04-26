import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/Home'
import About from '@/components/About'

import login from '../components/login/login.vue';
import signUp from '../components/signup/signup.vue';
import admin from '../components/admin/admin.vue';
import userTimeline from '../components/user/timeline.vue';
import userProfile from '../components/user/profile.vue';
import edit from '../components/user/edit';

import error from '../components/error.vue';

import {Navbar} from 'bootstrap-vue/es/components';

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
      name:'signup',
      path:'/signup',
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
    name: 'userProfile',
    path: '/profile',
    component: userProfile,
    meta: {requiresAuth: true, adminAuth: false, userAuth: true}
  },
  {

    name: 'userTimeline',
    path: '/timeline',
    component: userTimeline,
    meta: {requiresAuth: true, adminAuth: false, userAuth: true}
  },
  {
	  name: 'edit',
	  path: '/edit',
	  component: edit,
	  meta: {requiresAuth: true, adminAuth: false, userAuth: true}
  },
  {
     name:'error',
     path:'/error',
     component:error
   }
  ];

const router = new VueRouter({routes, mode: 'history'})

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    const authUser = JSON.parse(window.localStorage.getItem('lbUser'));
    next();
    if(!authUser || !authUser.token) {
          next({name:'login'})
    }else{
      next();
        }
  } else {
    next()
  }
});


export default router;

