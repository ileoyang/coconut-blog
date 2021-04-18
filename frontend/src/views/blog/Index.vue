<template>
    <el-card>
        <article v-for="(blog, index) in blogs" :key="index" class="media">
            <div class="media-left">
                <figure class="image is-48x48">
                    <img src="../../assets/pen.png"/>
                </figure>
            </div>
            <div class="media-content">
                <div class="level-left">
                    <el-tooltip :content="blog.title">
                        <router-link :to="{name: 'BlogDetail', params: {id: blog.blogId}}">
                            {{ blog.title }}
                        </router-link>
                    </el-tooltip>
                </div>
                <nav class="is-size-7">
                    <div class="level-left">
                        <router-link :to="{ path: '/' }" class="mr-2">
                            {{ blog.username }}
                        </router-link>
                        <span class="mr-2">
                            Posted on: {{ blog.createTime.substr(0, 10) }}
                        </span>
                        <el-tag v-for="(tagName, index) in blog.tagNames" :key="index" size="small" class="mr-2">
                            <router-link :to="{ path: '/' }">
                                {{ "#" + tagName }}
                            </router-link>
                        </el-tag>
                        <span>
                            View: {{ blog.view }}
                        </span>
                    </div>
                </nav>
            </div>
        </article>
        <hr/>
        <el-pagination
            v-show="page.total > 0"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="page.current"
            :page-size="page.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="page.total">
        </el-pagination>
    </el-card>
</template>

<script>
export default {
    data() {
        return {
            blogs: [],
            page: {
                current: 1,
                size: 10,
                total: 0
            }
        }
    },
    created() {
        this.getBlogs()
    },
    methods: {
        getBlogs() {
            this.$http.get("blog/list", {
                params: {
                    pageNum: this.page.current,
                    pageSize: this.page.size
                }
            }).then(res => {
                const data = res.data
                this.blogs = data.records
                this.page.current = data.current
                this.page.size = data.size
                this.page.total = data.total
            })
        },
        handleSizeChange(val) {
            this.page.size = val
            this.getBlogs()
        },
        handleCurrentChange(val) {
            this.page.current = val
            this.getBlogs()
        }
    }
}
</script>