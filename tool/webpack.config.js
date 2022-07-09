const path = require("path");
module.exports = {
  mode: process.env.NODE_ENV || "production",
  devtool: "source-map",
  output: {
    filename: "[name].bundle.js",
    path: __dirname + "/dist",
  },
  devServer: {
    host: "localhost",
    port: 8080,
  },
};
