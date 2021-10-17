package com.noodb.blog.config;

/**
 * security 配置类
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/10/1 6:13 下午
 */
// @EnableWebSecurity
// public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//  private List<String> url = Arrays.asList("/edit");
//
//  @Bean
//  public UserDetailsService userDetailsService() {
//    InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//    inMemoryUserDetailsManager.createUser(
//        User.withUsername("noodzhan")
//            .password("19970717.cf")
//            .authorities(Collections.emptyList())
//            .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
//            .build());
//    return inMemoryUserDetailsManager;
//  }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests().antMatchers("/api/blog/edit/*").authenticated();
//  }
// }
