import Vue from "vue";
import Antd from "ant-design-vue";
import "ant-design-vue/dist/antd.css";

Vue.use(Antd);
Vue.prototype.$appUrl = process.env.NODE_ENV === "development" ? "" : "";

Vue.prototype.$notification.config({
  placement: "topRight",
  top: 70,
  duration: 3,
});
// window.isDev = process.env.NODE_ENV === "development";
Vue.prototype.autoPrefix = function () {
  if (process.env.NODE_ENV === "development") {
    return "/api";
  } else if (process.env.NODE_ENV === "production") {
    return "/api";
  }
};
