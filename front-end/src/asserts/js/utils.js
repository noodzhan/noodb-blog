/**
 * 判断是否是手机端还是pc端
 * @returns {boolean}
 */
export function isPhoneScreen () {
  if (window.matchMedia('(min-width:768px)').matches) {
    return false
  } else {
    return true
  }
}
