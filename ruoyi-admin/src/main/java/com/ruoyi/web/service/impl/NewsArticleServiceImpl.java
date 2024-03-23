package com.ruoyi.web.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.NewsArticleMapper;
import com.ruoyi.web.domain.NewsArticle;
import com.ruoyi.web.service.INewsArticleService;

import javax.annotation.Resource;

/**
 * 文章Service业务层处理
 *
 * @author keloid
 * @date 2024-02-28
 */
@Service
public class NewsArticleServiceImpl implements INewsArticleService {
    @Resource
    private NewsArticleMapper newsArticleMapper;

    /**
     * 查询文章
     *
     * @param newsArticleId 文章主键
     * @return 文章
     */
    @Override
    public NewsArticle selectNewsArticleByNewsArticleId(Long newsArticleId) {

        return newsArticleMapper.selectNewsArticleByNewsArticleId(newsArticleId);
    }

    @Override
    public List<NewsArticle> selectNewsArticleListByTagId(Long tagId) {
        return newsArticleMapper.selectNewsArticleListByTagId(tagId);
    }

    /* 获取某一个类型的文章列表*/
    @Override
    public List<NewsArticle> selectNewsArticleListByCategoryId(Long categoryId) {
        return newsArticleMapper.selectNewsArticleListByCategoryId(categoryId);
    }

    @Override
    public List<NewsArticle> selectWXNewsArticleList(NewsArticle newsArticle) {
        return newsArticleMapper.selectWXNewsArticleList(newsArticle);
    }


    /* 获取用户收藏列表*/
    @Override
    public List<NewsArticle> selectWXNewsArticleCollectionList(String token) {
        return newsArticleMapper.selectNewsArticleCollectionListByToken(token);
    }

    /* 获取用户点赞列表*/
    @Override
    public List<NewsArticle> selectWXNewsArticleLikesList(String token) {
        return newsArticleMapper.selectNewsArticleLikesListByToken(token);
    }

    /* 随机获取文章列表*/
    @Override
    public List<NewsArticle> selectRandomArticleList(Integer articleNUm) {
        return newsArticleMapper.selectRandomArticleList(articleNUm);
    }

    /* 获取文章评论数*/
    @Override
    public Integer selectArticleCommentCount(Long newsArticleId) {
        return newsArticleMapper.selectArticleCommentCount(newsArticleId);
    }

    /* 获取文章收藏数*/
    @Override
    public Integer selectArticleCollectionCount(Long newsArticleId) {
        return newsArticleMapper.selectArticleCollectionCount(newsArticleId);
    }

    /**
     * 查询文章列表
     *
     * @param newsArticle 文章
     * @return 文章
     */
    @Override
    public List<NewsArticle> selectNewsArticleList(NewsArticle newsArticle) {
        return newsArticleMapper.selectNewsArticleList(newsArticle);
    }

    /**
     * 新增文章
     *
     * @param newsArticle 文章
     * @return 结果
     */
    @Override
    public int insertNewsArticle(NewsArticle newsArticle) {
        newsArticle.setCreateTime(DateUtils.getNowDate());
        return newsArticleMapper.insertNewsArticle(newsArticle);
    }

    /**
     * 修改文章
     *
     * @param newsArticle 文章
     * @return 结果
     */
    @Override
    public int updateNewsArticle(NewsArticle newsArticle) {
        return newsArticleMapper.updateNewsArticle(newsArticle);
    }

    /**
     * 批量删除文章
     *
     * @param newsArticleIds 需要删除的文章主键
     * @return 结果
     */
    @Override
    public int deleteNewsArticleByNewsArticleIds(Long[] newsArticleIds) {
        return newsArticleMapper.deleteNewsArticleByNewsArticleIds(newsArticleIds);
    }

    /**
     * 删除文章信息
     *
     * @param newsArticleId 文章主键
     * @return 结果
     */
    @Override
    public int deleteNewsArticleByNewsArticleId(Long newsArticleId) {
        return newsArticleMapper.deleteNewsArticleByNewsArticleId(newsArticleId);
    }
}
