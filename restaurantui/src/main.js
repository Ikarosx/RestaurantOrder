import Vue from 'vue'
import App from './App.vue'
import router from '@/base/config/router'
import vuetify from '@/plugins/vuetify' // path to vuetify export

// 引入snackbar
import snackbar from "@/base/components/snackbar";
Vue.prototype.$snackbar = snackbar;
Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')

