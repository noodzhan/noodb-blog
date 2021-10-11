<template>
  <div class="noodb-layout">
    <a-layout-header style="align-items: center;display: flex">
      <img src="../../asserts/img/logo.png" height="70%" @click="clickLogo">
      <a-input-search style="width: 200px;margin-left: auto" v-show="isShowSearch"></a-input-search>

      <a-dropdown style="padding-left: 50px">
        <a-icon type="plus"></a-icon>
        <a-menu mode="horizontal"  slot="overlay">
          <a-menu-item><a href="blog/edit/new">写文章</a></a-menu-item>
        </a-menu>
      </a-dropdown>

    </a-layout-header>
  </div>
</template>

<script>
import router from '@/router'

export default {
  name: 'NoodbHeader',
  props: {
    isShowSearch: Boolean
  },
  methods: {
    clickLogo () {
      router.push('/')
    }
  }
}
</script>

<style scoped>
@media screen and (max-width: 480px) {
  .ant-layout-header {
    padding: 0 20px !important;
  }
}

.noodb-logo {
  display: inline-block;
  cursor: pointer;
  font-size: 2em;
}

.noodb-layout .ant-layout-header {
  background: white;
  position: fixed;
  width: 100%;
  z-index: 1;
}

>>> .ant-menu-horizontal {
  border-bottom: inherit;
}
</style>
