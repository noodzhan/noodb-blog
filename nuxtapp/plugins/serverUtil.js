export default ({ app }, inject) => {
  const serverUtil = {};
  serverUtil.keywords = function () {
    return 'noodb个人博客,noodb,noodzhan,博客网站,www.noodb.com';
  };
  serverUtil.trim = function (text) {
    return text && text.replace('/ /g', '');
  };
  inject('serverUtil', serverUtil);
};
