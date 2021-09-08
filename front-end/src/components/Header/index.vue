<template>
  <div class="noodb-layout">
      <a-layout-header>
        <h2 class="noodb-logo" @click="clickLogo">noodb</h2>
        <!--        <img src="./public/assert/img/logo.png">-->
        <a-input-search style="width: 200px;float: right;top:25%" v-show="isShowSearch"></a-input-search>
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
.noodb-logo{
  display: inline-block;
  cursor: pointer;
  font-size: 2em;
}
.noodb-layout .ant-layout-header{
  background: white;
  position: fixed;
  width: 100%;
  z-index: 1;
}

</style>
