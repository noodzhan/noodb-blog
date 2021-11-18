<template>
  <NoodbLayout @headSearch="onHeadSearch">
    <template v-slot:side>
      <div>
        <a-card title="推荐文档" :bordered="false">
          <div v-for="(item,index) in recommendBooks" :key="index" class="recommend-book">
            <a>{{ item.name }}</a>
          </div>
        </a-card>
        <a-card title="推荐书籍" :bordered="false">
          <div v-for="(item,index) in recommendBooks" :key="index" class="recommend-book">
            <a>{{ item.name }}</a>
          </div>
        </a-card>
        <a-card title="捐赠支持" :bordered="false">
          <img src="../asserts/img/payme.jpeg" width="100%" style="text-align: center">
        </a-card>
      </div>
    </template>
    <template v-slot:content>
      <div class="home-content">
        <noodb-spin v-if="loading"></noodb-spin>
        <!--        <div v-if="blogs.length===0" style="height: 80vh"><h2>暂无数据</h2></div>-->
        <a-list item-layout="vertical" size="large" :data-source="blogs"
        >
          <div class="load-more" @click="readMore" v-if="blogs.length>pageSize">
            <a slot="loadMore" href="javascript:void(0)">阅读更多</a>
          </div>
          <a-list-item slot="renderItem" slot-scope="item">
            <a-list-item-meta
                :description="item.summary"
            >
              <a slot="title" @click="clickOnArticle(item)">{{ item.title }}</a>
            </a-list-item-meta>
          </a-list-item>
        </a-list>
      </div>
    </template>
  </NoodbLayout>
</template>

<script>
import NoodbLayout from '@/components/Layout'
import router from '@/router'
import HomeService from '@/asserts/js/homeService'
import NoodbSpin from '@/components/Spin'

export default {
  name: 'Home',
  components: {
    NoodbLayout,
    NoodbSpin
  },
  data: function () {
    return {
      pageSize: 15,
      pageNum: 1,
      loading: true,
      busy: false,
      api: new HomeService(this),
      blogs: [],
      recommendBooks: [{ name: 'JavaScript高级程序设计' }, { name: '深入迁出vuejs' }, { name: '代码之道' }, { name: '面向对象葵花宝典' }]
    }
  },
  methods: {
    clickOnArticle (article) {
      // 跳转到文章详情页面，并且通过参数传递文章id
      router.push({
        path: '/blog/' + article.id,
        params: {
          articleId: article.id
        }
      })
    },
    readMore () {
      const $vm = this
      this.api.getAllArticleSummary(this.pageNum + 1, this.pageSize, (res) => {
        if (res.code === 0) {
          if (res.data.records.length > 0) {
            // Array.prototype.push.apply($vm.blogs, res.data.records)
            $vm.blogs = $vm.blogs.concat(res.data.records)
            this.pageNum++
          } else {
            $vm.$notification.warn({ message: '到底啦' })
          }
        }
      })
    },
    onHeadSearch (value) {
      // console.log(value)
      const $vm = this
      this.api.getAllArticleSummary(this.pageNum, this.pageSize, (res) => {
        if (res.code === 0) {
          $vm.blogs = res.data.records
        }
        $vm.loading = false
      }, value)
    }

  },
  beforeMount () {
    const $vm = this
    this.api.getAllArticleSummary(this.pageNum, this.pageSize, (res) => {
      if (res.code === 0) {
        $vm.blogs = res.data.records
      }
      $vm.loading = false
    })
  }
}
</script>
<style scoped>
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
  background: #e5eaef;;
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
