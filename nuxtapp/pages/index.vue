<template>
  <NoodbLayout @headSearch="onHeadSearch">
    <template v-slot:side>
      <div>
        <a-card title="推荐文档" :bordered="false">
          <div
            v-for="(item, index) in recommendDocs"
            :key="index"
            class="recommend-book"
          >
            <a :href="item.url">{{ item.name }}</a>
          </div>
        </a-card>
        <a-card title="推荐书籍" :bordered="false">
          <div
            v-for="(item, index) in recommendBooks"
            :key="index"
            class="recommend-book"
          >
            <p>{{ item.name }}</p>
          </div>
        </a-card>
        <a-card title="捐赠支持" :bordered="false">
          <img
            src="../assets/img/payme.jpeg"
            width="100%"
            style="text-align: center"
          />
        </a-card>
      </div>
    </template>
    <template v-slot:content>
      <div class="home-content">
        <noodb-spin v-if="loading"></noodb-spin>
        <a-list item-layout="vertical" size="large" :data-source="blogs">
          <div
            v-show="blogs.length < total"
            class="load-more"
            @click="readMore"
          >
            <a slot="loadMore" href="javascript:void(0)">阅读更多</a>
          </div>
          <a-list-item slot="renderItem" slot-scope="item">
            <a-list-item-meta :description="item.summary">
              <a :href="`/blog/${item.id}`" slot="title">
                {{ item.title }}
              </a>
            </a-list-item-meta>
          </a-list-item>
        </a-list>
      </div>
      <noodb-back-top />
    </template>
  </NoodbLayout>
</template>

<script>
import NoodbLayout from '@/components/Layout';
import HomeService from '@/assets/js/homeService';
import NoodbSpin from '@/components/Spin';
import NoodbBackTop from '@/components/backTop';

export default {
  head: {
    title: 'noodb个人博客',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      {
        hid: 'description',
        name: 'noodb个人博客',
        content: 'noodb个人博客'
      }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      {
        rel: 'start',
        type: 'image/x-icon',
        href: 'www.noodb.com'
      }
    ],
    script: [
      {
        src: '/baiduStatistic.js'
      }
    ]
  },
  name: 'Home',
  components: {
    NoodbLayout,
    NoodbSpin,
    NoodbBackTop
  },
  data: function () {
    return {
      total: 0,
      pageSize: 15,
      pageNum: 1,
      loading: true,
      busy: false,
      api: null,
      blogs: [],
      recommendDocs: [
        { name: 'vuejs', url: 'https://cn.vuejs.org/index.html' },
        { name: 'spring', url: 'https://spring.io/' },
        {
          name: 'mdn web docs ',
          url: 'https://developer.mozilla.org/zh-CN/docs/Learn'
        },
        { name: 'stackoverflow', url: 'https://stackoverflow.com/' }
      ],
      recommendBooks: [
        { name: 'JavaScript高级程序设计' },
        { name: '深入迁出vuejs' },
        { name: '代码之道' },
        { name: '面向对象葵花宝典' }
      ]
    };
  },
  async fetch() {
    await this.$axios({
      url: '/api/article/all',
      method: 'GET',
      params: {
        pageNum: 1,
        pageSize: 15,
        searchValue: null
      }
    }).then((res) => {
      if (res.data.code === 0) {
        this.blogs = res.data.data.records;
        this.total = res.data.data.total;
      }
      this.loading = false;
    });
  },
  methods: {
    clickOnArticle(article) {
      // 跳转到文章详情页面，并且通过参数传递文章id
      this.$router.push({
        path: '/blog/' + article.id,
        params: {
          articleId: article.id
        }
      });
    },
    readMore() {
      const $vm = this;
      this.api.getAllArticleSummary(this.pageNum + 1, this.pageSize, (res) => {
        if (res.code === 0) {
          if (res.data.records.length > 0) {
            // Array.prototype.push.apply($vm.blogs, res.data.records)
            $vm.blogs = $vm.blogs.concat(res.data.records);
            this.pageNum++;
            $vm.total = res.data.total;
          } else {
            $vm.$notification.warn({ message: '到底啦' });
          }
        }
      });
    },
    onHeadSearch(value) {
      // console.log(value)
      const $vm = this;
      // 暂时搜索不支持分页。
      this.api.getAllArticleSummary(
        1,
        this.pageSize,
        (res) => {
          if (res.code === 0) {
            $vm.blogs = res.data.records;
            $vm.total = res.data.total;
          }
          $vm.loading = false;
        },
        value
      );
    }
  },
  beforeMount() {
    this.api = new HomeService(this);
  }
};
</script>
<style scoped>
/deep/ .ant-list-item-meta-description {
  color: #555666 !important;
}

/deep/ .ant-list-vertical .ant-list-item-meta-title {
  color: #222226;
  font-size: 18px;
}

.post {
  margin: 1vh 1vh 0 0;
  padding-right: 2vw;
}

.recommend-book {
  padding-top: 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.post:hover {
  background: #e5eaef;
}

.load-more {
  width: 80%;
  height: 40px;
  margin: 30px auto 60px;
  padding: 10px 15px;
  text-align: center;
  font-size: 15px;
  border-radius: 20px;
  color: #fff;
  background-color: #a5a5a5;
  display: block;
}

.load-more:hover {
  color: #fff;
  background-color: #9b9b9b;
}

.load-more > a {
  color: #fff;
}

.home-content {
  min-height: 80vh;
}
</style>
