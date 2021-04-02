<template>
  <v-card class="mx-auto" max-width="400">
    <v-img
      class="white--text align-end"
      height="200px"
      src="https://ikaros-picture.oss-cn-shenzhen.aliyuncs.com/typorarestaurant_bg.jpg"
    ></v-img>
    <v-card-text>
      <v-row>
        <v-col cols="12">
          <v-form ref="form">
            <v-row>
              <v-text-field
                v-model="user.username"
                :rules="rules.username"
                label="账号"
                required
                prepend-icon="mdi-account"
              ></v-text-field>
            </v-row>
            <v-row>
              <v-text-field
                v-model="user.password"
                :rules="rules.password"
                label="密码"
                required
                prepend-icon="mdi-lock"
                type="password"
              ></v-text-field>
            </v-row>
          </v-form>
        </v-col>
      </v-row>
    </v-card-text>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="#B39DDB" @click="register" :disabled="button.register">注册</v-btn>
      <v-btn color="primary" @click="login" :disabled="button.login">登陆</v-btn>
    </v-card-actions>
  </v-card>
</template>
<script>
import * as loginApi from "@/base/api/login";
import qs from "qs";
export default {
  data() {
    return {
      user: {
        password: "",
        username: ""
      },
      button: {
        login: false,
        register: false
      },
      rules: {
        username: [
          v => !!v || "请输入用户名",
          v => v.length <= 10 || "用户名过长"
        ],
        password: [
          v => !!v || "请输入密码",
          v => v.length >= 6 || "请输入至少6位密码"
        ]
      }
    };
  },
  mounted() {
    this.$root.$data.username = undefined;
  },
  methods: {
    validate() {
      return this.$refs.form.validate();
    },
    register() {
      if (!this.validate()) {
        return;
      }
      this.button.register = true;
      loginApi
        .register(this.user)
        .then(result => {
          if (result.success) {
            this.$snackbar.success(result.message);
          } else {
            if (result.code == 10002) {
              // 数据完整性约束异常
              result.message = "该用户名已存在";
            }
            this.$snackbar.error(result.message);
          }
          this.button.register = false;
          this.user.password = "";
        })
        .catch(error => {
          this.user.password = "";
          this.button.register = false;
          this.$snackbar.error(error.message);
        });
    },
    login() {
      if (!this.validate()) {
        return;
      }
      this.button.login = true;
      loginApi
        .login(this.user)
        .then(result => {
          if (result.success) {
            this.$snackbar.success(result.message);
            window.localStorage.setItem("user", qs.stringify(result.data.user));
            this.$root.$data.username = result.data.user.username;
            this.$root.$data.userId = result.data.user.id;
            this.$router.push("menu");
          } else {
            this.$snackbar.error(result.message);
          }
          this.user.password = "";
          this.button.login = false;
        })
        .catch(error => {
          this.user.password = "";
          this.button.login = false;
          this.$snackbar.error(error.message);
        });
    }
  }
};
</script>