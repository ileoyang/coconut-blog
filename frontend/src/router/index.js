import Vue from "vue"
import VueRouter from "vue-router"
import Register from "../views/user/Register"
import Login from "../views/user/Login"

Vue.use(VueRouter)

const routes = [
    {
        path: "/register",
        name: "Register",
        component: Register
    },
    {
        path: "/login",
        name: "Login",
        component: Login
    }
]

const router = new VueRouter({
    routes
})

export default router
