<template>
  <NoodbLayout>
    <template v-slot:side>
     <div style="display: flex;flex-direction: column">
       <div>
       </div>
       <div>
         <h4>推荐书籍</h4>
         <div v-for="(item,index) in recommendBooks" :key="index" class="recommend-book" >
           {{index+1}} 、
           <a>{{item.name}}</a>
         </div>
       </div>
     </div>
    </template>
    <template v-slot:content >
      <div v-for="(i,index) in blogs" :key="index" class="post" @click="clickOnArticle(i)">
        <h3>{{index}} {{i.title}}</h3>
        <span style="cursor: pointer;">{{i.content}}}</span>
      </div>
    </template>
  </NoodbLayout>
</template>

<script>
import NoodbLayout from '@/components/Layout'
import router from '@/router'
export default {
  name: 'Home',
  components: {
    NoodbLayout
  },
  data: function () {
    return {
      blogs: [{
        title: '文章标题1',
        content: '有一种看似合理的说法：如果父元素 height:auto 子元素还支持 height:100%，则父 元 素 的 高 度 很 容 易 陷 入 死 循 环 ， 高 度 无 限 。 例 如 ， 一 个 <div> 元 素 里 面 有 一 张vertical-align 为 bottom 同时高度为 192 像素的图片，此时，该<div>高度就是 192像素，假设此时插入一个子元素，高度设为 100%，如果此时 height:100%可以计算，则子元素应该也是 192 像素。但是，父元素 height 值是 auto，岂不是现在高度要从原来的192 像素变成 384 像素，然后 height:100%的子元素高度又要变成 384 像素，父元素高度又双倍……死循环了！\n' +
            '\n' +
            '实际上，这种解释是错误的，大家千万别被误导。证据就是宽度也存在类似场景，但并没有死循环。例如，在下面这个例子中，父元素采用“最大宽度”，然后有一个 inline-block子元素宽度 100%：'
      }, {
        title: '文章标题2',
        content: '有一种看似合理的说法：如果父元素 height:auto 子元素还支持 height:100%，则父 元 素 的 高 度 很 容 易 陷 入 死 循 环 ， 高 度 无 限 。 例 如 ， 一 个 <div> 元 素 里 面 有 一 张vertical-align 为 bottom 同时高度为 192 像素的图片，此时，该<div>高度就是 192像素，假设此时插入一个子元素，高度设为 100%，如果此时 height:100%可以计算，则子元素应该也是 192 像素。但是，父元素 height 值是 auto，岂不是现在高度要从原来的192 像素变成 384 像素，然后 height:100%的子元素高度又要变成 384 像素，父元素高度又双倍……死循环了！\n' +
            '\n' +
            '实际上，这种解释是错误的，大家千万别被误导。证据就是宽度也存在类似场景，但并没有死循环。例如，在下面这个例子中，父元素采用“最大宽度”，然后有一个 inline-block子元素宽度 100%：'
      }, {
        title: '文章标题1',
        content: '有一种看似合理的说法：如果父元素 height:auto 子元素还支持 height:100%，则父 元 素 的 高 度 很 容 易 陷 入 死 循 环 ， 高 度 无 限 。 例 如 ， 一 个 <div> 元 素 里 面 有 一 张vertical-align 为 bottom 同时高度为 192 像素的图片，此时，该<div>高度就是 192像素，假设此时插入一个子元素，高度设为 100%，如果此时 height:100%可以计算，则子元素应该也是 192 像素。但是，父元素 height 值是 auto，岂不是现在高度要从原来的192 像素变成 384 像素，然后 height:100%的子元素高度又要变成 384 像素，父元素高度又双倍……死循环了！\n' +
            '\n' +
            '实际上，这种解释是错误的，大家千万别被误导。证据就是宽度也存在类似场景，但并没有死循环。例如，在下面这个例子中，父元素采用“最大宽度”，然后有一个 inline-block子元素宽度 100%：'
      }, {
        title: '文章标题1',
        content: '有一种看似合理的说法：如果父元素 height:auto 子元素还支持 height:100%，则父 元 素 的 高 度 很 容 易 陷 入 死 循 环 ， 高 度 无 限 。 例 如 ， 一 个 <div> 元 素 里 面 有 一 张vertical-align 为 bottom 同时高度为 192 像素的图片，此时，该<div>高度就是 192像素，假设此时插入一个子元素，高度设为 100%，如果此时 height:100%可以计算，则子元素应该也是 192 像素。但是，父元素 height 值是 auto，岂不是现在高度要从原来的192 像素变成 384 像素，然后 height:100%的子元素高度又要变成 384 像素，父元素高度又双倍……死循环了！\n' +
            '\n' +
            '实际上，这种解释是错误的，大家千万别被误导。证据就是宽度也存在类似场景，但并没有死循环。例如，在下面这个例子中，父元素采用“最大宽度”，然后有一个 inline-block子元素宽度 100%：'
      }, {
        title: '文章标题1',
        content: '有一种看似合理的说法：如果父元素 height:auto 子元素还支持 height:100%，则父 元 素 的 高 度 很 容 易 陷 入 死 循 环 ， 高 度 无 限 。 例 如 ， 一 个 <div> 元 素 里 面 有 一 张vertical-align 为 bottom 同时高度为 192 像素的图片，此时，该<div>高度就是 192像素，假设此时插入一个子元素，高度设为 100%，如果此时 height:100%可以计算，则子元素应该也是 192 像素。但是，父元素 height 值是 auto，岂不是现在高度要从原来的192 像素变成 384 像素，然后 height:100%的子元素高度又要变成 384 像素，父元素高度又双倍……死循环了！\n' +
            '\n' +
            '实际上，这种解释是错误的，大家千万别被误导。证据就是宽度也存在类似场景，但并没有死循环。例如，在下面这个例子中，父元素采用“最大宽度”，然后有一个 inline-block子元素宽度 100%：'
      }],
      recommendBooks: [{ name: 'JavaScript高级程序设计' }, { name: '深入迁出vuejs' }, { name: '代码之道' }, { name: '面向对象葵花宝典' }]
    }
  },
  methods: {
    clickOnArticle (article) {
      console.log(article)
      // 跳转到文章详情页面，并且通过参数传递文章id
      router.push('/blog')
    }
  }
}
</script>
<style scoped>
.post {
  margin: 1vh 1vh 0 0;
  padding-right: 2vw;
}
.recommend-book{
  padding-top: 5px;
}
.post:hover{
  background: #e5eaef;;
}

</style>
