<template>
  <div class="noodb-layout">
    <a-layout-header>
      <div class="noodb-header">
        <div class="noodb-logo">
          <img
            src="../../assets/img/logo.png"
            height="50px"
            @click="clickLogo"
          />
        </div>
        <a-menu
          mode="horizontal"
          style="padding-left: 10px"
          :style="{ lineHeight: '60px' }"
          :selectable="false"
        >
          <a-menu-item key="1" href="/blog/1563541035307724801" target="_blank"
            >文档汇总</a-menu-item
          >
          <a-menu-item key="2">在线图书</a-menu-item>
          <a-menu-item key="3">任务管理</a-menu-item>
          <a-menu-item key="4">文章分类</a-menu-item>
        </a-menu>
        <a-input-search
          class="header-search"
          v-show="isShowSearch"
          @search="onSearch"
          size="large"
          placeholder="搜索文章"
        ></a-input-search>
      </div>

      <!-- <img src="../../assets/img/logo.png" height="70%" @click="clickLogo" />
        <a class="nav link">文档汇总</a>
        <a class="nav link">在线图书</a>
        <a class="nav link">任务管理</a>
        <a class="nav link">文章分类</a>
        <a-input-search
          class="header-search"
          v-show="isShowSearch"
          @search="onSearch"
        ></a-input-search>

        <a-dropdown v-if="isShowPlus" class="header-dropdown">
          <a-icon type="plus"></a-icon>
          <a-menu mode="horizontal" slot="overlay">
            <a-menu-item
              ><a href="javascript:void(0)" @click="clickWriteArticle"
                >写文章</a
              ></a-menu-item
            >
          </a-menu>
        </a-dropdown>
        <a href="https://github.com/noodzhan/noodb-blog" class="github-link">
          <svg
            style="justify-content: center"
            height="20"
            aria-hidden="true"
            viewBox="0 0 16 16"
            version="1.1"
            width="20"
            data-view-component="true"
            class="octicon octicon-mark-github v-align-middle"
          >
            <path
              fill-rule="evenodd"
              d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0016 8c0-4.42-3.58-8-8-8z"
            ></path>
          </svg>
        </a> -->
    </a-layout-header>
  </div>
</template>

<script>
// import router from '@/router';

export default {
  name: 'NoodbHeader',
  props: {
    isShowSearch: Boolean
  },
  methods: {
    clickLogo() {
      this.$router.push('/');
    },
    clickWriteArticle() {
      this.$router.push('/blog/edit/new');
    },
    onSearch(value) {
      this.$emit('headSearch', value);
    }
  },
  computed: {
    isShowPlus() {
      if (this.$route.path.includes('blog/edit/new')) {
        return false;
      }
      return true;
    }
  }
};
</script>

<style scoped>
@media screen and (max-width: 480px) {
  .ant-layout-header {
    padding: 0 20px !important;
  }
  .header-search {
    display: none;
  }
  .header-dropdown {
    margin-left: 27%;
  }
}
.header-dropdown {
  padding-left: 20px;
}
.noodb-logo {
  cursor: pointer;
  line-height: 60px;
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
  display: flex;
  padding-left: 300px;
  padding-right: 300px;
}
.ant-layout-header {
  border-top: 2px solid #096dd9;
}
.noodb-header .container {
  margin-left: auto;
  margin-right: auto;
  max-width: 1145px;
}

.github-link {
  font-weight: 500;
  color: #9b9b9b;
  margin-left: 20px;
  padding-right: 3%;
  text-decoration: none;
  display: flex;
}
.header-search {
  width: 300px;
  margin-left: auto;
}
.ant-menu-horizontal > .ant-menu-item:hover {
  color: #1890ff;
  border-bottom: 2px solid white;
}
.ant-menu-item {
  font-size: 18px;
  font-weight: bold;
  color: rgba(0, 0, 0, 0.55);
}
/deep/ .anticon.anticon-search.ant-input-search-icon > svg {
  height: 1.3em;
  width: 1.3em;
}
</style>
