package com.noodb.blog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析markdown工具类
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/9/12 10:12 下午
 */
public class MarkDownUtils {
  public static void getTitleTree(String markdown) {
    final String regex = "^(#{1,6}\\s*[\\S]+)";
    final String string = "";
    final String subst = "$1";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(string);
    final String result = matcher.replaceAll(subst);
    //        System.out.println(result);
  }

  public static void main(String[] args) {
    MarkDownUtils.getTitleTree("");
  }
}
