module.exports = {
  devServer: {
    port: 8080,
    proxy: {
      '/api': {
        target: 'http://localhost:8095',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    },
    disableHostCheck: true
  },
  configureWebpack: {
    devtool: 'source-map'
  }
}
