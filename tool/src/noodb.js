// import axios from "axios";

let noodbCookie = "";

async function axios(config) {
  let GM_xmlhttpRequest = window.GM_xmlhttpRequest;
  return new Promise((resolve, reject) => {
    //tamper monkey插件函数
    GM_xmlhttpRequest({
      url: config.url,
      method: config.method,
      param: config.param,
      cookie: config.cookie,
      data: config,
      onload: (resp) => {
        console.log(resp);
        resolve(resp);
      },
      onerror: (error) => {
        reject(error);
      },
    });
  });
}

axios.post = async (config) => {
  let confCopy = {};
  Object.assign(confCopy, config);
  console.log(config, confCopy);
  confCopy.method = "POST";
  if (confCopy.headers) {
    Object.keys(confCopy.headers).includes("cookie");
    if (confCopy.headers.cookie) {
      confCopy.cookie = config.headers.cookie;
      delete confCopy.headers.cookie;
    }
  }
  await axios(confCopy);
};

function generateBlog(problemId, problemContent, code) {
  //TODO 以后考虑一下js格式java代码，如果没有合适的思路，那只能后台实现，通过google java format
  let content = `# 题目 \n${problemContent} \n# 代码 \n\`\`\`java \n${code}\n\`\`\` `;
  console.log(content);
  return content;
}

function isExist() {
  return false;
}

async function save(problemId, problemContent, code) {
  let content = generateBlog(problemId, problemContent, code);
  if (isExist()) {
    //更新
  }
  await getCookie();
  //新增
  return axios.post({
    url: "https://noodb.com/api/article/edit",
    data: {
      title: problemId,
      content: content,
    },
    headers: {
      cookie: noodbCookie,
    },
  });
}

async function login() {
  return axios
    .post({
      url: "https://noodb.com/api/user/login",
      username: "noodzhan",
      password: "noodzhan",
    })
    .then((resp) => {
      console.log(resp);
      if (resp.data.code == 0) {
        noodbCookie = resp.data.data.cookie;
        return resp.data.data.cookie;
      }
    });
}

async function getCookie() {
  if (noodbCookie) {
    return noodbCookie;
  }
  return login();
}

export default save;
