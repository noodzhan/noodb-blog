package com.noodb.blog.util;

/**
 * 返回前端Response对象
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/8/18 10:58 下午
 */
public class R<T> {
  private Integer code;
  private T data;
  private String msg;

  R(Integer code, T data, String msg) {
    this.code = code;
    this.data = data;
    this.msg = msg;
  }

  public static R success(Object data) {
    return R.builder().code(0).data(data).build();
  }

  public static R fail() {
    return R.builder().code(-1).build();
  }

  public static <T> RBuilder<T> builder() {
    return new RBuilder<T>();
  }

  public Integer getCode() {
    return this.code;
  }

  public T getData() {
    return this.data;
  }

  public String getMsg() {
    return this.msg;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof R)) return false;
    final R<?> other = (R<?>) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$code = this.getCode();
    final Object other$code = other.getCode();
    if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
    final Object this$data = this.getData();
    final Object other$data = other.getData();
    if (this$data == null ? other$data != null : !this$data.equals(other$data)) return false;
    final Object this$msg = this.getMsg();
    final Object other$msg = other.getMsg();
    if (this$msg == null ? other$msg != null : !this$msg.equals(other$msg)) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof R;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $code = this.getCode();
    result = result * PRIME + ($code == null ? 43 : $code.hashCode());
    final Object $data = this.getData();
    result = result * PRIME + ($data == null ? 43 : $data.hashCode());
    final Object $msg = this.getMsg();
    result = result * PRIME + ($msg == null ? 43 : $msg.hashCode());
    return result;
  }

  public String toString() {
    return "R(code=" + this.getCode() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ")";
  }

  public static class RBuilder<T> {
    private Integer code;
    private T data;
    private String msg;

    RBuilder() {}

    public RBuilder<T> code(Integer code) {
      this.code = code;
      return this;
    }

    public RBuilder<T> data(T data) {
      this.data = data;
      return this;
    }

    public RBuilder<T> msg(String msg) {
      this.msg = msg;
      return this;
    }

    public R<T> build() {
      return new R<T>(code, data, msg);
    }

    public String toString() {
      return "R.RBuilder(code=" + this.code + ", data=" + this.data + ", msg=" + this.msg + ")";
    }
  }
}
