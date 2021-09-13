<template>
  <NoodbLayout>
    <template v-slot:side>
     <div style="padding-left: 20px" class="left-catalog">
       <a-anchor :offsetTop="70">
         <a-anchor-link href="#components-anchor-demo-basic" title="Basic demo" />
         <a-anchor-link href="#components-anchor-demo-static" title="Static demo" />
         <a-anchor-link
             href="#components-anchor-demo-basic"
             title="Basic demo with Target"
             target="_blank"
         />
         <a-anchor-link href="#API" title="API">
           <a-anchor-link href="#Anchor-Props" title="Anchor Props" />
           <a-anchor-link href="#FactoryBean" title="Link Props" />
         </a-anchor-link>
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
          '### 1-3 \n' +
          '# 2 \n' +
          '## 2-1\n',
      articleId: 1234,
      tree: []
    }
  },
  mounted () {
    let lastHeading = 1
    // const tree = []
    const $vm = this
    const renderer = {
      heading (text, level, raw, slugger) {
        // console.error(text)
        // console.error(level)
        const node = {}
        node.label = text
        node.level = level
        node.chrild = []
        if (level <= lastHeading) {
          $vm.tree.push(node)
        } else {
          $vm.tree[$vm.tree.length - 1].chrild.push(node)
          lastHeading = level
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
    },
    buildTree ($vm, node, lastNodeLevel) {

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
