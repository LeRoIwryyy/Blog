package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.Article;
import com.sangeng.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    //注入公共模块的ArticleService接口
    private ArticleService articleService;


    //----------------------------------测试mybatisPlus---------------------------------

//    @GetMapping("/list")
//    //Article是公共模块的实体类
//    public List<Article> test(){
//        //查询数据库的所有数据
//        return articleService.list();
//    }

    //----------------------------------测试统一响应格式-----------------------------------

    @GetMapping("/hotArticleList")
    //ResponseResult是huanf-framework工程的domain目录的类
    public ResponseResult hotArticleList(){
        //查询热门文章，封装成ResponseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }
}