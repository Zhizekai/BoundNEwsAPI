package com.ruoyi.web.mapper;

import java.util.List;
import com.ruoyi.web.domain.NewsCategory;

/**
 * 文章类型Mapper接口
 * 
 * @author keloid
 * @date 2024-02-28
 */
public interface NewsCategoryMapper 
{
    /**
     * 查询文章类型
     * 
     * @param newsCategoryId 文章类型主键
     * @return 文章类型
     */
    public NewsCategory selectNewsCategoryByNewsCategoryId(Long newsCategoryId);

    /**
     * 查询文章类型列表
     * 
     * @param newsCategory 文章类型
     * @return 文章类型集合
     */
    public List<NewsCategory> selectNewsCategoryList(NewsCategory newsCategory);

    /**
     * 新增文章类型
     * 
     * @param newsCategory 文章类型
     * @return 结果
     */
    public int insertNewsCategory(NewsCategory newsCategory);

    /**
     * 修改文章类型
     * 
     * @param newsCategory 文章类型
     * @return 结果
     */
    public int updateNewsCategory(NewsCategory newsCategory);

    /**
     * 删除文章类型
     * 
     * @param newsCategoryId 文章类型主键
     * @return 结果
     */
    public int deleteNewsCategoryByNewsCategoryId(Long newsCategoryId);

    /**
     * 批量删除文章类型
     * 
     * @param newsCategoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsCategoryByNewsCategoryIds(Long[] newsCategoryIds);
}
