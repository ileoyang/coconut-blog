<template>
    <el-card>
        <b-field horizontal label="Comment">
            <b-input v-model="curComment.content" type="textarea" maxlength="50"/>
        </b-field>
        <b-field horizontal>
            <p class="control">
                <b-button label="Submit" type="is-primary" @click="addComment"/>
            </p>
        </b-field>
        <article v-for="(comment, index) in prevComments" :key="index" class="media">
            <figure class="media-left image is-48x48">
                <img src="../../assets/pen.png"/>
            </figure>
            <div class="media-content">
                <div class="content align-left">
                    <strong>
                        {{ comment.username }}
                    </strong>
                    <span class="ml-2">
                        {{ comment.createTime.substr(0, 10) }}
                    </span>
                    <br/>
                    {{ comment.content }}
                </div>
            </div>
        </article>
    </el-card>
</template>

<script>
export default {
    props: {
        blogId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            prevComments: [],
            curComment: {
                content: "",
                blogId: null
            }
        }
    },
    created() {
        this.$http.get("comment/blog/" + this.blogId).then(res => {
            this.prevComments = res.data
        })
    },
    methods: {
        addComment() {
            this.curComment.blogId = this.blogId
            this.$http.post("comment/add", this.curComment).then(() => {
                this.$router.go()
            })
        }
    }
}
</script>