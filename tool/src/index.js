(function () {
  if (typeof window.CustomEvent === "function") return false;

  function CustomEvent(event, params) {
    params = params || { bubbles: false, cancelable: false, detail: undefined };
    var evt = document.createEvent("CustomEvent");
    evt.initCustomEvent(
      event,
      params.bubbles,
      params.cancelable,
      params.detail
    );
    return evt;
  }
  CustomEvent.prototype = window.Event.prototype;
  window.CustomEvent = CustomEvent;
})();
(function () {
  function ajaxEventTrigger(event) {
    var ajaxEvent = new CustomEvent(event, { detail: this });
    window.dispatchEvent(ajaxEvent);
  }

  var oldXHR = window.XMLHttpRequest;

  function newXHR() {
    var realXHR = new oldXHR();

    realXHR.addEventListener(
      "abort",
      function () {
        ajaxEventTrigger.call(this, "ajaxAbort");
      },
      false
    );
    realXHR.addEventListener(
      "error",
      function () {
        ajaxEventTrigger.call(this, "ajaxError");
      },
      false
    );
    realXHR.addEventListener(
      "load",
      function () {
        ajaxEventTrigger.call(this, "ajaxLoad");
      },
      false
    );
    realXHR.addEventListener(
      "loadstart",
      function () {
        ajaxEventTrigger.call(this, "ajaxLoadStart");
      },
      false
    );
    realXHR.addEventListener(
      "progress",
      function () {
        ajaxEventTrigger.call(this, "ajaxProgress");
      },
      false
    );
    realXHR.addEventListener(
      "timeout",
      function () {
        ajaxEventTrigger.call(this, "ajaxTimeout");
      },
      false
    );
    realXHR.addEventListener(
      "loadend",
      function () {
        ajaxEventTrigger.call(this, "ajaxLoadEnd");
      },
      false
    );
    realXHR.addEventListener(
      "readystatechange",
      function () {
        ajaxEventTrigger.call(this, "ajaxReadyStateChange");
      },
      false
    );

    let send = realXHR.send;
    realXHR.send = function (...arg) {
      send.apply(realXHR, arg);
      realXHR.body = arg[0];
      ajaxEventTrigger.call(realXHR, "ajaxSend");
    };

    let open = realXHR.open;
    realXHR.open = function (...arg) {
      open.apply(realXHR, arg);
      realXHR.method = arg[0];
      realXHR.orignUrl = arg[1];
      realXHR.async = arg[2];
      ajaxEventTrigger.call(realXHR, "ajaxOpen");
    };

    let setRequestHeader = realXHR.setRequestHeader;
    realXHR.requestHeader = {};
    realXHR.setRequestHeader = function (name, value) {
      realXHR.requestHeader[name] = value;
      setRequestHeader.call(realXHR, name, value);
    };
    return realXHR;
  }

  window.XMLHttpRequest = newXHR;
})();
var Gpins_data = {};
// 监听页面的ajax
window.addEventListener("ajaxReadyStateChange", function (e) {
  let xhr = e.detail;
  if (xhr.readyState == 4 && xhr.status == 200) {
    // xhr.getAllResponseHeaders()  响应头信息
    // xhr.requestHeader            请求头信息
    // xhr.responseURL              请求的地址
    // xhr.responseText             响应内容
    // xhr.orignUrl                 请求的原始参数地址
    // xhr.body                     post参数，（get参数在url上面）

    console.log(xhr);
  }
});
// console.log({msg:'注入完成',time: (new Date()).valueOf(),data: new Date()});
