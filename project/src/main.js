// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import s from './config/sj'
import iView from 'iview';
import 'iview/dist/styles/iview.css';
Vue.config.productionTip = false

Vue.use(iView, {
  transfer: true,
  size: 'large',
  select: {
      arrow: 'md-arrow-dropdown',
      arrowSize: 20
  }
});
window.s = s;
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
