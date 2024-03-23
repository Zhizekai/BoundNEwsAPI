package com.ruoyi.web.service;

import java.util.List;

import com.ruoyi.web.domain.NewsArticle;

/**
 * 文章Service接口
 *
 * @author keloid
 * @date 2024-02-28
 */
public interface INewsArticleService {
    /**
     * 查询文章
     *
     * @param newsArticleId 文章主键
     * @return 文章
     */
    public NewsArticle selectNewsArticleByNewsArticleId(Long newsArticleId);


    /* 获取某一个tag的文章列表*/
    public List<NewsArticle> selectNewsArticleListByTagId(Long tagId);

    /* 获取某一个类型的文章列表*/
    public List<NewsArticle> selectNewsArticleListByCategoryId(Long categoryId);


    /* 获取微信文章列表*/
    public List<NewsArticle> selectWXNewsArticleList(NewsArticle newsArticle);


    /* 获取用户收藏列表*/
    public List<NewsArticle> selectWXNewsArticleCollectionList(String token);


    /* 获取用户点赞列表*/
    public List<NewsArticle> selectWXNewsArticleLikesList(String token);

    /* 随机获取指定数量文章*/
    public List<NewsArticle> selectRandomArticleList(Integer articleNUm);

    /* 获取文章评论数*/
    public Integer selectArticleCommentCount(Long newsArticleId);

    /* 获取文章收藏数*/
    public Integer selectArticleCollectionCount(Long newsArticleId) ;

    /**
     * 查询文章列表
     *
     * @param newsArticle 文章
     * @return 文章集合
     */
    public List<NewsArticle> selectNewsArticleList(NewsArticle newsArticle);


    /**
     * 新增文章
     *
     * @param newsArticle 文章
     * @return 结果
     */
    public int insertNewsArticle(NewsArticle newsArticle);

    /**
     * 修改文章
     *
     * @param newsArticle 文章
     * @return 结果
     */
    public int updateNewsArticle(NewsArticle newsArticle);

    /**
     * 批量删除文章
     *
     * @param newsArticleIds 需要删除的文章主键集合
     * @return 结果
     */
    public int deleteNewsArticleByNewsArticleIds(Long[] newsArticleIds);

    /**
     * 删除文章信息
     *
     * @param newsArticleId 文章主键
     * @return 结果
     */
    public int deleteNewsArticleByNewsArticleId(Long newsArticleId);
}
