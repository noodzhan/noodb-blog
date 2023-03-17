<template>
  <NoodbLayout @headSearch="onHeadSearch">
    <template v-slot:side>
      <LeftBar></LeftBar>
    </template>
    <template v-slot:content>
      <div class="home-content">
        <noodb-spin v-if="loading"></noodb-spin>
        <a-list item-layout="vertical" size="large" :data-source="blogs">
          <ul class="ant-pagination">
            <li
              class="ant-pagination-item"
              :class="{ 'ant-pagination-disabled': current - 1 < 1 }"
            >
              <a
                class="page-link"
                :href="prePageUrl"
                aria-label="Previous"
                target="_self"
                :disabled="current - 1 < 1"
              >
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
            <li
              class="ant-pagination-item"
              :class="{
                'ant-pagination-disabled': current - 2 < 1,
                hide: current - 2 < 0
              }"
            >
              <a
                class="page-link"
                :href="`/blog/page/${current - 2}`"
                :disabled="current - 2 < 1"
                >{{ current - 2 }}</a
              >
            </li>
            <li
              class="ant-pagination-item"
              :class="{
                'ant-pagination-disabled': current - 1 < 1,
                hide: current - 1 < 0
              }"
            >
              <a
                class="page-link"
                :href="`/blog/page/${current - 1}`"
                :disabled="current - 1 < 1"
                >{{ current - 1 }}</a
              >
            </li>
            <!-- 当前页 -->
            <li class="ant-pagination-item ant-pagination-item-active">
              <a class="page-link" :href="`/blog/page/${current}`">{{
                current
              }}</a>
            </li>
            <li
              class="ant-pagination-item"
              :class="{
                'ant-pagination-disabled': current + 1 > pageCount,
                hide: current + 1 > pageCount
              }"
            >
              <a
                class="page-link"
                :href="`/blog/page/${current + 1}`"
                :disabled="current + 1 > pageCount"
                >{{ current + 1 }}</a
              >
            </li>
            <li
              class="ant-pagination-item"
              :class="{
                'ant-pagination-disabled': current + 2 > pageCount,
                hide: current + 2 > pageCount
              }"
            >
              <a
                class="page-link"
                :href="`/blog/page/${current + 2}`"
                :disabled="current + 2 > pageCount"
                >{{ current + 2 }}</a
              >
            </li>
            <li
              class="ant-pagination-item"
              :class="{
                'ant-pagination-disabled': current + 1 > pageCount
              }"
            >
              <a
                class="page-link"
                :href="nextPageUrl"
                aria-label="Next"
                :disabled="current + 1 > pageCount"
              >
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
            <li class="ant-pagination-options">
              <div class="ant-pagination-options-quick-jumper">
                跳到
                <a-input @keyup.enter="goto"></a-input>
                页 ,共 {{ pageCount }} 页
              </div>
            </li>
          </ul>
          <a-list-item slot="renderItem" slot-scope="item">
            <a-list-item-meta :description="item.summary">
              <a :href="`/blog/${item.id}`" slot="title">
              <span v-html="item.title"></span>      
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
import LeftBar from '@/components/LeftBar';

export default {
  head() {
    return {
      title: 'noodb',
      meta: [
        { charset: 'utf-8' },
        { name: 'viewport', content: 'width=device-width, initial-scale=1' },
        {
          hid: 'description',
          name: 'description',
          content: 'noodb'
        },
        {
          hid: 'keywords',
          name: 'keywords',
          content: this.$serverUtil.keywords()
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
    };
  },
  name: 'Home',
  components: {
    NoodbLayout,
    NoodbSpin,
    NoodbBackTop,
    LeftBar
  },
  computed: {
    nextPageUrl: function () {
      console.log(this.current);
      return `/blog/page/${this.current + 1}`;
    },
    prePageUrl: function () {
      console.log(this.current);
      return `/blog/page/${this.current - 1}`;
    }
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
      current: 1,
      pageCount: 0
    };
  },
  async fetch() {
    await this.$axios({
      url: '/api/article/all',
      method: 'GET',
      params: {
        pageNum: this.$router.currentRoute.params.pageNum,
        pageSize: 15,
        searchValue: null
      }
    }).then((res) => {
      if (res.data.code === 0) {
        this.blogs = res.data.data.records;
        this.total = res.data.data.total;
      }
      this.loading = false;
      this.current = new Number(this.$router.currentRoute.params.pageNum);
      this.pageCount = Math.ceil(this.total / this.pageSize);
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
    goto(event) {
      let page = event.target.value;
      if (page > this.pageCount) {
        return;
      }
      this.$router.push(`/blog/page/${page}`);
    },

    onHeadSearch(value) {
      // console.log(value)
      const $vm = this;
      // 暂时搜索不支持分页。
      this.api.searchArticle(
        1,
        this.pageSize,
        (res) => {
          if (res.code === 0) {
            $vm.blogs = res.data;
            // $vm.total = res.data.total;
          }
          $vm.loading = false;
        },
        value
      );
    }
  },

  beforeMount() {
    this.api = new HomeService(this);
  },
  watch: {
    '$route.params.pageNum': {
      immediate: true,
      handler(newVal, oldVal) {
        this.current = newVal;
      }
    }
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
.ant-pagination {
  /* margin-bottom: 20px; */
  width: 500px;
  margin: 20px auto;
  margin-top: 0px;
}
.hide {
  display: none;
}
</style>
