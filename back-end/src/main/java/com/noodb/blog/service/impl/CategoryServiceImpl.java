package com.noodb.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noodb.blog.mapper.CategoryMapper;
import com.noodb.blog.entity.Category;
import com.noodb.blog.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * 类别表(Category)表服务实现类
 *
 * @author noodzhan
 * @since 2021-08-14 20:18:57
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {
}
