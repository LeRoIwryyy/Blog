package com.sangeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sangeng.domain.entity.Category;
import com.sangeng.vo.ArticleListVo;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<ArticleListVo> serchList(String search);
}
