module.exports = {
  chainWebpack: (config) => {
    config.plugin("html").tap((args) => {
      args[0].title = "Is点餐系统";
      return args;
    });
  },
  configureWebpack: {
    resolve: {
      extensions: [".js", ".json", ".vue", ".scss", ".css"],
    },
    externals: {
      vue: "Vue",
      "vue-router": "VueRouter",
      vuetify: "Vuetify",
      axios: "axios",
    },
  },
};
