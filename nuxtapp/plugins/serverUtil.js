export default ({ app }, inject) => {
  const serverUtil = {};
  serverUtil.keywords = function () {
    return 'noodb个人博客,noodzhan,博客网站,vuejs,vue,springboot,www.noodb.com';
  };
  inject('serverUtil', serverUtil);
};
