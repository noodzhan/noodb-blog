import TurndownService from "turndown";

function generateMarkdown() {
  let href = window.location.href;
  let regExp = new RegExp("^https://leetcode.cn/problems/");
  if (!regExp.test(href)) {
    return undefined;
  }

  //1. 获取dom和样式
  //获取题目的信息。
  let leetcode = document
    .querySelector(".notranslate .notranslate")
    .outerHTML.replaceAll("<strong>", "")
    .replaceAll("</strong>", "");

  // console.log(leetcode);

  //复制样式
  document.querySelector("body>div").style = "";

  //2.获取代码,拦截这个请求，获取代码
  //https://leetcode.cn/problems/gray-code/submit/

  let turndownService = new TurndownService();
  let markdown = turndownService.turndown(leetcode);
  // console.log(markdown);
  return markdown;
}

function getprombleTitle() {
  return document.querySelector("h4 > a").text;
}

export { getprombleTitle };
export default generateMarkdown;
