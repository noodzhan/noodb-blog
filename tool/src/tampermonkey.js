// ==UserScript==
// @name         leetcode
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  leetcode push tool!
// @author       You
// @match        https://leetcode.cn/problems/*
// @icon         data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==
// @grant        GM_xmlhttpRequest
// @connect      noodb.com
// ==/UserScript==
unsafeWindow.GM_xmlhttpRequest = GM_xmlhttpRequest;
(function () {
  "use strict";
  if (location.href === "http://localhost:8080/") return;
  window.onload = function () {
    var script = document.createElement("script");
    script.src = "http://localhost:8080/main.bundle.js";
    document.body.appendChild(script);
  };
})();
