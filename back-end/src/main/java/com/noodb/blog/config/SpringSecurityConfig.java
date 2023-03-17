package com.noodb.blog.config;

import com.noodb.blog.security.JwtAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * security 配置类
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/10/1 6:13 下午
 */
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers("/article/all/**")
        .antMatchers("/article/one/**")
            .antMatchers("/article/search")
            .antMatchers("/article/createIndex")
        .antMatchers("/user/login");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("noodzhan")
        .password("noodzhan")
        .roles("USER")
        .and()
        .passwordEncoder(
            new PasswordEncoder() {
              @Override
              public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
              }

              @Override
              public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return true;
              }
            });
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .headers()
        .frameOptions()
        .disable()
        .and()
        .formLogin()
        .disable()
        .logout()
        .disable()
        .addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        // 不需要 session（不创建会话）
        .sessionManagement()
        .disable();
  }
}
