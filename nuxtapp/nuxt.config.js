export default {
  /*
   ** Plugins - https://nuxtjs.org/docs/2.x/directory-structure/plugins
   */
  plugins: [
    '~/plugins/antd.js',
    '~/plugins/vueConfig.js',
    '~/plugins/markedit.js'
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
  dev: process.env.NODE_ENV !== 'production'
};
