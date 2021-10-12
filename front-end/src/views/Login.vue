<template>
  <a-modal :visible="visible" :footer="null" @cancel="handleCancel($event)">
    <a-form-model v-model="formObj" :label-col="{span:4}" :wrapper-col="{span: 20}" style="padding: 20px 15px">
      <a-form-item label="用户名">
        <a-input v-model="formObj.name">
          <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
        </a-input>
      </a-form-item>
      <a-form-item label="密码">
        <a-input v-model="formObj.password" type="password">
          <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
        </a-input>
      </a-form-item>
      <a-form-item class="login-footer" :wrapper-col="{span:24}">
        <a-button>
          重置
        </a-button>
        <a-button type="primary" @click="onSubmit" >
          登录
        </a-button>
      </a-form-item>
    </a-form-model>
  </a-modal>
</template>
<script>
export default {
  name: 'NoodbLogin',

  data: function () {
    return {
      formObj: {}
    }
  },
  computed: {
    visible: function () {
      return this.$store.state.openLoginModal
    }
  },
  methods: {
    handleCancel (event) {
      this.$emit('cancel', event)
    },
    onSubmit () {
      console.log('login')
    }

  }
}
</script>
<style scoped lang="css">
.login-footer{
  text-align: center
}
.login-footer button{
  margin: 0 50px;
}

</style>
