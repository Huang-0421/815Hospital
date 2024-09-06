const { defineConfig } = require('@vue/cli-service')
module.exports = {
  devServer: {
    port: 8082,
    proxy:'http://192.168.1.191:8081'// 将端口号设置为8081，可以根据需要修改
  }
}
