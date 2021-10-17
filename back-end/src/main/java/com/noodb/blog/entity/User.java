package com.noodb.blog.entity;

import javax.validation.constraints.NotBlank;

/**
 * TODO
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/10/14 11:09 下午
 */
public class User {
  private Long id;
  @NotBlank private String username;
  @NotBlank private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
