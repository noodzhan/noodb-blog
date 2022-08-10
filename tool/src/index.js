import addListener from "./listen";
import generateMarkdown, { getprombleTitle } from "./leetcode";
import save from "./noodb";

function init() {
  console.log(window.location.href);
  setTimeout(() => {
    //页面加载结束，获取markdown
    window.article = generateMarkdown();
    window.problemTitle = getprombleTitle();
    console.log(window.article);
    console.log(window.problemTitle);
  }, 500);
}
init();

let observerDomArray = document.querySelectorAll("a[href^='/problems/']");
observerDomArray.forEach((dom) => {
  dom.onclick = init;
});

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
  save(
    window.problemTitle,
    window.article,
    "class Solution {\n    private int[] nums;\n    private List<List<Integer>> result = new ArrayList();\n\n    public List<List<Integer>> subsets(int[] nums) {\n        this.nums = nums;\n        generate(0,-11,Arrays.stream(nums).boxed().collect(Collectors.toList()),new ArrayList());\n        result.add(new ArrayList());\n        return result;\n    }\n\n    \n    private void generate(int cur,int max,List<Integer> list,List<Integer>choose){\n\n        for(int i=0;i<list.size();i++){\n            if(max < list.get(i)){\n                int temp = list.get(i);\n                List<Integer> tm = new ArrayList();\n                choose.add(temp);\n                tm.addAll(choose);\n                result.add(tm);\n                //移除当前选中的\n                list.remove(i);\n                generate(cur+1,Math.max(temp,max),list,choose);\n                //添加的时候，要保证顺序。不然之后回溯的时候就会有问题。\n                list.add(i,temp);\n                choose.remove(choose.indexOf(temp));\n            }\n        }\n    }\n\n}",
    "java"
  ).then((resp) => {
    if (resp.code == 0) {
      window.open("https://noodb.com/blog/" + resp.data, "_blank");
    }
  });
};

addListener("", function (xhr) {
  console.log(xhr.orignUrl);
  let reg = new RegExp("^/problems/.+/submit/$");
  if (reg.test(xhr.orignUrl)) {
    let data = JSON.parse(xhr.body);
    save(window.problemTitle, window.article, data.typed_code, data.lang).then(
      (resp) => {
        if (resp.code == 0) {
          window.open("https://noodb.com/blog/" + resp.data, "_blank");
        }
      }
    );
  }
});
