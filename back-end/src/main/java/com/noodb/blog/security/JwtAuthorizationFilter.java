package com.noodb.blog.security;

import com.noodb.blog.entity.User;
import com.noodb.blog.jwt.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

import static org.springframework.security.web.authentication.www.BasicAuthenticationConverter.AUTHENTICATION_SCHEME_BASIC;

/**
 * JWT 过滤器
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2022/9/9 00:07
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private AuthenticationEntryPoint authenticationEntryPoint = new Http403ForbiddenEntryPoint();

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    UsernamePasswordAuthenticationToken uPassToken = null;
    try {

      uPassToken = this.parseToken(request);
    } catch (Exception e) {
      if (e instanceof AuthenticationException) {
        this.authenticationEntryPoint.commence(
            request, response, new NoFoundToken("not found token in http headers"));
      } else {
        throw e;
      }
    }
    if (Objects.isNull(uPassToken)) {
      this.authenticationEntryPoint.commence(
          request, response, new NoFoundToken("not found token in http headers"));
    } else {
      // 将认证信息存入 Spring 安全上下文中
      SecurityContextHolder.getContext().setAuthentication(uPassToken);
      chain.doFilter(request, response);
    }
  }

  private UsernamePasswordAuthenticationToken parseToken(HttpServletRequest request)
      throws UnsupportedEncodingException {
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null) {
      return null;
    }
    header = header.trim();
    if (!StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
      return null;
    }
    if (header.equalsIgnoreCase(AUTHENTICATION_SCHEME_BASIC)) {
      throw new BadCredentialsException("Empty basic authentication token");
    }
    byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
    byte[] decoded = decode(base64Token);
    String token = new String(decoded, StandardCharsets.UTF_8);
    User user = JwtUtils.getSubject(User.class, token);
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
    return usernamePasswordAuthenticationToken;
  }

  private byte[] decode(byte[] base64Token) {
    try {
      return Base64.getDecoder().decode(base64Token);
    } catch (IllegalArgumentException ex) {
      throw new BadCredentialsException("Failed to decode basic authentication token");
    }
  }

  class NoFoundToken extends AuthenticationException {
    public NoFoundToken(String msg, Throwable cause) {
      super(msg, cause);
    }

    public NoFoundToken(String msg) {
      super(msg);
    }
  }
}
