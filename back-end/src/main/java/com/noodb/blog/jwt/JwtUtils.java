package com.noodb.blog.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noodb.blog.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

/**
 * jwt 工具类
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/10/14 11:28 下午
 */
public class JwtUtils {

  /** 使用hs256方式，加密和解密都是找个密钥 */
  private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  private static JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(getKey()).build();

  private static JwtSerialize jwtSerialize = new JwtUtils.JwtSerialize();

  public static String genToken(Object payload) {

    return Jwts.builder().signWith(key).setSubject(jwtSerialize.getJson(payload)).compact();
  }

  public static <T> T getSubject(Class<T> t, String jwt) {
    Jws<Claims> claimsJws = jwtParser.parseClaimsJws(jwt);
    return (T) jwtSerialize.parseObj(claimsJws.getBody().getSubject(), t);
  }

  /**
   * 判断jwt是否签名
   *
   * @param jwt jsonwebtoken
   * @return
   */
  public static boolean isSigned(String jwt) {
    return jwtParser.isSigned(jwt);
  }

  public static Key getKey() {
    return key;
  }

  public static JwtParser getJwtParser() {
    return jwtParser;
  }

  /** 封装序列化工具，作为内部类 */
  static class JwtSerialize {
    private ObjectMapper objectMapper = new ObjectMapper();

    public String getJson(Object o) {
      String result = "";
      try {
        result = objectMapper.writeValueAsString(o);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      return result;
    }

    public Object parseObj(String str, Class clazz) {
      Object o = null;
      try {
        o = objectMapper.readValue(str, clazz);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      return o;
    }
  }

  public static void main(String[] args) {
    JwtUtils jwtUtils = new JwtUtils();
    User user = new User();
    user.setUsername("noodb");
    user.setPassword("noodb");
    String jwt = jwtUtils.genToken(user);
    User payLoad = jwtUtils.getSubject(User.class, jwt);
    System.out.println(payLoad);
  }
}
