import addListener from "./listen";
import generateMarkdown from "./leetcode";
import save from "./noodb";

setTimeout(() => {
  //页面加载结束，获取markdown
  window.article = generateMarkdown();
  console.log(window.article);
}, 200);

addListener("", function(xhr) {
  console.log(xhr.orignUrl);
  let reg = new RegExp("^/problems/.+/submit/$");
  if (reg.test(xhr.orignUrl)) {
    console.log(xhr.responeText);
    console.log(xhr.body);
    save("12", window.article, xhr.body).then(resp => {
      console.log(resp);
    });
  }
});
