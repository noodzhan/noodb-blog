export default {
  /*
   ** Plugins - https://nuxtjs.org/docs/2.x/directory-structure/plugins
   */
  plugins: [
    { src: '~/plugins/antd.js' },
    { src: '~/plugins/vueConfig.js' },
    { src: '~/plugins/markedit.js' },
    { src: '~/plugins/serverUtil.js' },
    { src: '~/plugins/storeCache.js', ssr: false }
  ],

  modules: ['@nuxtjs/axios', '@nuxtjs/proxy'],
  axios: {
    baseURL: 'https://www.noodb.com/', // Used as fallback if no runtime config is provided
    proxy: true
  },
  proxy: {
    '/api': {
      target: 'https://noodb.com/api',
      pathRewrite: {
        '^/api': '/'
      }
    }
  },
  router: {
    middleware: 'history'
  },
  dev: process.env.NODE_ENV !== 'production'
};
