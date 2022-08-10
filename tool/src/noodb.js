let noodbCookie = "";

async function gmRequest(config) {
  let GM_xmlhttpRequest = window.GM_xmlhttpRequest;
  return new Promise((resolve, reject) => {
    //tamper monkey插件函数
    console.log({
      url: config.url,
      method: config.method,
      param: config.param,
      cookie: config.cookie,
      data: config.data,
      headers: config.headers,
      responseType: "json",
    });
    GM_xmlhttpRequest({
      url: config.url,
      method: config.method,
      param: config.param,
      cookie: config.cookie,
      data: config.data,
      headers: config.headers,
      responseType: "json",
      onload: (resp) => {
        console.log(resp);
        resolve(resp.response);
      },
      onerror: (error) => {
        console.log(error);
        reject(error);
      },
    });
  });
}

function generateBlog(problemId, problemContent, code, lang) {
  //TODO 以后考虑一下js格式java代码，如果没有合适的思路，那只能后台实现，通过google java format
  let content = `# 题目 \n${problemContent} \n# 代码 \n\`\`\`${lang} \n${code}\n\`\`\` `;
  console.log(content);
  return content;
}

function isExist() {
  return false;
}

async function save(problemId, problemContent, code, lang) {
  let content = generateBlog(problemId, problemContent, code, lang);
  if (isExist()) {
    //更新
  }
  await getCookie();
  //新增
  return gmRequest({
    method: "POST",
    url: "https://noodb.com/api/article/edit",
    data: JSON.stringify({
      title: "leetcode -- " + problemId,
      content: content,
    }),
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
      Accept: "application/json",
    },
    cookie: noodbCookie,
  });
}

async function login() {
  return gmRequest({
    url: "https://noodb.com/api/user/login",
    method: "POST",
    data: JSON.stringify({ username: "noodzhan", password: "noodzhan" }),
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
      Accept: "application/json",
    },
  }).then((resp) => {
    console.log(resp);
    if (resp.code == 0) {
      noodbCookie = resp.data.token;
      console.log(noodbCookie);
      return resp.data.token;
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
