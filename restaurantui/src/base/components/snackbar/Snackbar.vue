<template>
  <v-snackbar v-model="tip.status" :timeout="tip.timeout" :color="tip.color" top>{{tip.message}}</v-snackbar>
</template>
 
<script>
export default {
  name: "Snackbar",
  props: {},
  components: {},
  data() {
    return {
      tip: {
        status: false,
        color: "success",
        timeout: 2000,
        message: ""
      }
    };
  },
  created() {},
  methods: {
    info(mes) {
      this.tipShow(mes, "#2196F3");
    },
    error(mes) {
      this.tipShow(mes, "#FF5252");
    },
    warning(mes) {
      this.tipShow(mes, "#EF6C00");
    },
    success(mes) {
      this.tipShow(mes, "#4CAF50");
    },
    tipShow(message, color) {
      this.tip.message = message;
      this.tip.status = true;
      this.tip.color = color;
    }
  }
};
</script>
 
<style lang='scss'>
.v-snack {
  .v-snack__content {
    justify-content: flex-start;
    i {
      margin-right: 10px;
    }
  }
}
</style>