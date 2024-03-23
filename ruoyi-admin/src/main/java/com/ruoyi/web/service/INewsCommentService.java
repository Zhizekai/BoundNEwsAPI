package com.ruoyi.web.service;

import java.util.List;
import com.ruoyi.web.domain.NewsComment;

/**
 * 文章评论Service接口
 * 
 * @author keloid
 * @date 2024-02-28
 */
public interface INewsCommentService 
{
    /**
     * 查询文章评论
     * 
     * @param newsCommentId 文章评论主键
     * @return 文章评论
     */
    public NewsComment selectNewsCommentByNewsCommentId(Long newsCommentId);

    /**
     * 查询文章评论列表
     * 
     * @param newsComment 文章评论
     * @return 文章评论集合
     */
    public List<NewsComment> selectNewsCommentList(NewsComment newsComment);

    public List<NewsComment> selectCommentListByArticleId(Long newsArticleId);

    /**
     * 新增文章评论
     * 
     * @param newsComment 文章评论
     * @return 结果
     */
    public int insertNewsComment(NewsComment newsComment);

    /* 微信小程序添加评论*/
    public int insertWXNewsComment(NewsComment newsComment);

    /**
     * 修改文章评论
     * 
     * @param newsComment 文章评论
     * @return 结果
     */
    public int updateNewsComment(NewsComment newsComment);

    /**
     * 批量删除文章评论
     * 
     * @param newsCommentIds 需要删除的文章评论主键集合
     * @return 结果
     */
    public int deleteNewsCommentByNewsCommentIds(Long[] newsCommentIds);

    /**
     * 删除文章评论信息
     * 
     * @param newsCommentId 文章评论主键
     * @return 结果
     */
    public int deleteNewsCommentByNewsCommentId(Long newsCommentId);
}
