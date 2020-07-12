import Vue from "vue";
import App from "./App.vue";
import router from "@/base/config/router";
import vuetify from "@/plugins/vuetify"; // path to vuetify export
import qs from "qs";
// 引入snackbar
import snackbar from "@/base/components/snackbar";
Vue.prototype.$snackbar = snackbar;
Vue.config.productionTip = false;
var store = {
  debug: true,
  state: {
    username: "",
  },
  setUsernameAction(newValue) {
    this.state.username = newValue;
  },
  clearUsernameAction() {
    this.state.username = "";
  },
};
var user = qs.parse(localStorage.getItem("user"));
var username = "";
var userId = "";
if (user != null) {
  username = user.username;
  userId = user.id;
}
new Vue({
  router,
  vuetify,
  data: {
    username: username,
    userId: userId,
  },
  render: (h) => h(App),
}).$mount("#app");
