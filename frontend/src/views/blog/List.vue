<template>
  <div>
    <el-card shadow="never">
      <div slot="header" class="clearfix">
        <article v-for="(blog, index) in blogs" :key="index" class="media">
          <div class="media-left">
            <figure class="image is-48x48">
              <img src="@/assets/icon.png" style="border-radius: 5px" />
            </figure>
          </div>
          <div class="media-content">
            <p class="ellipsis is-ellipsis-1">
              <el-tooltip
                class="item"
                effect="dark"
                :content="blog.title"
                placement="top"
              >
                <router-link
                  :to="{ name: 'BlogDetail', params: { id: blog.blogId } }"
                >
                  <span class="level-left">{{ blog.title }}</span>
                </router-link>
              </el-tooltip>
            </p>
            <nav class="level has-text-grey is-mobile is-size-7 mt-2">
              <div class="level-left">
                <div class="level-left">
                  <router-link class="level-item" :to="{ path: '/' }">
                    {{ blog.username }}
                  </router-link>
                  <span class="mr-1">
                    posted on:{{ blog.createTime.substr(0, 10) }}
                  </span>
                  <span
                    v-for="(tagName, index) in blog.tagNames"
                    :key="index"
                    class="tag is-hidden-mobile is-success is-light mr-1"
                  >
                    <router-link :to="{}">
                      {{ '#' + tagName }}
                    </router-link>
                  </span>
                  <span class="is-hidden-mobile">view:{{ blog.view }}</span>
                </div>
              </div>
            </nav>
          </div>
        </article>
      </div>
      <el-pagination
        v-show="page.total > 0"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page.current"
        :page-size="page.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page.total"
      >
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  props: {
    word: {
      type: String,
      required: false,
    },
  },
  data() {
    return {
      blogs: [],
      page: {
        current: 1,
        size: 10,
        total: 0,
      },
    };
  },
  created() {
    this.getBlogs();
  },
  methods: {
    getBlogs() {
      this.$http
        .get('blog/list', {
          params: {
            pageNum: this.page.current,
            pageSize: this.page.size,
            word: this.word,
          },
        })
        .then((res) => {
          const data = res.data;
          this.blogs = data.records;
          this.page.current = data.current;
          this.page.size = data.size;
          this.page.total = data.total;
        });
    },
    handleSizeChange(val) {
      this.page.size = val;
      this.getBlogs();
    },
    handleCurrentChange(val) {
      this.page.current = val;
      this.getBlogs();
    },
  },
};
</script>
