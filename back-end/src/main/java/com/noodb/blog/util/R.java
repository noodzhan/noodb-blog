package com.noodb.blog.util;

import lombok.Builder;
import lombok.Data;

/**
 * 返回前端Response对象
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/8/18 10:58 下午
 */
@Data
@Builder
public class R<T> {
    private Integer code;
    private T data;
    private String msg;

    public static R success(Object data){
        return R.builder().code(0).data(data).build();
    }

}
