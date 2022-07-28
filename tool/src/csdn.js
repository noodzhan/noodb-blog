const request = require("request");
const CryptoJS = require("crypto-js");
const axios = require("axios").default;
function createUuid() {
  return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (c) {
    var r = (Math.random() * 16) | 0;
    var v = c === "x" ? r : (r & 0x3) | 0x8;
    return v.toString(16);
  });
}

function signCSDN(apiPath, contentType = "application/json") {
  var once = createUuid();
  // var once = "da4e1ba8-6e4f-4da0-91cf-2bf469a92cc7";
  var signStr = `POST\n*/*\n\napplication/json\n\nx-ca-key:203803574\nx-ca-nonce:${once}\n${apiPath}`;
  // console.log(signStr);
  var hash = CryptoJS.HmacSHA256(signStr, "9znpamsyl2c7cdrr9sas0le9vbc3r6ba");

  // console.log(hash.toString(CryptoJS.enc.Base64));
  // console.log(hash.toString(CryptoJS.enc.Hex));

  var hashInBase64 = CryptoJS.enc.Base64.stringify(hash);
  console.log(hashInBase64);
  return {
    accept: "*/*",
    "content-type": contentType,
    "x-ca-key": 203803574,
    "x-ca-nonce": once,
    "x-ca-signature": hashInBase64,
    "x-ca-signature-headers": "x-ca-key,x-ca-nonce",
  };
}

async function getMetaData() {
  let res;
  await new Promise((resolve, reject) => {
    let cookie =
      "uuid_tt_dd=10_19687702220-1656899702558-285803; c_adb=1; _ga=GA1.2.77427306.1656899826; ssxmod_itna=7qfx2D0DB70QKBKGHD8QbTx98wxPY5DOiK0+0DBuGi4iNDnD8x7YDvmmEWimYPojDbfG=0Q42fPo6mmDEWoM1x0aDbqGk=ear4GGjxBYDQxAYDGDDPXD84DrD7gCkyvxYPG0DiKGRDlKFExDajxi3yx2OhxGCBxDC07PDwx0CvgIadRvmDiHqUxbODGyOD75pDlaqA+0OtISfYY5zCtoDIoS5DB+zD0FXPIb7/g5Dvcdsvxib7oEUfrHh/ir3+iheZnqxLlG4Gix41C1qGV=4YmG47W4giQDY=D4K7NN4DDpyb7+Y+YD; ssxmod_itna2=7qfx2D0DB70QKBKGHD8QbTx98wxPY5DOiK0+D8qpGqGXKiqGabAmOk8x8EMKhIRUK3lrT2tqe53uptr9DcGKm1emo4RnFVQYbMQ+O8O=kmFuj8FfymkpgWI4rUcO7I71F1RI8ZOlDrLS/h5E87tzzdq6YQ6xAkKbGmKqYGtkedd14njeWjQfQ0KzCljVj0Aul3NUznT1A7D6YRoOaM=tE8aESWr8tjpjB3WkK/jDxZQEbt4ORfhiphLClR74xuDSBwtzN+izsl8Ksw65hLt8iAxd13ibUokvUqCmBZU/jhznyIL2dy6MgKzrpslNnO6LQUD+UOnmYrE/CvBGDmiwBmwslqAgK9Y4iD02=ixSN87tXmQ1ieQP2smdmCbxgDvaR1ltW34X7L/GTOAu8Iu7IK8Uod8Ie2pNI2s2U5Kd3eem3At3Yz6=7xm7zwhtqmSIhx4WDG2zDeRIvterDhb+GOkYFmxY4eY4+vRiUiuSQaoXdCV71oiFe2SmxPWGdjq=DlaKCCx08DiQ3+GieLuADod3uzg6WEC7DiYOrQDNq4dhGqBHPmnUB3ietFsQARhwmmaqx3FktdEmlSmYD===; UserName=guidgeek; UserInfo=587b76c69ad4485891432286700f0fa0; UserToken=587b76c69ad4485891432286700f0fa0; UserNick=noodzhan; AU=176; UN=guidgeek; BT=1657336668402; p_uid=U010000; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22guidgeek%22%2C%22scope%22%3A1%7D%7D; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19687702220-1656899702558-285803!5744*1*guidgeek; _gid=GA1.2.632349740.1658716296; c_dl_prid=-; c_dl_rid=1658718563379_989882; c_dl_fref=https://blog.csdn.net/u010376788/article/details/51337312; c_dl_fpage=/download/weixin_38732343/12724440; c_dl_um=-; dc_sid=66474d1298818e01a51eb9c271179da5; c_segment=11; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1657769093,1658107708,1658209196,1658800967; firstDie=1; log_Id_click=51; c_pref=https%3A//www.baidu.com/link; c_ref=https%3A//www.google.com/; c_first_ref=www.google.com; dc_session_id=10_1658921304726.565233; c_first_page=https%3A//blog.csdn.net/hongweigg/article/details/125207095; c_dsid=11_1658921299639.066418; c_page_id=default; dc_tos=rfofv7; log_Id_pv=109; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1658921300; log_Id_view=403";
    const options = {
      url: "https://me.csdn.net/api/user/show",
      headers: {
        cookie: cookie,
      },
    };
    request(options, (error, response, body) => {
      if (!error && response.statusCode == 200) {
        resolve(body);
      } else {
        reject();
      }
    });
  }).then((body) => {
    // console.log(body);
    console.log("2");
    res = JSON.parse(body);
    return JSON.parse(body);
  });
  console.log("1");
  return {
    uid: res.data.csdnid,
    title: res.data.username,
    avatar: res.data.avatarurl,
    type: "csdn",
    displayName: "CSDN",
    supportTypes: ["markdown", "html"],
    home: "https://mp.csdn.net/",
    icon: "https://g.csdnimg.cn/static/logo/favicon32.ico",
  };
}

function editPost(post) {
  let cookie =
    "uuid_tt_dd=10_19687702220-1656899702558-285803; c_adb=1; _ga=GA1.2.77427306.1656899826; ssxmod_itna=7qfx2D0DB70QKBKGHD8QbTx98wxPY5DOiK0+0DBuGi4iNDnD8x7YDvmmEWimYPojDbfG=0Q42fPo6mmDEWoM1x0aDbqGk=ear4GGjxBYDQxAYDGDDPXD84DrD7gCkyvxYPG0DiKGRDlKFExDajxi3yx2OhxGCBxDC07PDwx0CvgIadRvmDiHqUxbODGyOD75pDlaqA+0OtISfYY5zCtoDIoS5DB+zD0FXPIb7/g5Dvcdsvxib7oEUfrHh/ir3+iheZnqxLlG4Gix41C1qGV=4YmG47W4giQDY=D4K7NN4DDpyb7+Y+YD; ssxmod_itna2=7qfx2D0DB70QKBKGHD8QbTx98wxPY5DOiK0+D8qpGqGXKiqGabAmOk8x8EMKhIRUK3lrT2tqe53uptr9DcGKm1emo4RnFVQYbMQ+O8O=kmFuj8FfymkpgWI4rUcO7I71F1RI8ZOlDrLS/h5E87tzzdq6YQ6xAkKbGmKqYGtkedd14njeWjQfQ0KzCljVj0Aul3NUznT1A7D6YRoOaM=tE8aESWr8tjpjB3WkK/jDxZQEbt4ORfhiphLClR74xuDSBwtzN+izsl8Ksw65hLt8iAxd13ibUokvUqCmBZU/jhznyIL2dy6MgKzrpslNnO6LQUD+UOnmYrE/CvBGDmiwBmwslqAgK9Y4iD02=ixSN87tXmQ1ieQP2smdmCbxgDvaR1ltW34X7L/GTOAu8Iu7IK8Uod8Ie2pNI2s2U5Kd3eem3At3Yz6=7xm7zwhtqmSIhx4WDG2zDeRIvterDhb+GOkYFmxY4eY4+vRiUiuSQaoXdCV71oiFe2SmxPWGdjq=DlaKCCx08DiQ3+GieLuADod3uzg6WEC7DiYOrQDNq4dhGqBHPmnUB3ietFsQARhwmmaqx3FktdEmlSmYD===; UserName=guidgeek; UserInfo=587b76c69ad4485891432286700f0fa0; UserToken=587b76c69ad4485891432286700f0fa0; UserNick=noodzhan; AU=176; UN=guidgeek; BT=1657336668402; p_uid=U010000; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22guidgeek%22%2C%22scope%22%3A1%7D%7D; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19687702220-1656899702558-285803!5744*1*guidgeek; _gid=GA1.2.632349740.1658716296; c_dl_prid=-; c_dl_rid=1658718563379_989882; c_dl_fref=https://blog.csdn.net/u010376788/article/details/51337312; c_dl_fpage=/download/weixin_38732343/12724440; c_dl_um=-; dc_sid=66474d1298818e01a51eb9c271179da5; c_segment=11; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1657769093,1658107708,1658209196,1658800967; firstDie=1; log_Id_click=51; c_pref=https%3A//www.baidu.com/link; c_ref=https%3A//www.google.com/; c_first_ref=www.google.com; dc_session_id=10_1658921304726.565233; c_first_page=https%3A//blog.csdn.net/hongweigg/article/details/125207095; c_dsid=11_1658921299639.066418; c_page_id=default; dc_tos=rfofv7; log_Id_pv=109; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1658921300; log_Id_view=403";
  var headers = signCSDN("/blog-console-api/v3/mdeditor/saveArticle");
  let res;

  const options = {
    url: "https://bizapi.csdn.net/blog-console-api/v3/mdeditor/saveArticle",
    headers: {
      cookie: cookie,
    },
    method: "POST",
    data: JSON.stringify(post),
  };
  Object.assign(options.headers, headers);
  Object.assign(options.headers, {
    origin: "https://editor.csdn.net",
    referer: "https://editor.csdn.net/",
    "user-agent":
      "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36",
  });
  request(options, (error, response, body) => {
    console.log(response);
    console.log(eval("'" + body.msg + "'"));
    if (!error && response.statusCode == 200) {
      resolve(JSON.parse(body));
    } else {
    }
  });
}

// getMetaData().then((res) => {
//   console.log(res);
// });

// editPost({
//   title: "测试11111111111111111111111111111111",
//   markdowncontent: "# 测试\n## 测试\n### 测试\n# 测试\n## 测试\n### 测试\n",
//   content:
//     '<h1><a id="_0"></a>测试</h1>\n<h2><a id="_1"></a>测试</h2>\n<h3><a id="_2"></a>测试</h3>\n<h1><a id="_3"></a>测试</h1>\n<h2><a id="_4"></a>测试</h2>\n<h3><a id="_5"></a>测试</h3>\n\n',
//   readType: "public",
//   level: 1,
//   tags: "vue.js",
//   status: 0,
//   categories: "",
//   type: "original",
//   original_link: "",
//   authorized_status: false,
//   Description: "测试标题",
//   not_auto_saved: "1",
//   source: "pc_mdeditor",
//   cover_images: [],
//   cover_type: 0,
//   is_new: 1,
//   vote_id: 0,
//   pubStatus: "publish",
// });

let header = signCSDN("/blog-console-api/v3/mdeditor/saveArticle");

header.cookie =
  "uuid_tt_dd=10_19687702220-1656899702558-285803; c_adb=1; _ga=GA1.2.77427306.1656899826; ssxmod_itna=7qfx2D0DB70QKBKGHD8QbTx98wxPY5DOiK0+0DBuGi4iNDnD8x7YDvmmEWimYPojDbfG=0Q42fPo6mmDEWoM1x0aDbqGk=ear4GGjxBYDQxAYDGDDPXD84DrD7gCkyvxYPG0DiKGRDlKFExDajxi3yx2OhxGCBxDC07PDwx0CvgIadRvmDiHqUxbODGyOD75pDlaqA+0OtISfYY5zCtoDIoS5DB+zD0FXPIb7/g5Dvcdsvxib7oEUfrHh/ir3+iheZnqxLlG4Gix41C1qGV=4YmG47W4giQDY=D4K7NN4DDpyb7+Y+YD; ssxmod_itna2=7qfx2D0DB70QKBKGHD8QbTx98wxPY5DOiK0+D8qpGqGXKiqGabAmOk8x8EMKhIRUK3lrT2tqe53uptr9DcGKm1emo4RnFVQYbMQ+O8O=kmFuj8FfymkpgWI4rUcO7I71F1RI8ZOlDrLS/h5E87tzzdq6YQ6xAkKbGmKqYGtkedd14njeWjQfQ0KzCljVj0Aul3NUznT1A7D6YRoOaM=tE8aESWr8tjpjB3WkK/jDxZQEbt4ORfhiphLClR74xuDSBwtzN+izsl8Ksw65hLt8iAxd13ibUokvUqCmBZU/jhznyIL2dy6MgKzrpslNnO6LQUD+UOnmYrE/CvBGDmiwBmwslqAgK9Y4iD02=ixSN87tXmQ1ieQP2smdmCbxgDvaR1ltW34X7L/GTOAu8Iu7IK8Uod8Ie2pNI2s2U5Kd3eem3At3Yz6=7xm7zwhtqmSIhx4WDG2zDeRIvterDhb+GOkYFmxY4eY4+vRiUiuSQaoXdCV71oiFe2SmxPWGdjq=DlaKCCx08DiQ3+GieLuADod3uzg6WEC7DiYOrQDNq4dhGqBHPmnUB3ietFsQARhwmmaqx3FktdEmlSmYD===; UserName=guidgeek; UserInfo=587b76c69ad4485891432286700f0fa0; UserToken=587b76c69ad4485891432286700f0fa0; UserNick=noodzhan; AU=176; UN=guidgeek; BT=1657336668402; p_uid=U010000; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22guidgeek%22%2C%22scope%22%3A1%7D%7D; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19687702220-1656899702558-285803!5744*1*guidgeek; _gid=GA1.2.632349740.1658716296; c_dl_prid=-; c_dl_rid=1658718563379_989882; c_dl_fref=https://blog.csdn.net/u010376788/article/details/51337312; c_dl_fpage=/download/weixin_38732343/12724440; c_dl_um=-; dc_sid=66474d1298818e01a51eb9c271179da5; c_segment=11; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1657769093,1658107708,1658209196,1658800967; firstDie=1; log_Id_click=51; c_pref=https%3A//www.baidu.com/link; c_ref=https%3A//www.google.com/; c_first_ref=www.google.com; dc_session_id=10_1658921304726.565233; c_first_page=https%3A//blog.csdn.net/hongweigg/article/details/125207095; c_dsid=11_1658921299639.066418; c_page_id=default; dc_tos=rfofv7; log_Id_pv=109; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1658921300; log_Id_view=403";
axios({
  method: "POST",
  url: "https://bizapi.csdn.net/blog-console-api/v3/mdeditor/saveArticle",
  headers: header,
  data: {
    title: "测试11111111111111111111111111111111",
    markdowncontent: "# 测试\n## 测试\n### 测试\n# 测试\n## 测试\n### 测试\n",
    content:
      '<h1><a id="_0"></a>测试</h1>\n<h2><a id="_1"></a>测试</h2>\n<h3><a id="_2"></a>测试</h3>\n<h1><a id="_3"></a>测试</h1>\n<h2><a id="_4"></a>测试</h2>\n<h3><a id="_5"></a>测试</h3>\n\n',
    readType: "public",
    level: 1,
    tags: "vue.js",
    status: 0,
    categories: "",
    type: "original",
    original_link: "",
    authorized_status: false,
    Description: "测试标题",
    not_auto_saved: "1",
    source: "pc_mdeditor",
    cover_images: [],
    cover_type: 0,
    is_new: 1,
    vote_id: 0,
    pubStatus: "publish",
  },
}).then((resp) => {
  console.log(resp);
});
