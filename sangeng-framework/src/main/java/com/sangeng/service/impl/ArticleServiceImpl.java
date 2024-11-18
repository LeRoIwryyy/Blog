package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.constants.SystemCanstants;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Article;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.service.ArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.vo.HotArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//ServiceImpl是mybatisPlus官方提供的
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ResponseResult hotArticleList() {

        //查询热门文章，封装成ResponseResult返回。把所有查询条件写在queryWrapper里面
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //查询的不能是草稿。也就是Status字段不能是0
        queryWrapper.eq(Article::getStatus, SystemCanstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序。也就是根据ViewCount字段降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只能查询出来10条消息。当前显示第一页的数据，每页显示10条数据
        Page<Article> page = new Page<>(SystemCanstants.ARTICLE_STATUS_CURRENT,SystemCanstants.ARTICLE_STATUS_SIZE);
        page(page,queryWrapper);

        //获取最终的查询结果，把结果封装在Article实体类里面会有很多不需要的字段
        List<Article> articles = page.getRecords();

        //解决: 把结果封装在HotArticleVo实体类里面，在HotArticleVo实体类只写我们要的字段
        //List<HotArticleVO> articleVos = new ArrayList<>();
        //for (Article xxarticle : articles) {
        //    HotArticleVO xxvo = new HotArticleVO();
        //    //使用spring提供的BeanUtils类，来实现bean拷贝。第一个参数是源数据，第二个参数是目标数据，把源数据拷贝给目标数据
        //    BeanUtils.copyProperties(xxarticle,xxvo); //xxarticle就是Article实体类，xxvo就是HotArticleVo实体类
        //    //把我们要的数据封装成集合
        //    articleVos.add(xxvo);
        //}
        //注释掉，使用我们定义的BeanCopyUtils工具类的copyBeanList方法。如下

        //一行搞定
        List<HotArticleVO> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVO.class);


        return ResponseResult.okResult(articleVos);
    }
}