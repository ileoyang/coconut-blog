<template>
    <b-navbar fixed-top>
        <template slot="brand">
            <b-navbar-item tag="router-link" :to="{ path: '/' }">
                <img src="../assets/coconut.png"/>
            </b-navbar-item>
        </template>
        <template slot="start">
            <b-navbar-item>
                Home
            </b-navbar-item>
            <b-navbar-item>
                Documentation
            </b-navbar-item>
            <b-navbar-dropdown label="Info">
                <b-navbar-item>
                    About
                </b-navbar-item>
                <hr class="dropdown-divider"/>
                <b-navbar-item>
                    Contact
                </b-navbar-item>
            </b-navbar-dropdown>
            <b-navbar-item>
                <b-input v-model="word" placeholder="Enter blog content" type="search"/>
                <b-button type="is-primary" label="Search" tag="router-link" :to="{ path: `/blog/search/${word}` }"/>
            </b-navbar-item>
        </template>
        <template slot="end">
            <b-navbar-item tag="div" v-if="username == null">
                <div class="buttons">
                    <b-button class="is-primary" tag="router-link" :to="{ path: '/register' }">
                        <strong>
                            Sign up
                        </strong>
                    </b-button>
                    <b-button class="is-light" tag="router-link" :to="{ path: '/login' }">
                        Log in
                    </b-button>
                </div>
            </b-navbar-item>
            <b-navbar-dropdown v-else :label="username">
                <b-navbar-item tag="router-link" :to="{ name: 'BlogCreate' }">
                    Post
                </b-navbar-item>
                <hr class="dropdown-divider"/>
                <b-navbar-item>
                    Profile
                </b-navbar-item>
                <hr class="dropdown-divider"/>
                <b-navbar-item tag="a" @click="logOut">
                    Log out
                </b-navbar-item>
            </b-navbar-dropdown>
        </template>
    </b-navbar>
</template>

<script>
import { mapGetters } from "vuex"

export default {
    data() {
        return {
            word: ""
        }
    },
    computed: {
        ...mapGetters([
            "username"
        ])
    },
    methods: {
        logOut() {
            this.$store.commit("REMOVE_ALL")
            // reload page
            this.$router.go()
        }
    }
}
</script>