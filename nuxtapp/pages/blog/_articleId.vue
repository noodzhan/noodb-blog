<template>
  <NoodbLayout>
    <template v-slot:side>
      <div class="left-catalog">
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
    </template>
    <template v-slot:content>
      <div>
        <a-tooltip placement="bottomLeft" title="编辑">
          <a-icon type="edit" @click="editMd" class="noodb-edit-icon" />
        </a-tooltip>
        <div id="md" v-html="md" />
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
import MarkedWrapper from '@/assets/js/MarkedWrapper';

export default {
  head: {
    title: this,
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      {
        hid: 'description',
        name: 'description',
        content: 'my website description'
      }
    ],
    link: [{ rel: 'icon', type: 'image/x-icon', href: '/img/favicon.ico' }]
  },
  name: 'Article',
  components: {
    NoodbLayout,
    NoodbSpin,
    NoodbBackTop
  },
  data: function () {
    return {
      loading: true,
      md: '',
      articleId: '',
      titles: [],
      travelNodeList: [],
      markedWrapper: null
    };
  },
  fetchOnServe: true,
  async fetch() {
    this.loading = true;
    // 1. 从路由中获取文章id
    // console.log(this.$route.params.articleId)
    // 2. 发起网络请求获取数据
    this.markedWrapper = new MarkedWrapper();
    await this.$http({
      url: this.$appUrl + this.autoPrefix() + '/article/one',
      method: 'GET',
      params: { id: this.$router.currentRoute.params.articleId }
    }).then((res) => {
      if (res.data.code === 0 && res.data && res.data.data) {
        this.articleId = res.data.data.id;
        this.markedWrapper.setSrc(res.data.data.content);
        this.md = this.markedWrapper.renderer();
        this.titles = this.markedWrapper.getHeaderList();
        // 设置网页title
        // document.title = res.data.data.title;
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
      router.push({
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
</style>
