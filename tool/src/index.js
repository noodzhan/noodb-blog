import addListener from "./listen";
import generateMarkdown from "./leetcode";
import save from "./noodb";

setTimeout(() => {
  //页面加载结束，获取markdown
  window.article = generateMarkdown();
  console.log(window.article);
}, 200);

let button = document.createElement("button");
button.style.width = "50px";
button.style.height = "20px";
button.style.position = "fixed";
button.style.top = "200px";
button.style.left = "200px";
button.textContent = "上传";
button.style.background = "red";

document.body.appendChild(button);
console.error(window.GM_xmlhttpRequest);
button.onclick = () => {
  console.log("开始");
  save("78. 子集", "jjjjjj");
};

addListener("", function (xhr) {
  console.log(xhr.orignUrl);
  let reg = new RegExp("^/problems/.+/submit/$");
  if (reg.test(xhr.orignUrl)) {
    console.log(xhr.responeText);
    console.log(xhr.body);
    save("12", window.article, xhr.body).then((resp) => {
      console.log(resp);
    });
  }
});
