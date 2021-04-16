import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import store from "./store"

// element-ui
import "./plugins/element"

// global css
import "./assets/style.css"

// axios
import "./plugins/axios"

// buefy
import "./plugins/buefy"

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
