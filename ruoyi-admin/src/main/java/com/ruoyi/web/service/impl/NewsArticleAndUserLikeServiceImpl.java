package com.ruoyi.web.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.NewsArticleAndUserLikeMapper;
import com.ruoyi.web.domain.NewsArticleAndUserLike;
import com.ruoyi.web.service.INewsArticleAndUserLikeService;

/**
 * 文章和用户点赞的关联Service业务层处理
 * 
 * @author keloid
 * @date 2024-03-05
 */
@Service
public class NewsArticleAndUserLikeServiceImpl implements INewsArticleAndUserLikeService 
{
    @Autowired
    private NewsArticleAndUserLikeMapper newsArticleAndUserLikeMapper;

    /**
     * 查询文章和用户点赞的关联
     * 
     * @param id 文章和用户点赞的关联主键
     * @return 文章和用户点赞的关联
     */
    @Override
    public NewsArticleAndUserLike selectNewsArticleAndUserLikeById(Long id)
    {
        return newsArticleAndUserLikeMapper.selectNewsArticleAndUserLikeById(id);
    }

    /**
     * 查询文章和用户点赞的关联列表
     * 
     * @param newsArticleAndUserLike 文章和用户点赞的关联
     * @return 文章和用户点赞的关联
     */
    @Override
    public List<NewsArticleAndUserLike> selectNewsArticleAndUserLikeList(NewsArticleAndUserLike newsArticleAndUserLike)
    {
        return newsArticleAndUserLikeMapper.selectNewsArticleAndUserLikeList(newsArticleAndUserLike);
    }

    /**
     * 新增文章和用户点赞的关联
     * 
     * @param newsArticleAndUserLike 文章和用户点赞的关联
     * @return 结果
     */
    @Override
    public int insertNewsArticleAndUserLike(NewsArticleAndUserLike newsArticleAndUserLike)
    {
        newsArticleAndUserLike.setCreateTime(DateUtils.getNowDate());
        return newsArticleAndUserLikeMapper.insertNewsArticleAndUserLike(newsArticleAndUserLike);
    }

    /**
     * 修改文章和用户点赞的关联
     * 
     * @param newsArticleAndUserLike 文章和用户点赞的关联
     * @return 结果
     */
    @Override
    public int updateNewsArticleAndUserLike(NewsArticleAndUserLike newsArticleAndUserLike)
    {
        return newsArticleAndUserLikeMapper.updateNewsArticleAndUserLike(newsArticleAndUserLike);
    }

    /**
     * 批量删除文章和用户点赞的关联
     * 
     * @param ids 需要删除的文章和用户点赞的关联主键
     * @return 结果
     */
    @Override
    public int deleteNewsArticleAndUserLikeByIds(Long[] ids)
    {
        return newsArticleAndUserLikeMapper.deleteNewsArticleAndUserLikeByIds(ids);
    }

    /**
     * 删除文章和用户点赞的关联信息
     * 
     * @param id 文章和用户点赞的关联主键
     * @return 结果
     */
    @Override
    public int deleteNewsArticleAndUserLikeById(Long id)
    {
        return newsArticleAndUserLikeMapper.deleteNewsArticleAndUserLikeById(id);
    }
}
