import Vue from 'vue'
import VueRouter from 'vue-router'

// 引入组件
import login from '@/models/login.vue'
import casLogin from '@/models/casLogin.vue'
import home from '@/models/home/homeBridging.vue'

// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    // redirect: 'casLogin'
    redirect: 'login'
  },
  {
    path: `/login`,
    component: login
  },
  {
    path: `/casLogin`,
    component: casLogin
  },
  {
    path: `/home`,
    component: home,
    // props: { menuAlign: 'top' },//left==>菜单在左侧 top==>菜单在上方
    props: { menuAlign: 'left' },//left==>菜单在左侧 top==>菜单在上方
    children: [
      {
        path: `/welcome`,
        component: () => import("@/models/home/welcome")
      },
      {
        name: 'auth',
        path: '/auth',
        component: () => import('@/models/sys/main'),
        children:[
          {
            name: 'menu',
            path: '/sys/menu',
            component: () => import('@/models/sys/menu/menuMain')
          },
          {
            name: 'user',
            path: '/sys/user',
            component: () => import('@/models/sys/user/userMain')
          },
          {
            name: 'supervisionUser',
            path: '/sys/supervisionUser',
            component: () => import('@/models/sys/user/supervisionUserMain')
          },
          {
            name: 'role',
            path: '/sys/role',
            component: () => import('@/models/sys/role/roleMain')
          },
          {
            name: 'origin',
            path: '/sys/origin',
            component: () => import('@/models/sys/origin/originMain')
          }

        ]
      },
      {
        name: 'record',
        path: '/record',
        component: () => import('@/models/record/main'),
        children: [
          {
            name: 'reportDefined',
            path: '/record/reportDefined',
            component: () => import('@/models/record/reportDefined'),
            children: [
              {
                name: 'reportDefinedMain',
                path: '/record/reportDefined/reportDefinedMain',
                component: () => import('@/models/record/reportDefined/base/reportDefinedMain'),
                children: []
              },
              {
                name: 'reportDefinedUnit',
                path: '/record/reportDefined/unitMain',
                component: () => import('@/models/record/reportDefined/unit/unitMain'),
                children: []
              },
              {
                name: 'oneDimensionsStatic',
                path: '/record/reportDefined/oneDimensionsStatic',
                component: () => import('@/models//record/reportDefined/unit/oneDimensionsStatic/main'),
                children: []
              }
              ,
              {
                name: 'oneDimensionsStaticAdd',
                path: '/record/reportDefined/oneDimensionsStatic/add',
                component: () => import('@/models//record/reportDefined/unit/oneDimensionsStatic/add'),
                children: []
              },
              {
                name: 'oneDimensionsStaticEdit',
                path: '/record/reportDefined/oneDimensionsStatic/edit',
                component: () => import('@/models//record/reportDefined/unit/oneDimensionsStatic/edit'),
                children: []
              },
              {
                name: 'oneDimensionsDynamic',
                  path: '/record/reportDefined/oneDimensionsDynamic',
                component: () => import('@/models//record/reportDefined/unit/oneDimensionsDynamic/main'),
                children: []
              },
              {
                name: 'oneDimensionsDynamicAdd',
                  path: '/record/reportDefined/oneDimensionsDynamic/add',
                component: () => import('@/models//record/reportDefined/unit/oneDimensionsDynamic/add'),
                children: []
              },
              {
                name: 'oneDimensionsDynamicEdit',
                  path: '/record/reportDefined/oneDimensionsDynamic/edit',
                component: () => import('@/models//record/reportDefined/unit/oneDimensionsDynamic/edit'),
                children: []
              },
              {
                name: 'reportFill',
                path: '/record/report/reportFill',
                component: () => import('@/models/record/report/reportFill'),
                children: [
                  {
                    name: 'oneDimensionsStaticRecord',
                    path: '/record/onedim/onedimRecord',
                    component: () => import('@/models//record/report/onedim/onedimRecord'),
                  },
                  {
                    name: 'treeDimensionsDynRecord',
                    path: '/record/treedim/treedimRecord',
                    component: () => import('@/models//record/report/treedim/treedimRecord'),
                  },
                  {
                    name: 'oneDimensionsDynamicRecord',
                    path: '/record/groupdim/groupdimRecord',
                    component: () => import('@/models//record/report/groupdim/groupdimRecord'),
                  },
                  {
                    name: 'multDimensionsStaticRecord',
                    path: '/record/multdim/griddimRecord',
                    component: () => import('@/models//record/report/multdim/griddimRecord'),
                  }
                ]
              },

              {
                name: 'oneDimensionsStaticRecord',
                path: '/record/onedim/onedimRecord',
                component: () => import('@/models//record/report/onedim/onedimRecord'),
                children: []
              },
              {
                name: 'multDimensionsStatic',
                path: '/record/reportDefined/multDimensionsStatic',
                component: () => import('@/models//record/reportDefined/unit/multDimensionsStatic/main'),
                children: []
              },
              {
                name: 'treeMultDiensionsDynamic',
                path: '/record/reportDefined/treeMultDiensionsDynamic',
                component: () => import('@/models//record/reportDefined/unit/treeMultDiensionsDynamic/main'),
                children: []
              },
              {
                name: 'multDimensionsStaticAdd',
                path: '/record/reportDefined/multDimensionsStatic/add',
                component: () => import('@/models//record/reportDefined/unit/multDimensionsStatic/add'),
                children: []
              },
              {
                name: 'multDimensionsStaticEdit',
                path: '/record/reportDefined/multDimensionsStatic/edit',
                component: () => import('@/models//record/reportDefined/unit/multDimensionsStatic/edit'),
                children: []
              }
            ]
          },
          // {
          //   name: 'templateView',
          //   path: '/record/template/templateView',
          //   component: () => import('@/models/record/template/templateView')
          // },
          // {
          //   name: 'templateEdit',
          //   path: '/record/template/templateEdit',
          //   component: () => import('@/models/record/template/templateEdit')
          // },
          // {
          //   name: 'reportCreate',
          //   path: '/record/report/edit',
          //   component: () => import('@/models/record/report/reportEdit')
          // },
          // {
          //   name: 'reviewMain',
          //   path: '/record/review',
          //   component: () => import('@/models/record/approve/reviewMain')
          // },
          // {
          //   name: 'confirm',
          //   path: '/record/confirm',
          //   component: () => import('@/models/record/approve/confirmMain')
          // },
          {
            name: 'reportStepsMain',
            path: '/record/reportSteps',
            component: () => import('@/models/record/report/reportStepsMain'),
            children: []
          },{
            name: 'stepsEdit',
            path: '/record/stepsEdit',
            component: () => import('@/models/record/report/stepsEdit'),
            children: []
          },{
            name: 'submitAUmanager',
            path: '/record/submitAUmanager',
            component: () => import('@/models/record/submitAuthority/submitAUmanager'),
            children: []
          },{
            name: 'administrative',
            path: '/record/administrative',
            component: () => import('@/models/record/submitAuthority/administrative'),
            children: []
          },{
            name: 'reportUnit',
            path: '/record/reportUnit',
            component: () => import('@/models/record/reportDefined/unit/reportunit'),
            children: []
          },{
            name: 'reportStatements',
            path: '/record/reportStatements',
            component: () => import('@/models/record/reportDefined/unit/reportstatements'),
            children: []
          },{
            name: 'reportApproval',
            path: '/record/reportApproval',
            component: () => import('@/models/record/submitAuthority/reportapproval'),
            children: []
          },{
            name: 'reportSupervision',
            path: '/record/reportSupervision',
            component: () => import('@/models/record/submitAuthority/reportsupervision'),
            children: []
          }
        ]
      }
    ]
  }
]

var router =  new VueRouter({
  routes
})
export default router
