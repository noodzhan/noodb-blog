<template>
  <NoodbLayout :hasLeft="true">
    <template v-slot:side>
      <div class="left-catalog" v-if="titles.length > 0">
        <a-anchor :offsetTop="70">
          <a-anchor-link
            v-for="(item, index) in titles"
            :href="'#' + item.id"
            :key="index"
            :title="item.title"
            :style="{ 'padding-left': 10 * item.level + 'px' }"
          />
        </a-anchor>
      </div>
      <LeftBar v-if="titles.length <= 0"></LeftBar>
    </template>
    <template v-slot:content>
      <div class="content">
        <a-tooltip placement="bottomLeft" title="编辑">
          <a-icon type="edit" @click="editMd" class="noodb-edit-icon" />
        </a-tooltip>
        <div class="article-title">{{ headTitle }}</div>
        <div id="md" v-html="md" class="markdown-body" />
        <noodb-spin v-if="loading"></noodb-spin>
      </div>
      <noodb-back-top />
    </template>
  </NoodbLayout>
</template>
<script>
import NoodbLayout from '@/components/Layout';
import NoodbSpin from '@/components/Spin';
import NoodbBackTop from '@/components/backTop';
import Markdownit from 'markdown-it';
import hljs from 'highlight.js';
import 'highlight.js/styles/github.css';
import 'mavon-editor/dist/markdown/github-markdown.min.css';
import LeftBar from '@/components/LeftBar';

export default {
  head() {
    return {
      title: this.headTitle,
      meta: [
        { charset: 'utf-8' },
        { name: 'viewport', content: 'width=device-width, initial-scale=1' },
        {
          hid: 'description',
          name: 'description',
          content: this.summary
        },
        {
          hid: 'keywords',
          name: 'keywords',
          content: Array.of(
            this.$serverUtil.keywords(),
            this.headTitle,
            this.$serverUtil.trim(this.summary)
          ).join(',')
        }
      ],
      link: [{ rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }],
      script: [
        {
          src: '/baiduStatistic.js'
        }
      ]
    };
  },
  name: 'Article',
  components: {
    NoodbLayout,
    NoodbSpin,
    NoodbBackTop,
    LeftBar
  },
  data: function () {
    return {
      loading: true,
      md: '',
      articleId: '',
      headTitle: '',
      titles: [],
      travelNodeList: []
    };
  },
  async fetch() {
    this.loading = true;
    // 1. 从路由中获取文章id
    // console.log(this.$route.params.articleId)
    // 2. 发起网络请求获取数据
    await this.$http({
      url: this.$appUrl + this.autoPrefix() + '/article/one',
      method: 'GET',
      params: { id: this.$router.currentRoute.params.articleId }
    }).then((res) => {
      if (res.data.code === 0 && res.data && res.data.data) {
        this.articleId = res.data.data.id;
        this.headTitle = res.data.data.title;
        this.summary = res.data.data.summary;
        let markdown = new Markdownit({
          html: true, // Enable HTML tags in source
          xhtmlOut: true, // Use '/' to close single tags (<br />).
          breaks: true, // Convert '\n' in paragraphs into <br>
          langPrefix: 'lang-', // CSS language prefix for fenced blocks. Can be
          linkify: true, // 自动识别url
          typographer: true,
          quotes: '“”‘’',
          highlight: function (str, lang) {
            if (lang && hljs.getLanguage(lang)) {
              try {
                return hljs.highlight(str, { language: lang }).value;
              } catch (__) {}
            }

            return ''; // use external default escaping
          }
        });
        let index = 0;
        let $vm = this;
        markdown.renderer.rules.heading_open = function (
          tokens,
          idx,
          options,
          env,
          self
        ) {
          let level = tokens[idx].markup.split('#').length - 1;
          let content = tokens[idx + 1].content;
          if (content) {
            $vm.titles.push({
              id: index,
              title: content,
              level: level
            });
          }
          return '<h' + level + ' id="' + index++ + '">';
        };
        this.md = markdown.render(res.data.data.content);
      } else {
        this.$notification.warning({ message: '当前博客不存在' });
        this.$router.push('/home');
      }
      this.loading = false;
    });
    // 3. 将文章数据给this.md
  },
  beforeMount() {},
  methods: {
    editMd() {
      this.$router.push({
        path: 'edit/' + this.articleId,
        params: {
          articleId: this.articleId
        }
      });
    }
  }
};
</script>
<style scoped>
.content {
  padding-bottom: 20px;
}
.left-catalog {
  padding-left: 20px;
}

.left-catalog >>> .ant-affix {
  top: 64px;
}

#md {
  padding-right: 3vw;
}

.noodb-edit-icon {
  position: fixed;
  right: 0;
  padding-right: 3vw;
  padding-top: 10px;
}
.article-title {
  font-size: 28px;
  word-wrap: break-word;
  color: #222226;
  font-weight: 600;
  margin: 0;
  word-break: break-all;
  padding-bottom: 20px;
}
</style>
