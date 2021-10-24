<template>
  <div style="height: 100%;padding: 0 2em">
    <NoodbHeader :is-show-search="isShowSearch"></NoodbHeader>
    <noodb-spin v-if="loading"></noodb-spin>
    <div class="edit-title">
      <span class="edit-title-span">  标题：</span>
      <a-input v-model="article.title" style="flex: 2"></a-input>
      <a-button type="primary" style="margin:0  20px" @click="save">保存</a-button>
    </div>
    <mavon-editor v-model="article.content" ref="mavonEditor" :autofocus="true" :scroll-style="true"
                  style="height: 80vh" :toolbars="toolbars" :subfield="!isPhoneScreen"
                  :default-open="isPhoneScreen?'edit':''"
                  @imgAdd="onImgAdd" @imgDel="onImgDel"
    >
    </mavon-editor>
  </div>
</template>
<script>
import NoodbHeader from '@/components/Header'
import 'highlight.js/styles/github.css'
import { isPhoneScreen } from '../asserts/js/utils'
import NoodbSpin from '@/components/Spin'
import EditArticleService from '@/asserts/js/eidtArticle'
import MavonEditConfig from '@/asserts/js/MavonEditorConfig'

export default {
  name: 'Edit',
  components: { NoodbHeader, NoodbSpin },
  data: function () {
    return {
      loading: true,
      isShowSearch: false,
      article: {},
      isPhoneScreen: false,
      editArticleService: new EditArticleService(this)
    }
  },
  computed: {
    toolbars: function () {
      if (this.isPhoneScreen) {
        return MavonEditConfig.phoneToolBars()
      } else {
        return MavonEditConfig.pcToolBars()
      }
    }

  },
  created () {
    const $vm = this
    window.addEventListener('resize', () => {
      $vm.isPhoneScreen = isPhoneScreen()
    })
  },
  beforeMount () {
    const id = this.$route.params.articleId
    // 新增博客
    if (id === 'new') {
      this.loading = false
      return
    }
    this.$http({
      url: this.$appUrl + this.autoPrefix() + '/article/one',
      method: 'GET',
      params: { id: id }

    }).then(res => {
      if (res.data.code === 0) {
        this.article = res.data.data
      } else {
        this.$notification.warning({ message: '系统错误' })
      }
      this.loading = false
    })
  },
  methods: {
    save () {
      const $vm = this
      this.$http({
        url: $vm.$appUrl + $vm.autoPrefix() + '/article/edit',
        method: 'POST',
        data: $vm.article
      }).then((res) => {
        if (res.data.code === 0) {
          $vm.$notification.info({ message: '保存成功' })
        } else {
          $vm.$notification.warning({ message: '保存失败' })
        }
      }).catch(() => {
        $vm.$notification.error({ message: '系统错误' })
      })
    },
    onImgAdd (pos, file) {
      const $vm = this
      this.editArticleService.uploadImage(file, this.article.id, (resp) => {
        $vm.$refs.mavonEditor.$img2Url(pos, resp.data.frontUrl)
      }, () => {
        $vm.$notification.error({ message: '上传图片失败' })
      })
    },
    onImgDel (pos, file) {
      console.log('del' + pos)
      console.log(file)
    }
  }
}
</script>
<style scoped>
>>> .v-note-wrapper {
  z-index: 0;
}

.edit-title {
  display: flex;
  padding-top: 64px;
  padding-bottom: 2em;
  justify-content: center;
  align-items: center
}

.edit-title-span {
  direction: ltr;
  text-align: center;
  font-size: 1.5em
}
</style>
