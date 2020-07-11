import Snackbar from "./Snackbar.vue";

import Vue from "vue";

const v = new Vue({
  render(createElement) {
    return createElement(Snackbar);
  }
});

v.$mount();

document.body.appendChild(v.$el);

const snackbar = v.$children[0];

function info(mes) {
  snackbar.info(mes);
}

function error(mes) {
  snackbar.error(mes);
}

function warning(mes) {
  snackbar.warning(mes);
}

function success(mes) {
  snackbar.success(mes);
}
export default {
  info,
  success,
  warning,
  error
};
