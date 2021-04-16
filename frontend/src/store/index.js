import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        jwt: localStorage.getItem("jwt"),
        username: localStorage.getItem("username")
    },
    mutations: {
        SET_JWT: (state, jwt) => {
            state.jwt = jwt
            localStorage.setItem("jwt", jwt)
        },
        SET_USERNAME: (state, username) => {
            state.username = username
            localStorage.setItem("username", username)
        },
        REMOVE_ALL: (state) => {
            state.jwt = state.username = null
            localStorage.removeItem("jwt")
            localStorage.removeItem("username")
        }
    },
    getters: {
        jwt: state => {
            return state.jwt
        },
        username: state => {
            return state.username
        }
    },
    actions: {
    },
    modules: {
    }
})
