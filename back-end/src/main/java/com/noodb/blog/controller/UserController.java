package com.noodb.blog.controller;

import com.noodb.blog.entity.User;
import com.noodb.blog.jwt.JwtUtils;
import com.noodb.blog.util.R;
import com.noodb.blog.vo.LoginVO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * TODO
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/10/14 11:04 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @PostMapping("/login")
  public R<LoginVO> login(@Validated @RequestBody User user) {
    if (user.getUsername().equals("noodzhan") && user.getPassword().equals("noodzhan")) {
      LoginVO loginVO = new LoginVO();
      loginVO.setUsername("noodzhan");
      String token = JwtUtils.genToken(user);
      loginVO.setToken(token);
      // 通知springSecurity 登录成功。
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(
              user.getUsername(), token, Collections.emptyList());

      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

      return R.success(loginVO);
    }
    return R.fail();
  }
}
