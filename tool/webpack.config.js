const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
module.exports = {
  mode: process.env.NODE_ENV || "production",
  devtool: "source-map",
  output: {
    filename: "[name].bundle.js",
    path: __dirname + "/dist",
  },
  plugins: [new HtmlWebpackPlugin()],
  devServer: {
    host: "localhost",
    port: 8080,
    hot: false,
    client: {
      reconnect: false,
    },
  },
};
