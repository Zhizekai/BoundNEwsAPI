package com.ruoyi.web.service;

import java.util.List;
import com.ruoyi.web.domain.NewsArticleAndUserLike;

/**
 * 文章和用户点赞的关联Service接口
 * 
 * @author keloid
 * @date 2024-03-05
 */
public interface INewsArticleAndUserLikeService 
{
    /**
     * 查询文章和用户点赞的关联
     * 
     * @param id 文章和用户点赞的关联主键
     * @return 文章和用户点赞的关联
     */
    public NewsArticleAndUserLike selectNewsArticleAndUserLikeById(Long id);

    /**
     * 查询文章和用户点赞的关联列表
     * 
     * @param newsArticleAndUserLike 文章和用户点赞的关联
     * @return 文章和用户点赞的关联集合
     */
    public List<NewsArticleAndUserLike> selectNewsArticleAndUserLikeList(NewsArticleAndUserLike newsArticleAndUserLike);

    /**
     * 新增文章和用户点赞的关联
     * 
     * @param newsArticleAndUserLike 文章和用户点赞的关联
     * @return 结果
     */
    public int insertNewsArticleAndUserLike(NewsArticleAndUserLike newsArticleAndUserLike);

    /**
     * 修改文章和用户点赞的关联
     * 
     * @param newsArticleAndUserLike 文章和用户点赞的关联
     * @return 结果
     */
    public int updateNewsArticleAndUserLike(NewsArticleAndUserLike newsArticleAndUserLike);

    /**
     * 批量删除文章和用户点赞的关联
     * 
     * @param ids 需要删除的文章和用户点赞的关联主键集合
     * @return 结果
     */
    public int deleteNewsArticleAndUserLikeByIds(Long[] ids);

    /**
     * 删除文章和用户点赞的关联信息
     * 
     * @param id 文章和用户点赞的关联主键
     * @return 结果
     */
    public int deleteNewsArticleAndUserLikeById(Long id);
}
