<template>
  <v-row>
    <v-col>
      <v-form ref="menuForm">
        <v-row>
          <v-text-field v-model="menu.name" label="菜单名称" required :rules="rules.notNull"></v-text-field>
        </v-row>
        <v-row>
          <v-text-field v-model="menu.price" label="价格" required :rules="rules.notNull"></v-text-field>
        </v-row>
        <v-row>
          <v-select
            v-model="menu.type"
            required
            prepend-icon="mdi-food"
            item-text="name"
            item-value="id"
            :items="menuTypes"
            :rules="rules.notNull"
          ></v-select>
        </v-row>
        <v-row>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="insertMenu">新建菜单</v-btn>
        </v-row>
      </v-form>
    </v-col>
    <v-col>
      <v-form ref="menuTypeForm">
        <v-row>
          <v-text-field
            v-model="menuType.name"
            label="菜系名称"
            required
            prepend-icon="mdi-name"
            :rules="rules.notNull"
          ></v-text-field>
        </v-row>
        <v-row>
          <v-spacer></v-spacer>
          <v-btn color="primary" @click="insertMenuType">新建菜系</v-btn>
        </v-row>
      </v-form>
    </v-col>
  </v-row>
</template>
<script>
import * as menuApi from "@/base/api/menu.js";
export default {
  data() {
    return {
      rules: {
        notNull: [v => !!v || "不能为空"]
      },
      menu: {
        name: "",
        type: 0,
        price:null
      },
      btn: {
        menu: false,
        menuType: false
      },
      menuType:{
          name:""
      },
      menuTypes: []
    };
  },
  mounted() {
    this.listAllMenuType();
  },
  methods: {
    insertMenu() {
      if (!this.$refs.menuForm.validate()) {
        return;
      }
      this.btn.menu = true;
      menuApi
        .insertMenu(this.menu)
        .then(result => {
          if (result.success) {
            this.$snackbar.success(result.message);
          } else {
            this.$snackbar.error(result.message);
          }
          this.btn.menu = false;
        })
        .catch(error => {
          this.btn.menu = false;
          this.$snackbar.error(error.message);
        });
    },
    insertMenuType() {
      if (!this.$refs.menuTypeForm.validate()) {
        return;
      }
      this.btn.menuType = true;
      menuApi
        .insertMenuType(this.menuType)
        .then(result => {
          if (result.success) {
            this.$snackbar.success(result.message);
            this.menuType.name = ""
            this.listAllMenuType()
          } else {
            this.$snackbar.error(result.message);
          }
          this.btn.menuType = false;
        })
        .catch(error => {
          this.btn.menuType = false;
          this.$snackbar.error(error.message);
        });
    },
    listAllMenuType() {
      menuApi
        .listAllMenuType()
        .then(result => {
          if (result.success) {
            this.menuTypes = result.data.list;
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