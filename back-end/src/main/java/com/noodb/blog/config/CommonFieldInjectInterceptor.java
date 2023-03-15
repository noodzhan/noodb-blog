package com.noodb.blog.config;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.noodb.blog.entity.CommonField;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;


public class CommonFieldInjectInterceptor implements InnerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(CommonFieldInjectInterceptor.class);

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        BoundSql boundSql = ms.getBoundSql(parameter);
        if (boundSql.getParameterObject() instanceof CommonField) {
            //注入公共字段配置
        }
        logger.debug(boundSql.getSql());
    }
}
