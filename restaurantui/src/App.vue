<template>
  <!-- App.vue -->
  <v-app>
    <!-- Sizes your content based upon application components -->
    <v-content>
      <!-- Provides the application the proper gutter -->
      <v-container fill-height>
        <v-row>
          <v-col cols="12">
            <v-row align="center" justify="center" class="lighten-5">
              <router-view></router-view>
            </v-row>
          </v-col>
          <!-- If using vue-router -->
        </v-row>
      </v-container>
    </v-content>

    <v-footer app>
      <!-- -->
    </v-footer>
  </v-app>
</template>

<style>
#app {
  font-family:  Microsoft YaHei, Arial, sans-serif;
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
