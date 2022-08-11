import addListener from "./listen";
import generateMarkdown, { getprombleTitle } from "./leetcode";
import save from "./noodb";

function init() {
  window.article = generateMarkdown();
  window.problemTitle = getprombleTitle();
  console.log(window.article);
  console.log(window.problemTitle);
}

let timer = undefined;

function checkIsRenender() {
  let href = window.location.href;
  let regExp = new RegExp("^https://leetcode.cn/problems/");
  if (!regExp.test(href)) {
    return;
  }
  let dom = document.querySelector(".notranslate .notranslate");
  if (dom) {
    init();
    mounted(
      document.querySelector("button[data-cypress='RunCode']").parentElement
    );
    if (!timer) {
      clearTimeout(timer);
    } else {
      timer = setTimeout(checkIsRenender, 300);
    }
  }
}
checkIsRenender();

//对含问题的a标签，添加监听
let observerDomArray = document.querySelectorAll("a[href^='/problems/']");
observerDomArray.forEach((dom) => {
  dom.onclick = init;
});

function createSyncButton() {
  let button = document.createElement("button");
  button.style.width = "80px";
  button.style.height = "30px";
  button.textContent = "同步";
  button.style.fontSize = "13px";
  button.style.cursor = "pointer";
  button.style.marginLeft = "10px";
  button.onclick = () => {
    console.log("手动同步");
    save(
      window.problemTitle,
      window.article,
      window.code,
      window.leetcodeLang
    ).then((resp) => {
      if (resp.code == 0) {
        window.open("https://noodb.com/blog/" + resp.data, "_blank");
      }
    });
  };
  return button;
}

function mounted(parent) {
  if (parent) {
    parent.appendChild(createSyncButton());
  }
}

addListener("", function (xhr) {
  console.log(xhr.orignUrl);
  let reg = new RegExp("^/problems/.+/submit/$");
  if (reg.test(xhr.orignUrl)) {
    let data = JSON.parse(xhr.body);
    window.code = data.typed_code;
    window.leetcodeLang = data.lang;
    save(window.problemTitle, window.article, data.typed_code, data.lang).then(
      (resp) => {
        if (resp.code == 0) {
          window.open("https://noodb.com/blog/" + resp.data, "_blank");
        }
      }
    );
  }
});
