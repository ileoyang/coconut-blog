<template>
  <div class="columns">
    <div class="column is-three-quarters">
      <el-card class="box-card" shadow="never">
        <div slot="header" class="has-text-centered">
          <p class="is-size-5 has-text-weight-bold">{{ blog.title }}</p>
          <div class="has-text-grey is-size-7 mt-3">
            <span>{{ blog.createTime.substr(0, 10) }}</span>
            <el-divider direction="vertical" />
            <span>Author：{{ blog.username }}</span>
            <el-divider direction="vertical" />
            <span>View：{{ blog.view }}</span>
          </div>
        </div>
        <div class="markdown-body" align="left" v-html="blog.content" />
        <nav class="level has-text-grey is-size-7 mt-6">
          <div class="level-left">
            <p class="level-item">
              <b-taglist>
                <router-link
                  v-for="(tagName, index) in blog.tagNames"
                  :key="index"
                  :to="{}"
                >
                  <b-tag type="is-info is-light mr-1">
                    {{ '#' + tagName }}
                  </b-tag>
                </router-link>
              </b-taglist>
            </p>
          </div>
          <div v-show="username == blog.username" class="level-right">
            <b-button
              type="is-primary is-light mr-2"
              size="is-small"
              tag="router-link"
              :to="{ name: 'BlogUpdate', params: { id: blog.blogId } }"
            >
              Edit
            </b-button>
            <b-button
              type="is-danger is-light"
              size="is-small"
              @click="deleteBlog"
            >
              Delete
            </b-button>
          </div>
        </nav>
      </el-card>
      <Comment :blogId="blog.blogId" />
    </div>
    <div class="column">
      <section>
        <el-card class="" shadow="never">
          <div slot="header">
            <span class="has-text-weight-bold">👨‍💻 About author</span>
          </div>
          <div class="has-text-centered">
            <p class="is-size-5 mb-5">
              <router-link :to="{ path: `/member/${blog.username}/home` }">
                {{ blog.username }}
              </router-link>
            </p>
            <div class="columns is-mobile">
              <div class="column is-half">
                <code>{{ author.blogCount }}</code>
                <p>Blog</p>
              </div>
              <div class="column is-half">
                <code>{{ author.followerCount }}</code>
                <p>Follower</p>
              </div>
            </div>
            <div>
              <button
                v-if="isFollow"
                class="button is-success button-center is-fullwidth"
                @click="xorFollow"
              >
                Following
              </button>
              <button
                v-else
                class="button is-link button-center is-fullwidth"
                @click="xorFollow"
              >
                Follow
              </button>
            </div>
          </div>
        </el-card>
      </section>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import 'github-markdown-css/github-markdown.css';
import Comment from './Comment';
export default {
  data() {
    return {
      blog: {},
      author: {},
      isFollow: false,
    };
  },
  computed: {
    ...mapGetters(['username']),
  },
  components: {
    Comment,
  },
  created() {
    this.$http.get('blog/' + this.$route.params.id).then((res) => {
      this.blog = res.data;
      const MarkdownIt = require('markdown-it');
      const md = new MarkdownIt();
      this.blog.content = md.render(this.blog.content);
      this.$http.get('user/' + this.blog.userId).then((res) => {
        this.author = res.data;
      });
      if (this.username != null) {
        this.$http.get('follow/is/' + this.blog.userId).then((res) => {
          this.isFollow = res.data;
        });
      }
    });
  },
  methods: {
    deleteBlog() {
      this.$http.delete('blog/delete/' + this.blog.blogId).then(() => {
        this.$router.push('/');
      });
    },
    xorFollow() {
      this.$http.get('follow/' + this.blog.userId).then(() => {
        this.isFollow ^= 1;
        this.$router.go();
      });
    },
  },
};
</script>
