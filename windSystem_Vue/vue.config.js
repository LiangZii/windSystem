module.exports = {
    // hash 模式下可使用
    // publicPath: process.env.NODE_ENV === 'development' ? '/' : './',
    publicPath: '/',
    outputDir: 'dist',
    assetsDir: 'static',
    lintOnSave: false,//process.env.NODE_ENV === 'development',
    productionSourceMap: false,
    devServer: {
      port: port,
      open: false,//是否自动打开浏览器
      overlay: {
        warnings: false,
        errors: false
      },
      proxy: {
        '/api': {
          target: process.env.VUE_APP_BASE_API,
          changeOrigin: true,
          pathRewrite: {
            '^/api': '/'
          }
        },
        '/person': {
          target: process.env.VUE_APP_BASE_PERSONSYS,
          changeOrigin: true,
          pathRewrite: {
            '^/person': '/'
          }
        },
        '/pms': {
          target: process.env.VUE_APP_BASE_AUTHORITY,
          changeOrigin: true,
          pathRewrite: {
            '^/pms': '/'
          }
        }
      }
    }}