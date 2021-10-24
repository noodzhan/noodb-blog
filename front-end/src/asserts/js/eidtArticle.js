export default class EditArticleService {
  constructor ($vm) {
    this.$vm = $vm
  }

  uploadImage (file, articleId, successFn, errorFn) {
    const formData = new FormData()
    const articleImage = {}
    articleImage.articleId = articleId
    articleImage.systemUrl = '123'
    formData.append('file', file)
    formData.append('articleImages', new Blob([JSON.stringify(articleImage)], { type: 'application/json' }))
    this.$vm.$http({
      url: this.$vm.$appUrl + this.$vm.autoPrefix() + '/article/img',
      method: 'post',
      data: formData
    }).then(resp => {
      successFn(resp.data)
    }).catch(error => {
      errorFn(error)
    })
  }
}
