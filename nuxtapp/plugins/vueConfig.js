export default (context, inject) => {
  //给客户端注入axios
  inject("http", context.$axios);
};
