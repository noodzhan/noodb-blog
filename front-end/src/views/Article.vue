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
        <a-tooltip placement="bottomLeft" title="编辑" >
          <a-icon type="edit" style="position: fixed;right: 0;padding-right: 3vw;padding-top: 10px" @click="editMd"/>
        </a-tooltip>
        <div id="md" v-html="md"/>
      </div>
    </template>
  </NoodbLayout>
</template>
<script>
import NoodbLayout from '@/components/Layout'
import Marked from 'marked'
import HighLight from 'highlight.js'
import router from '@/router'
export default {
  name: 'Article',
  components: {
    NoodbLayout
  },
  data: function () {
    return {
      md: '# 1 \n' +
          '## 1-2 \n' +
          '## 1-2 \n' +
          '### 1-3 \n' +
          '# 2 \n' +
          '## 2-1\n',
      articleId: 1234,
      titles: []
    }
  },
  mounted () {
    const $vm = this
    const renderer = {
      heading (text, level, raw, slugger) {
        if (level === 1) {
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
    // 2. 发起网络请求获取数据
    // 3. 将文章数据给this.md
    this.md = Marked(this.md)
  },
  methods: {
    editMd () {
      router.push('edit/' + this.articleId)
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
