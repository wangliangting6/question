import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import login from '@/views/user/login'
import createQuestion from '@/views/question/createQuestion'
import questionList from '@/views/question/questionList'
import answerList from '@/views/question/answerList'
import myQuestion from '@/views/user/myQuestion'
Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },{
      path: '/login',
      name: 'login',
      component: login
    },{
      
      path: '/createQuestion',
      name: 'createQuestion',
      component: createQuestion
    },{
      
      path: '/questionList',
      name: 'questionList',
      component: questionList
    },{
      
      path: '/myQuestion',
      name: 'myQuestion',
      component: myQuestion
    },{
      
      path: '/answerList',
      name: 'answerList',
      component: answerList
    }
  ]
})
