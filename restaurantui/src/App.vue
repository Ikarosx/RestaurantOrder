<template>
  <!-- App.vue -->
  <v-app>
    <v-app-bar app color="rgb(255, 246, 211)">
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
      <v-toolbar-title>Is点餐系统</v-toolbar-title>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn text>点餐</v-btn>
        <v-btn text>历史订单</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <p class="mb-0" v-show="$root.$data.username != ''">
        欢迎您，{{$root.$data.username}}
        <a @click="logout">注销</a>
      </p>
    </v-app-bar>
    <!-- Sizes your content based upon application components -->
    <v-content>
      <!-- Provides the application the proper gutter -->
      <v-container fill-height>
        <v-row align="center" justify="center" class="lighten-5">
          <router-view></router-view>
        </v-row>
        <!-- If using vue-router -->
      </v-container>
    </v-content>

    <v-footer app>
      <!-- -->
    </v-footer>
  </v-app>
</template>

<style>
#app {
  font-family: Microsoft YaHei, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: cornsilk;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
<script>
import qs from "qs";
import * as loginApi from "@/base/api/login.js";
export default {
  data() {
    return {
      username: ""
    };
  },
  mounted() {},
  methods: {
    logout() {
      window.localStorage.removeItem("user");
      this.$root.$data.username = "";

      loginApi
        .logout()
        .then(result => {
          if (result.success) {
            window.location.href = "/";
          } else {
            this.$snackbar.error(result.message);
          }
        })
        .catch(error => {
          this.$snackbar.error(error.message);
        });
    }
  }
};
</script>