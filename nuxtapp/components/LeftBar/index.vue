<template>
  <div>
    <a-card title="推荐文档" :bordered="false">
      <a slot="extra" href="/blog/1563541035307724801" target="_blank">
        <a-icon type="link" />
      </a>
      <div
        v-for="(item, index) in recommendDocs"
        :key="index"
        class="recommend-book"
      >
        <a :href="item.url">{{ item.name }}</a>
      </div>
    </a-card>
    <a-card title="推荐书籍" :bordered="false">
      <a slot="extra" href="https://www.noodb.com/books" target="_blank">
        <a-icon type="link" />
      </a>
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
        src="../../assets/img/payme.jpeg"
        width="100%"
        style="text-align: center"
      />
    </a-card>
  </div>
</template>
<script>
export default {
  name: 'leftBar',
  data: function () {
    return {
      recommendDocs: [
        { name: '滴答清单', url: 'https://dida365.com/webapp/#q/all/today' },
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
  }
};
</script>
<style lang="css" scoped>
.ant-card-extra a {
  color: black;
}
</style>
