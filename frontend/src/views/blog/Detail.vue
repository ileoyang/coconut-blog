<template>
    <div class="columns">
        <div class="column is-three-quarters">
            <el-card>
                <div slot="header">
                    <p class="is-size-5 has-text-weight-bold">
                        {{ blog.title }}
                    </p>
                    <div class="is-size-7 mt-3">
                        <span>
                            Create time: {{ blog.createTime.substr(0, 10) }}
                        </span>
                        <el-divider direction="vertical"/>
                        <span>
                            Author: {{ blog.username }}
                        </span>
                        <el-divider direction="vertical"/>
                        <span>
                            View: {{ blog.view }}
                        </span>
                    </div>
                </div>
                <div class="markdown-body align-left" v-html="blog.content"/>
                <nav class="level has-text-grey is-size-7 mt-6">
                    <div class="level-left">
                        <el-tag v-for="(tagName, index) in blog.tagNames" :key="index" size="small" class="mr-2">
                            <router-link :to="{ path: '/' }">
                                {{ "#" + tagName }}
                            </router-link>
                        </el-tag>
                    </div>
                    <div class="level-right" v-show="username == blog.username">
                        <b-button type="is-primary is-light mr-2" size="is-small" tag="router-link" :to="{ name: 'BlogUpdate', params: {id: blog.blogId} }">
                            Edit
                        </b-button>
                        <b-button type="is-danger is-light" size="is-small" @click="deleteBlog">
                            Delete
                        </b-button>
                    </div>
                </nav>
            </el-card>
            <Comment :blogId="blog.blogId"/>
        </div>
        <div class="column">
            <el-card>
                <div slot="header">
                    <span class="has-text-weight-bold">
                        About author
                    </span>
                </div>
                <p class="is-size-5 mb-5">
                    <router-link :to="{ path: '/' }">
                        {{ blog.username }}
                    </router-link>
                </p>
                <div class="columns">
                    <div class="column is-half">
                        <code>
                            {{ author.blogCount }}
                        </code>
                        <p>
                            Blog(s)
                        </p>
                    </div>
                    <div class="column is-half">
                        <code>
                            {{ author.followerCount }}
                        </code>
                        <p>
                            Follower(s)
                        </p>
                    </div>
                </div>
                <b-button v-if="isFollow" type="is-success" @click="xorFollow">
                    Following
                </b-button>
                <b-button v-else type="is-link" @click="xorFollow">
                    Follow
                </b-button>
            </el-card>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex"
import "github-markdown-css/github-markdown.css"
import Comment from "./Comment"

export default {
    data() {
        return {
            blog: {},
            author: {},
            isFollow: false
        }
    },
    computed: {
        ...mapGetters([
            "username"
        ])
    },
    components: {
        Comment
    },
    created() {
        this.$http.get("blog/" + this.$route.params.id).then(res => {
            this.blog = res.data
            const MarkdownIt = require("markdown-it");
            const md = new MarkdownIt();
            this.blog.content = md.render(this.blog.content)
            this.$http.get("user/" + this.blog.userId).then(res => {
                this.author = res.data
            })
            if (this.username != null) {
                this.$http.get("follow/is/" + this.blog.userId).then(res => {
                    this.isFollow = res.data
                })
            }
        })
    },
    methods: {
        deleteBlog() {
            this.$http.delete("blog/delete/" + this.blog.blogId).then(() => {
                this.$router.push("/")
            })
        },
        xorFollow() {
            this.$http.get("follow/" + this.blog.userId).then(() => {
                this.isFollow ^= 1
                this.$router.go()
            })
        }
    }
}
</script>