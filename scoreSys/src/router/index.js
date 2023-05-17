import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Layout from '../views/Layout/index.vue'
import ChangePassword from '../views/Persondata/ChangePassword.vue';
import EditStudent from '../views/StudentManage/EditStudent.vue';
import EditTeacher from '../views/TeacherManage/EditTeacher.vue';
import EditCourse from '../views/CourseManage/EditCourse.vue';
import EditStudentScore from '../views/Score/EditStudentScore.vue';

// 异步
// 子级路由
const Score = ()=>import('../views/Score/Score.vue')
const Persondata = ()=>import('../views/Persondata/Persondata.vue')
const AddStudent = ()=>import('../views/StudentManage/AddStudent.vue')
const StudentTable = ()=>import('../views/StudentManage/StudentTable.vue')
const EnterGrade = ()=>import('../views/StudentManage/EnterGrade.vue')
const AddTeacher = ()=>import('../views/TeacherManage/AddTeacher.vue')
const TeacherTable = ()=>import('../views/TeacherManage/TeacherTable.vue')
const AddCourse = ()=>import('../views/CourseManage/AddCourse.vue')
const CourseTable = ()=>import('../views/CourseManage/CourseTable.vue')

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    meta:{title:'登录'},
  },
  {
    path: '/Register',
    name: 'Register',
    component: Register,
    meta:{title:'注册'},
  },
  {
    path: '/ChangePassword',
    name: 'ChangePassword',
    component: ChangePassword,
    meta:{title:'修改密码'},
  },
  {
    path: '/EditStudent',
    name: 'EditStudent',
    component: EditStudent,
    meta:{title:'编辑学生信息'},
  },
  {
    path: '/EditTeacher',
    name: 'EditTeacher',
    component: EditTeacher,
    meta:{title:'编辑教师信息'},
  },
  {
    path: '/EditCourse',
    name: 'EditCourse',
    component: EditCourse,
    meta:{title:'编辑课程信息'},
  },
  {
    path: '/EditStudentScore',
    name: 'EditStudentScore',
    component: EditStudentScore,
    meta:{title:'编辑学生成绩信息'},
  },
  {
    path:'/Layout',
    component:Layout,
    children:[
      {
        path:'/Score',
        name:'Score',
        component:Score,
        meta:{title:'学生成绩'},
      },{
        path:'/Persondata',
        name:'Persondata',
        component:Persondata,
        meta:{title:'个人中心'},
      },{
        path:'/StudentTable',
        name:'StudentTable',
        component:StudentTable,
        meta:{title:'学生列表'},
      },{
        path:'/AddStudent',
        name:'AddStudent',
        component:AddStudent,
        meta:{title:'添加学生'},
      },{
        path:'/EnterGrade',
        name:'EnterGrade',
        component:EnterGrade,
        meta:{title:'录入成绩'},
      },
      {
        path:'/TeacherTable',
        name:'TeacherTable',
        component:TeacherTable,
        meta:{title:'教师列表'},
      },{
        path:'/AddTeacher',
        name:'AddTeacher',
        component:AddTeacher,
        meta:{title:'添加教师'},
      },
      {
        path:'/CourseTable',
        name:'CourseTable',
        component:CourseTable,
        meta:{title:'课程列表'},
      },{
        path:'/AddCourse',
        name:'AddCourse',
        component:AddCourse,
        meta:{title:'添加课程'},
      },
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
