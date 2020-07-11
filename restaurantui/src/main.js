import Vue from "vue";
import App from "./App.vue";
import router from "@/base/config/router";
import vuetify from "@/plugins/vuetify"; // path to vuetify export

// 引入snackbar
import snackbar from "@/base/components/snackbar";
Vue.prototype.$snackbar = snackbar;
Vue.config.productionTip = false;
var store = {
  debug: true,
  state: {
    username: ''
  },
  setUsernameAction (newValue) {
    this.state.username = newValue
  },
  clearUsernameAction () {
    this.state.username = ''
  }
}
new Vue({
  router,
  vuetify,
  data:{
    username: ''
  },
  render: (h) => h(App),
}).$mount("#app");
