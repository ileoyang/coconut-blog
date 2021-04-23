import Vue from "vue"
import VueRouter from "vue-router"
import Register from "../views/user/Register"
import Login from "../views/user/Login"
import Index from "../views/blog/List"
import BlogEdit from "../views/blog/Edit"
import BlogDetail from "../views/blog/Detail"
import BlogSearch from "../views/blog/Search"

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        name: "Index",
        component: Index
    },
    {
        path: "/register",
        name: "Register",
        component: Register
    },
    {
        path: "/login",
        name: "Login",
        component: Login
    },
    {
        path: "/blog/create",
        name: "BlogCreate",
        component: BlogEdit
    },
    {
        path: "/blog/:id",
        name: "BlogDetail",
        component: BlogDetail
    },
    {
        path: "/blog/update/:id",
        name: "BlogUpdate",
        component: BlogEdit
    },
    {
        path: "/blog/search/:word",
        name: "BlogSearch",
        component: BlogSearch
    }
]

const router = new VueRouter({
    routes
})

export default router
