package com.ruoyi.web.service;

import java.util.List;
import com.ruoyi.web.domain.NewsArticleAndUserCollection;

/**
 * 用户收藏Service接口
 * 
 * @author keloid
 * @date 2024-03-05
 */
public interface INewsArticleAndUserCollectionService 
{
    /**
     * 查询用户收藏
     * 
     * @param id 用户收藏主键
     * @return 用户收藏
     */
    public NewsArticleAndUserCollection selectNewsArticleAndUserCollectionById(Long id);

    /**
     * 查询用户收藏列表
     * 
     * @param newsArticleAndUserCollection 用户收藏
     * @return 用户收藏集合
     */
    public List<NewsArticleAndUserCollection> selectNewsArticleAndUserCollectionList(NewsArticleAndUserCollection newsArticleAndUserCollection);

    /**
     * 新增用户收藏
     * 
     * @param newsArticleAndUserCollection 用户收藏
     * @return 结果
     */
    public int insertNewsArticleAndUserCollection(NewsArticleAndUserCollection newsArticleAndUserCollection);

    /**
     * 修改用户收藏
     * 
     * @param newsArticleAndUserCollection 用户收藏
     * @return 结果
     */
    public int updateNewsArticleAndUserCollection(NewsArticleAndUserCollection newsArticleAndUserCollection);

    /**
     * 批量删除用户收藏
     * 
     * @param ids 需要删除的用户收藏主键集合
     * @return 结果
     */
    public int deleteNewsArticleAndUserCollectionByIds(Long[] ids);

    /**
     * 删除用户收藏信息
     * 
     * @param id 用户收藏主键
     * @return 结果
     */
    public int deleteNewsArticleAndUserCollectionById(Long id);
}
