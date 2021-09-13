<template>
  <div style="height: 100%;padding: 0 2em">
    <NoodbHeader :is-show-search="isShowSearch"></NoodbHeader>
    <div class="edit-title">
      <span class="edit-title-span">  标题：</span>
      <a-input :default-value="article.title" style="flex: 2"></a-input>
    </div>
    <mavon-editor v-model="article.content" ref="mavonEditor" :autofocus="true" :scroll-style="true"
                  style="height: 80vh" :toolbars="toolbars" :subfield="!isPhoneScreen" :default-open="isPhoneScreen?'edit':''"></mavon-editor>
  </div>
</template>
<script>
import NoodbHeader from '@/components/Header'
import 'highlight.js/styles/github.css'
import { isPhoneScreen } from '../asserts/js/utils'

export default {
  name: 'Edit',
  components: { NoodbHeader },
  data: function () {
    return {
      isShowSearch: false,
      article: {
        id: '',
        content: '#### FactoryBean\n' +
            '\n' +
            'If a bean implements this interface, it is used as a factory for an object to expose, not directly as a bean instance that will be exposed itself.\n' +
            '\n' +
            '**FactoryBean以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，根据该Bean的ID从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身，如果要获取FactoryBean对象，请在id前面加一个&符号来获取。**\n' +
            '\n' +
            '#### DefaultListableBeanFactory\n' +
            'AbstractRefreshableApplicationContext为什么持有DefaultListableBeanFactory\n' +
            'BeanFactory的接口其实是由这个DefaultListableBeanFactory代理实现的。\n' +
            '比如：\n' +
            '```java\n' +
            '\t@Override\n' +
            '\tpublic Object getBean(String name) throws BeansException {\n' +
            '\t\tassertBeanFactoryActive();\n' +
            '\t\treturn getBeanFactory().getBean(name);\n' +
            '\t}\n' +
            '```\n' +
            '其中getBeanFactory()是由`AbstractRefreshableApplicationContext`\n' +
            '```java\n' +
            '\t@Override\n' +
            '\tpublic final ConfigurableListableBeanFactory getBeanFactory() {\n' +
            '\t\tDefaultListableBeanFactory beanFactory = this.beanFactory;\n' +
            '\t\tif (beanFactory == null) {\n' +
            '\t\t\tthrow new IllegalStateException("BeanFactory not initialized or already closed - " +\n' +
            '\t\t\t\t\t"call \'refresh\' before accessing beans via the ApplicationContext");\n' +
            '\t\t}\n' +
            '\t\treturn beanFactory;\n' +
            '\t}\n' +
            '```\n' +
            '#### 依赖查找\n' +
            '1. \n' +
            '2. 单一类型依赖查找\n' +
            '3. 集合类型\n' +
            '4. 层次类型\n' +
            '5. 延时\n' +
            '6. 安全\n' +
            '7. 内建可查找\n' +
            '8. 依赖查找中的经典异常\n' +
            '#### 依赖注入\n' +
            '  xml,annotation,api三种方式\n' +
            '1. setter注入方式\n' +
            '2. constructor注入方式\n' +
            '3. field注入\n' +
            ' @autowired注解,不能注入静态字段static \n' +
            '4. 方法注入method\n' +
            '5. 接口回调注入Aware接口 \n' +
            '#### @Autowired注入原理\n' +
            '1. bean的设置属性\n' +
            '   \n' +
            '   AbstractAutowiredCapableBeanFactory#populateBean()\n' +
            '    `````java\n' +
            '    for (BeanPostProcessor bp : getBeanPostProcessors()) {\n' +
            '\t\t\t\tif (bp instanceof InstantiationAwareBeanPostProcessor) {\n' +
            '\t\t\t\t\tInstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;\n' +
            '\t\t\t\t\tPropertyValues pvsToUse = ibp.postProcessProperties(pvs, bw.getWrappedInstance(), beanName);\n' +
            '\t\t\t\t\tif (pvsToUse == null) {\n' +
            '\t\t\t\t\t\tif (filteredPds == null) {\n' +
            '\t\t\t\t\t\t\tfilteredPds = filterPropertyDescriptorsForDependencyCheck(bw, mbd.allowCaching);\n' +
            '\t\t\t\t\t\t}\n' +
            '\t\t\t\t\t\tpvsToUse = ibp.postProcessPropertyValues(pvs, filteredPds, bw.getWrappedInstance(), beanName);\n' +
            '\t\t\t\t\t\tif (pvsToUse == null) {\n' +
            '\t\t\t\t\t\t\treturn;\n' +
            '\t\t\t\t\t\t}\n' +
            '\t\t\t\t\t}\n' +
            '\t\t\t\t\tpvs = pvsToUse;\n' +
            '\t\t\t\t}\n' +
            '\t\t\t}\n' +
            '   ``````\n' +
            '    处理bean的设置属性的前置和后置方法(InstantiationAwareBeanPostProcessor),其中@Autowired就是实现postProcessProperties()这个后置方法\n' +
            '2. AutowiredAnnotationBeanPostProcessor#postProcessProperties()方法\n' +
            '   ````java\n' +
            '    @Override\n' +
            '\tpublic PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) {\n' +
            '\t\tInjectionMetadata metadata = findAutowiringMetadata(beanName, bean.getClass(), pvs);\n' +
            '\t\ttry {\n' +
            '\t\t\tmetadata.inject(bean, beanName, pvs);\n' +
            '\t\t}\n' +
            '\t\tcatch (BeanCreationException ex) {\n' +
            '\t\t\tthrow ex;\n' +
            '\t\t}\n' +
            '\t\tcatch (Throwable ex) {\n' +
            '\t\t\tthrow new BeanCreationException(beanName, "Injection of autowired dependencies failed", ex);\n' +
            '\t\t}\n' +
            '\t\treturn pvs;\n' +
            '\t}\n' +
            '   `````\n' +
            '\n' +
            '  inject()\n' +
            '  `````java\n' +
            '  @Override\n' +
            '\t\tprotected void inject(Object bean, @Nullable String beanName, @Nullable PropertyValues pvs) throws Throwable {\n' +
            '\t\t\tField field = (Field) this.member;\n' +
            '\t\t\tObject value;\n' +
            '\t\t\tif (this.cached) {\n' +
            '\t\t\t\tvalue = resolvedCachedArgument(beanName, this.cachedFieldValue);\n' +
            '\t\t\t}\n' +
            '\t\t\telse {\n' +
            '\t\t\t\tDependencyDescriptor desc = new DependencyDescriptor(field, this.required);\n' +
            '\t\t\t\tdesc.setContainingClass(bean.getClass());\n' +
            '\t\t\t\tSet<String> autowiredBeanNames = new LinkedHashSet<>(1);\n' +
            '\t\t\t\tAssert.state(beanFactory != null, "No BeanFactory available");\n' +
            '\t\t\t\tTypeConverter typeConverter = beanFactory.getTypeConverter();\n' +
            '\t\t\t\ttry {\n' +
            '\t\t\t\t\tvalue = beanFactory.resolveDependency(desc, beanName, autowiredBeanNames, typeConverter);\n' +
            '\t\t\t\t}\n' +
            '\t\t\t\tcatch (BeansException ex) {\n' +
            '\t\t\t\t\tthrow new UnsatisfiedDependencyException(null, beanName, new InjectionPoint(field), ex);\n' +
            '\t\t\t\t}\n' +
            '\t\t\t\tsynchronized (this) {\n' +
            '\t\t\t\t\tif (!this.cached) {\n' +
            '\t\t\t\t\t\tif (value != null || this.required) {\n' +
            '\t\t\t\t\t\t\tthis.cachedFieldValue = desc;\n' +
            '\t\t\t\t\t\t\tregisterDependentBeans(beanName, autowiredBeanNames);\n' +
            '\t\t\t\t\t\t\tif (autowiredBeanNames.size() == 1) {\n' +
            '\t\t\t\t\t\t\t\tString autowiredBeanName = autowiredBeanNames.iterator().next();\n' +
            '\t\t\t\t\t\t\t\tif (beanFactory.containsBean(autowiredBeanName) &&\n' +
            '\t\t\t\t\t\t\t\t\t\tbeanFactory.isTypeMatch(autowiredBeanName, field.getType())) {\n' +
            '\t\t\t\t\t\t\t\t\tthis.cachedFieldValue = new ShortcutDependencyDescriptor(\n' +
            '\t\t\t\t\t\t\t\t\t\t\tdesc, autowiredBeanName, field.getType());\n' +
            '\t\t\t\t\t\t\t\t}\n' +
            '\t\t\t\t\t\t\t}\n' +
            '\t\t\t\t\t\t}\n' +
            '\t\t\t\t\t\telse {\n' +
            '\t\t\t\t\t\t\tthis.cachedFieldValue = null;\n' +
            '\t\t\t\t\t\t}\n' +
            '\t\t\t\t\t\tthis.cached = true;\n' +
            '\t\t\t\t\t}\n' +
            '\t\t\t\t}\n' +
            '\t\t\t}\n' +
            '\t\t\tif (value != null) {\n' +
            '\t\t\t\tReflectionUtils.makeAccessible(field);\n' +
            '\t\t\t\tfield.set(bean, value);\n' +
            '\t\t\t}\n' +
            '\t\t}\n' +
            '``````\n' +
            '![image-20210619143857043](assert/img/image-20210619143857043.png)',
        title: 'FactoryBean'
      },
      isPhoneScreen: false
    }
  },
  computed: {
    toolbars: function () {
      if (this.isPhoneScreen) {
        return {
          bold: true, // 粗体
          italic: true, // 斜体
          header: true, // 标题
          underline: true, // 下划线
          strikethrough: true, // 中划线
          mark: true // 标记
        }
      } else {
        return {
          bold: true, // 粗体
          italic: true, // 斜体
          header: true, // 标题
          underline: true, // 下划线
          strikethrough: true, // 中划线
          mark: true, // 标记
          superscript: true, // 上角标
          subscript: true, // 下角标
          quote: true, // 引用
          ol: true, // 有序列表
          ul: true, // 无序列表
          link: true, // 链接
          imagelink: true, // 图片链接
          code: true, // code
          table: true, // 表格
          // fullscreen: true, // 全屏编辑
          // readmodel: true, // 沉浸式阅读
          // htmlcode: true, // 展示html源码
          help: true, // 帮助
          /* 1.3.5 */
          undo: true, // 上一步
          redo: true, // 下一步
          trash: true, // 清空
          save: true, // 保存（触发events中的save事件）
          /* 1.4.2 */
          navigation: true, // 导航目录
          /* 2.1.8 */
          alignleft: true, // 左对齐
          aligncenter: true, // 居中
          alignright: true, // 右对齐
          /* 2.2.1 */
          subfield: true, // 单双栏模式
          preview: true // 预览
        }
      }
    }

  },
  created () {
    const $vm = this
    window.addEventListener('resize', () => {
      $vm.isPhoneScreen = isPhoneScreen()
    })
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
