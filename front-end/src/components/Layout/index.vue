<template>
  <div class="noodb-layout">
    <a-layout>
      <NoodbHeader :is-show-search="isPCComponent"></NoodbHeader>
<!--      <a-divider style="top: 64px;position: fixed;z-index: 1"></a-divider>-->
      <a-layout class="noodb-container">
        <a-layout-sider breakpoint="lg"
                        collapsed-width="0"
                        :trigger=null theme="light" @breakpoint="breakpoint" v-show="isPCComponent">
          <slot name="side">侧边区</slot>
        </a-layout-sider>
        <a-layout-content>
          <slot name="content">内容区</slot>
        </a-layout-content>
      </a-layout>
      <a-layout-footer>
        <span>版权所有@ www.noodb.com &&& 赣ICP备2021005048号-1</span>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
import router from '@/router'
import NoodbHeader from '@/components/Header'
export default {
  name: 'NoodbLayout',
  components: {
    NoodbHeader
  },
  data: function () {
    return {
      // 控制pc是否显示组件
      isPCComponent: true
    }
  },
  methods: {
    breakpoint (broken) {
      if (broken) {
        this.isPCComponent = false
      } else {
        this.isPCComponent = true
      }
    },
    clickLogo () {
      router.push('/')
    }
  }
}
</script>

<style scoped>
.noodb-layout .ant-layout-sider{
  padding: 0 1em;
}
.noodb-container{
  margin-top: 64px;
}
.noodb-layout .ant-layout-header{
  background: white;
  position: fixed;
  width: 100%;
  z-index: 1;
}
.noodb-layout .ant-layout-content{
  background: white;
  padding: 0 1em;
}
.noodb-layout .ant-layout-footer{
  text-align: center;
}
>>> .ant-divider-horizontal{
  margin: 0 0;
}
</style>
