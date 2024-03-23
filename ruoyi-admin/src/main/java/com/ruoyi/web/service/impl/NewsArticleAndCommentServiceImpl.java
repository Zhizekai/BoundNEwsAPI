package com.ruoyi.web.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.NewsArticleAndCommentMapper;
import com.ruoyi.web.domain.NewsArticleAndComment;
import com.ruoyi.web.service.INewsArticleAndCommentService;

/**
 * 关联表Service业务层处理
 * 
 * @author keloid
 * @date 2024-03-06
 */
@Service
public class NewsArticleAndCommentServiceImpl implements INewsArticleAndCommentService 
{
    @Autowired
    private NewsArticleAndCommentMapper newsArticleAndCommentMapper;

    /**
     * 查询关联表
     * 
     * @param articleAndCommentId 关联表主键
     * @return 关联表
     */
    @Override
    public NewsArticleAndComment selectNewsArticleAndCommentByArticleAndCommentId(Long articleAndCommentId)
    {
        return newsArticleAndCommentMapper.selectNewsArticleAndCommentByArticleAndCommentId(articleAndCommentId);
    }

    /**
     * 查询关联表列表
     * 
     * @param newsArticleAndComment 关联表
     * @return 关联表
     */
    @Override
    public List<NewsArticleAndComment> selectNewsArticleAndCommentList(NewsArticleAndComment newsArticleAndComment)
    {
        return newsArticleAndCommentMapper.selectNewsArticleAndCommentList(newsArticleAndComment);
    }

    /**
     * 新增关联表
     * 
     * @param newsArticleAndComment 关联表
     * @return 结果
     */
    @Override
    public int insertNewsArticleAndComment(NewsArticleAndComment newsArticleAndComment)
    {
        newsArticleAndComment.setCreateTime(DateUtils.getNowDate());
        return newsArticleAndCommentMapper.insertNewsArticleAndComment(newsArticleAndComment);
    }

    /**
     * 修改关联表
     * 
     * @param newsArticleAndComment 关联表
     * @return 结果
     */
    @Override
    public int updateNewsArticleAndComment(NewsArticleAndComment newsArticleAndComment)
    {
        return newsArticleAndCommentMapper.updateNewsArticleAndComment(newsArticleAndComment);
    }

    /**
     * 批量删除关联表
     * 
     * @param articleAndCommentIds 需要删除的关联表主键
     * @return 结果
     */
    @Override
    public int deleteNewsArticleAndCommentByArticleAndCommentIds(Long[] articleAndCommentIds)
    {
        return newsArticleAndCommentMapper.deleteNewsArticleAndCommentByArticleAndCommentIds(articleAndCommentIds);
    }

    /**
     * 删除关联表信息
     * 
     * @param articleAndCommentId 关联表主键
     * @return 结果
     */
    @Override
    public int deleteNewsArticleAndCommentByArticleAndCommentId(Long articleAndCommentId)
    {
        return newsArticleAndCommentMapper.deleteNewsArticleAndCommentByArticleAndCommentId(articleAndCommentId);
    }
}
