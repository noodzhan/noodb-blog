<template>
  <div style="margin: 20vh auto;width: 300px;display: flex;flex-direction: column;">
    <img src="@/asserts/img/logo.png" style="margin: 15px auto">
    <a-form-model v-model="formObj" :label-col="{span:4}" :wrapper-col="{span: 20}"
    >
      <a-form-item label="用户名">
        <a-input v-model="formObj.username">
          <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)"/>
        </a-input>
      </a-form-item>
      <a-form-item label="密码">
        <a-input v-model="formObj.password" type="password">
          <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)"/>
        </a-input>
      </a-form-item>
      <a-form-item class="login-footer" :wrapper-col="{span:24}">
        <a-button>
          重置
        </a-button>
        <a-button type="primary" @click="onSubmit">
          登录
        </a-button>
      </a-form-item>
    </a-form-model>
  </div>
</template>
<script>

export default {
  name: 'NoodbLogin',
  components: {},
  data: function () {
    return {
      formObj: {}
    }
  },
  computed: {
    visible: function () {
      return this.$store.state.isLogin
    }
  },
  methods: {
    onSubmit () {
      this.$http({
        url: this.$appUrl + this.autoPrefix() + '/user/login',
        method: 'post',
        data: this.formObj
      }).then(resp => {
        if (resp.data.code === 0) {
          // 成功
          this.$store.commit('setLoginFlag', true)
          this.$store.commit('setUserInfo', resp.data.data)
          this.$router.push('/home')
        } else {
          this.$notification.error({ message: '登入失败' })
        }
      })
    }

  }
}
</script>
<style scoped lang="css">
.login-footer {
  text-align: center
}

.login-footer button {
  margin: 0 30px;
}

</style>
