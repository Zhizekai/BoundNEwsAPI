package com.ruoyi.web.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.NewsCommentMapper;
import com.ruoyi.web.domain.NewsComment;
import com.ruoyi.web.service.INewsCommentService;

/**
 * 文章评论Service业务层处理
 *
 * @author keloid
 * @date 2024-02-28
 */
@Service
public class NewsCommentServiceImpl implements INewsCommentService {
    @Autowired
    private NewsCommentMapper newsCommentMapper;

    /**
     * 查询文章评论
     *
     * @param newsCommentId 文章评论主键
     * @return 文章评论
     */
    @Override
    public NewsComment selectNewsCommentByNewsCommentId(Long newsCommentId) {
        return newsCommentMapper.selectNewsCommentByNewsCommentId(newsCommentId);
    }

    /**
     * 查询文章评论列表
     *
     * @param newsComment 文章评论
     * @return 文章评论
     */
    @Override
    public List<NewsComment> selectNewsCommentList(NewsComment newsComment) {
        return newsCommentMapper.selectNewsCommentList(newsComment);
    }

    @Override
    public List<NewsComment> selectCommentListByArticleId(Long newsArticleId) {
        return newsCommentMapper.selectCommentListByArticleId(newsArticleId);
    }

    /**
     * 新增文章评论
     *
     * @param newsComment 文章评论
     * @return 结果
     */
    @Override
    public int insertNewsComment(NewsComment newsComment) {
        newsComment.setCreateTime(DateUtils.getNowDate());
        return newsCommentMapper.insertNewsComment(newsComment);
    }

    /* 微信添加评论*/
    @Override
    public int insertWXNewsComment(NewsComment newsComment) {
        newsComment.setCreateTime(DateUtils.getNowDate());
        return newsCommentMapper.insertWXNewsComment(newsComment);
    }


    /**
     * 修改文章评论
     *
     * @param newsComment 文章评论
     * @return 结果
     */
    @Override
    public int updateNewsComment(NewsComment newsComment) {
        return newsCommentMapper.updateNewsComment(newsComment);
    }

    /**
     * 批量删除文章评论
     *
     * @param newsCommentIds 需要删除的文章评论主键
     * @return 结果
     */
    @Override
    public int deleteNewsCommentByNewsCommentIds(Long[] newsCommentIds) {
        return newsCommentMapper.deleteNewsCommentByNewsCommentIds(newsCommentIds);
    }

    /**
     * 删除文章评论信息
     *
     * @param newsCommentId 文章评论主键
     * @return 结果
     */
    @Override
    public int deleteNewsCommentByNewsCommentId(Long newsCommentId) {
        return newsCommentMapper.deleteNewsCommentByNewsCommentId(newsCommentId);
    }
}
