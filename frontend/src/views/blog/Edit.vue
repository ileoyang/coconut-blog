<template>
    <div class="columns">
        <div class="column is-full">
            <el-card>
                <div slot="header">
                    Edit blog
                </div>
                <el-form :model="blog">
                    <el-form-item>
                        <el-input v-model="blog.title" placeholder="input title"/>
                    </el-form-item>
                    <mavon-editor v-model="blog.content" language="en"/>
                    <b-taginput v-model="blog.tagNames" maxlength="10" maxtags="3" type="is-success" placeholder="input tags" class="my-3"/>
                    <el-form-item class="mt-3">
                        <el-button type="primary" @click="editBlog()">
                            Submit
                        </el-button>
                    </el-form-item>
                </el-form>
            </el-card>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            blog: {
                blogId: null,
                title: "",
                content: "",
                tagNames: []
            }
        }
    },
    created() {
        this.blog.blogId = this.$route.params.id
        if (this.blog.blogId == null) {
            return
        }
        this.$http.get("blog/" + this.blog.blogId).then(res => {
            const data = res.data
            this.blog.title = data.title
            this.blog.content = data.content
            this.blog.tagNames = data.tagNames
        })
    },
    methods: {
        editBlog() {
            this.$http.post("blog/edit", this.blog).then(res => {
                this.$router.push({
                    name: "BlogDetail",
                    params: { id: res.data.id }
                })
            })
        }
    }
}
</script>