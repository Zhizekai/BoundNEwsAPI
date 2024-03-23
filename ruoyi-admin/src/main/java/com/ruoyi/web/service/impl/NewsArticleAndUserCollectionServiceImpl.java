package com.ruoyi.web.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.NewsArticleAndUserCollectionMapper;
import com.ruoyi.web.domain.NewsArticleAndUserCollection;
import com.ruoyi.web.service.INewsArticleAndUserCollectionService;

/**
 * 用户收藏Service业务层处理
 * 
 * @author keloid
 * @date 2024-03-05
 */
@Service
public class NewsArticleAndUserCollectionServiceImpl implements INewsArticleAndUserCollectionService 
{
    @Autowired
    private NewsArticleAndUserCollectionMapper newsArticleAndUserCollectionMapper;

    /**
     * 查询用户收藏
     * 
     * @param id 用户收藏主键
     * @return 用户收藏
     */
    @Override
    public NewsArticleAndUserCollection selectNewsArticleAndUserCollectionById(Long id)
    {
        return newsArticleAndUserCollectionMapper.selectNewsArticleAndUserCollectionById(id);
    }

    /**
     * 查询用户收藏列表
     * 
     * @param newsArticleAndUserCollection 用户收藏
     * @return 用户收藏
     */
    @Override
    public List<NewsArticleAndUserCollection> selectNewsArticleAndUserCollectionList(NewsArticleAndUserCollection newsArticleAndUserCollection)
    {
        return newsArticleAndUserCollectionMapper.selectNewsArticleAndUserCollectionList(newsArticleAndUserCollection);
    }

    /**
     * 新增用户收藏
     * 
     * @param newsArticleAndUserCollection 用户收藏
     * @return 结果
     */
    @Override
    public int insertNewsArticleAndUserCollection(NewsArticleAndUserCollection newsArticleAndUserCollection)
    {
        newsArticleAndUserCollection.setCreateTime(DateUtils.getNowDate());
        return newsArticleAndUserCollectionMapper.insertNewsArticleAndUserCollection(newsArticleAndUserCollection);
    }

    /**
     * 修改用户收藏
     * 
     * @param newsArticleAndUserCollection 用户收藏
     * @return 结果
     */
    @Override
    public int updateNewsArticleAndUserCollection(NewsArticleAndUserCollection newsArticleAndUserCollection)
    {
        return newsArticleAndUserCollectionMapper.updateNewsArticleAndUserCollection(newsArticleAndUserCollection);
    }

    /**
     * 批量删除用户收藏
     * 
     * @param ids 需要删除的用户收藏主键
     * @return 结果
     */
    @Override
    public int deleteNewsArticleAndUserCollectionByIds(Long[] ids)
    {
        return newsArticleAndUserCollectionMapper.deleteNewsArticleAndUserCollectionByIds(ids);
    }

    /**
     * 删除用户收藏信息
     * 
     * @param id 用户收藏主键
     * @return 结果
     */
    @Override
    public int deleteNewsArticleAndUserCollectionById(Long id)
    {
        return newsArticleAndUserCollectionMapper.deleteNewsArticleAndUserCollectionById(id);
    }
}
