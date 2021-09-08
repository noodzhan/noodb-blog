<template>
  <NoodbLayout>
    <template v-slot:side>
     <div style="padding-left: 20px" class="left-catalog">
       <a-anchor :offsetTop="64">
         <a-anchor-link href="#components-anchor-demo-basic" title="Basic demo" />
         <a-anchor-link href="#components-anchor-demo-static" title="Static demo" />
         <a-anchor-link
             href="#components-anchor-demo-basic"
             title="Basic demo with Target"
             target="_blank"
         />
         <a-anchor-link href="#API" title="API">
           <a-anchor-link href="#Anchor-Props" title="Anchor Props" />
           <a-anchor-link href="#FactoryBean" title="Link Props" />
         </a-anchor-link>
       </a-anchor>
     </div>
    </template>
    <template v-slot:content>
      <Blog></Blog>
    </template>
  </NoodbLayout>
</template>
<script>
import Blog from '@/components/Blog'
import NoodbLayout from '@/components/Layout'
export default {
  name: 'Article',
  components: {
    Blog,
    NoodbLayout
  }
}
</script>
<style scoped>
 .left-catalog >>>.ant-affix{
  top: 64px
}

</style>
