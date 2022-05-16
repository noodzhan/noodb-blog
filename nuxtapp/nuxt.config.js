export default {
  /*
   ** Plugins - https://nuxtjs.org/docs/2.x/directory-structure/plugins
   */
  plugins: ["~/plugins/antd.js"],
  modules: ['@nuxtjs/axios'],
  axios: {
    baseURL: 'https://www.noodb.com/api', // Used as fallback if no runtime config is provided
  },
};
