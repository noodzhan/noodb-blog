import axios from "axios";
console.log("------------------ start");
async function getMetaData() {
  var res = await axios.get("https://me.csdn.net/api/user/show");
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

console.log(getMetaData());
