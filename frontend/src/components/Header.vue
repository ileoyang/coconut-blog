<template>
  <header class="header has-background-white has-text-black">
    <b-navbar class="container is-white" :fixed-top="true">
      <template slot="brand">
        <b-navbar-item tag="router-link" :to="{ path: '/' }">
          <img src="@/assets/icon.png" alt="logo" />
        </b-navbar-item>
      </template>
      <template slot="start">
        <b-navbar-item tag="router-link" :to="{ path: '/' }">
          🏠 Home
        </b-navbar-item>
      </template>
      <template slot="end">
        <b-navbar-item tag="div">
          <b-field position="is-centered">
            <b-input
              v-model="word"
              class="s_input"
              width="80%"
              placeholder="Enter blog content"
              rounded
              clearable
              type="search"
            />
            <p class="control">
              <b-button class="is-info" @click="search">Search</b-button>
            </p>
          </b-field>
        </b-navbar-item>
        <b-navbar-item v-if="username == null" tag="div">
          <div class="buttons">
            <b-button
              class="is-light"
              tag="router-link"
              :to="{ path: '/register' }"
            >
              Register
            </b-button>
            <b-button
              class="is-light"
              tag="router-link"
              :to="{ path: '/login' }"
            >
              Login
            </b-button>
          </div>
        </b-navbar-item>
        <b-navbar-dropdown v-else :label="username">
          <b-navbar-item tag="a" @click="logOut">👋 Log Out</b-navbar-item>
        </b-navbar-dropdown>
      </template>
    </b-navbar>
  </header>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  data() {
    return {
      word: '',
    };
  },
  computed: {
    ...mapGetters(['username']),
  },
  methods: {
    search() {
      this.$router.push(`/blog/search/${this.word}`);
    },
    logOut() {
      this.$store.commit('REMOVE_ALL');
      // reload page
      this.$router.go();
    },
  },
};
</script>
