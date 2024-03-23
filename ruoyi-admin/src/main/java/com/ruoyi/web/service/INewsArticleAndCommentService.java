package com.ruoyi.web.service;

import java.util.List;
import com.ruoyi.web.domain.NewsArticleAndComment;

/**
 * 关联表Service接口
 * 
 * @author keloid
 * @date 2024-03-06
 */
public interface INewsArticleAndCommentService 
{
    /**
     * 查询关联表
     * 
     * @param articleAndCommentId 关联表主键
     * @return 关联表
     */
    public NewsArticleAndComment selectNewsArticleAndCommentByArticleAndCommentId(Long articleAndCommentId);

    /**
     * 查询关联表列表
     * 
     * @param newsArticleAndComment 关联表
     * @return 关联表集合
     */
    public List<NewsArticleAndComment> selectNewsArticleAndCommentList(NewsArticleAndComment newsArticleAndComment);

    /**
     * 新增关联表
     * 
     * @param newsArticleAndComment 关联表
     * @return 结果
     */
    public int insertNewsArticleAndComment(NewsArticleAndComment newsArticleAndComment);

    /**
     * 修改关联表
     * 
     * @param newsArticleAndComment 关联表
     * @return 结果
     */
    public int updateNewsArticleAndComment(NewsArticleAndComment newsArticleAndComment);

    /**
     * 批量删除关联表
     * 
     * @param articleAndCommentIds 需要删除的关联表主键集合
     * @return 结果
     */
    public int deleteNewsArticleAndCommentByArticleAndCommentIds(Long[] articleAndCommentIds);

    /**
     * 删除关联表信息
     * 
     * @param articleAndCommentId 关联表主键
     * @return 结果
     */
    public int deleteNewsArticleAndCommentByArticleAndCommentId(Long articleAndCommentId);
}
