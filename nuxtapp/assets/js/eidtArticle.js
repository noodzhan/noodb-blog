export default class EditArticleService {
  constructor($vm) {
    this.$vm = $vm;
  }

  uploadImage(file, articleId, successFn, errorFn) {
    let token;
    if (this.$vm.$store.state.isLogin) {
      token = this.$vm.$store.state.userInfo.token;
      token = window.btoa(token);
    }
    const formData = new FormData();
    const articleImage = {};
    articleImage.articleId = articleId;
    articleImage.systemUrl = '123';
    formData.append('file', file);
    formData.append(
      'articleImages',
      new Blob([JSON.stringify(articleImage)], { type: 'application/json' })
    );
    this.$vm
      .$http({
        url: this.$vm.$appUrl + this.$vm.autoPrefix() + '/article/img',
        method: 'post',
        data: formData,
        headers: {
          Authorization: 'Bearer ' + token
        }
      })
      .then(resp => {
        successFn(resp.data);
      })
      .catch(error => {
        errorFn(error);
      });
  }

  deleteApi(ids, successFn) {
    let token;
    if (this.$vm.$store.state.isLogin) {
      token = this.$vm.$store.state.userInfo.token;
      token = window.btoa(token);
    }
    this.$vm
      .$http({
        url: this.$vm.$appUrl + this.$vm.autoPrefix() + '/article/delete',
        method: 'post',
        data: ids,
        headers: {
          Authorization: 'Bearer ' + token
        }
      })
      .then(resp => {
        successFn(resp);
      });
  }
}
