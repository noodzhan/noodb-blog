<template>
  <NoodbLayout>
    <template v-slot:side>
     <div style="padding-left: 20px" class="left-catalog">
       <a-anchor :offsetTop="70">
         <a-anchor-link v-for="(item,index) in titles" :href="'#'+item" :key="index" :title="item" />
       </a-anchor>
     </div>
    </template>
    <template v-slot:content>
      <div>
        <noodb-login :visible="isOpenLoginModal" @cancel="handleCancel"></noodb-login>
        <a-tooltip placement="bottomLeft" title="编辑" >
          <a-icon type="edit" style="position: fixed;right: 0;padding-right: 3vw;padding-top: 10px" @click="editMd"/>
        </a-tooltip>
        <div id="md" v-html="md"/>
        <noodb-spin v-if="loading"></noodb-spin>
      </div>
    </template>
  </NoodbLayout>
</template>
<script>
import NoodbLayout from '@/components/Layout'
import Marked from 'marked'
import HighLight from 'highlight.js'
// import router from '@/router'
import NoodbSpin from '@/components/Spin'
import NoodbLogin from '@/views/Login.vue'
export default {
  name: 'Article',
  components: {
    NoodbLayout,
    NoodbSpin,
    NoodbLogin
  },
  data: function () {
    return {
      loading: true,
      md: '',
      articleId: '',
      titles: [],
      isOpenLoginModal: false
    }
  },
  beforeMount () {
    this.loading = true
    const $vm = this
    const renderer = {
      heading (text, level, raw, slugger) {
        if (level <= 4) {
          $vm.titles.push(text)
        }
        if (this.options.headerIds) {
          return '<h' +
              level +
              ' id="' +
              this.options.headerPrefix +
              slugger.slug(raw) +
              '">' +
              text +
              '</h' +
              level +
              '>\n'
        }
        // ignore IDs
        return '<h' + level + '>' + text + '</h' + level + '>\n'
      }
    }
    Marked.use({ renderer })
    Marked.setOptions({
      highlight: function (code) {
        return HighLight.highlightAuto(code).value
      },
      pedantic: false,
      gfm: true,
      tables: true,
      breaks: false,
      sanitize: false,
      smartLists: true,
      smartypants: false,
      xhtml: false
      // headerPrefix: 'noodb_'
    })
    // 1. 从路由中获取文章id
    // console.log(this.$route.params.articleId)
    // 2. 发起网络请求获取数据
    this.$http({
      url: this.$appUrl + this.autoPrefix() + '/article/one',
      method: 'GET',
      params: { id: this.$route.params.articleId }

    }).then(res => {
      if (res.data.code === 0) {
        this.articleId = res.data.data.id
        this.md = Marked(res.data.data.content)
      } else {
        this.$notification.warning({ message: '系统错误' })
      }
      this.loading = false
    })
    // 3. 将文章数据给this.md
  },
  methods: {
    editMd () {
      // 1. 判断用户是否登录
      this.isOpenLoginModal = true
      // router.push({
      //   path: 'edit/' + this.articleId,
      //   params: {
      //     articleId: this.articleId
      //   }
      // })
    },
    handleCancel () {
      this.isOpenLoginModal = false
    }
  }
}
</script>
<style scoped>
 .left-catalog >>>.ant-affix{
  top: 64px
}
 #md{
   padding-right: 3vw;
 }
</style>
