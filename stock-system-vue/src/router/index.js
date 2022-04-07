import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [{
    path: '/login',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/',
    name: 'main',
    meta: {
      requireAuth: true
    },
    component: () => import('../views/Main.vue'),
    children: [{
        path: 'dashboard',
        component: () => import('../views/Dashboard.vue')
      },
      {
        path: 'about',
        component: () => import('../views/AboutView.vue')
      },
      {
        path: 'qs',
        component: () => import('../views/Questionnaire.vue')
      }
    ]
  },
  {
    path: '/404',
    component: () => import('../views/404.vue'),
    hidden: true
  },
  {
    path: '*',
    redirect: '/404',
    hidden: true
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requireAuth)) { // 判断该路由是否需要登录权限
    //  if (localStorage.token) { // 判断当前的token是否存在 ； 登录存入的token
    //   next({
    //     path: '/login'
    //   });
    //  }
    //  else {
    //   next({
    //    path: '/',
    //    query: {redirect: to.fullPath} // 将跳转的路由path作为参数，登录成功后跳转到该路由
    //   })
    //  }
    // sessionStorage.clear()
    if (sessionStorage.getItem("user") != null) {
      next();
    } else {
      alert('请先登录')
      next({
        path: '/login'
      });
    }
  } else {
    next();
  }
});

export default router