<template>
  <div class="noodb-layout">
    <a-layout-header class="noodb-header">
      <img src="../../asserts/img/logo.png" height="70%" @click="clickLogo">

      <a-input-search style="width: 200px;margin-left: auto" v-show="isShowSearch" @search="onSearch"></a-input-search>

      <a-dropdown style="padding-left: 20px" v-if="isShowPlus" :class="{'mobile-plus':!isShowSearch}">
        <a-icon type="plus"></a-icon>
        <a-menu mode="horizontal" slot="overlay">
          <a-menu-item><a href="javascript:void(0)" @click="clickWriteArticle">写文章</a></a-menu-item>
        </a-menu>
      </a-dropdown>
      <a href="https://github.com/noodzhan/noodb-blog" class="github-link">
        <svg style="justify-content: center" height="20" aria-hidden="true" viewBox="0 0 16 16" version="1.1" width="20"
             data-view-component="true"
             class="octicon octicon-mark-github v-align-middle">
          <path fill-rule="evenodd"
                d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0016 8c0-4.42-3.58-8-8-8z"></path>
        </svg>
      </a>
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
    },
    clickWriteArticle () {
      router.push('/blog/edit/new')
    },
    onSearch (value) {
      this.$emit('headSearch', value)
    }

  },
  computed: {
    isShowPlus () {
      if (this.$route.path.includes('blog/edit/new')) {
        return false
      }
      return true
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

.mobile-plus {
  margin-left: auto;
}

>>> .ant-menu-horizontal {
  border-bottom: inherit;
}

.noodb-header {
  align-items: center;
  display: flex
}

.github-link {
  font-weight: 500;
  color: #9b9b9b;
  margin-left: 20px;
  padding-right: 3%;
  text-decoration: none;
  display: flex
}

</style>
