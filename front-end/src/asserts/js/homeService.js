/**
 * 所有Home.vue发出的请求业务
 */
export default class HomeService {
  constructor($vm) {
    this.$vm = $vm;
  }

  getAllArticleSummary(pageNum, pageSize, callBack, searchValue) {
    this.$vm
      .$http({
        url: this.$vm.$appUrl + this.$vm.autoPrefix() + '/article/all',
        method: 'GET',
        params: {
          pageNum: pageNum || 1,
          pageSize: pageSize || 15,
          searchValue: searchValue
        }
      })
      .then(res => {
        callBack(res.data);
      });
  }
}
