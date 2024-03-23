package com.ruoyi.web.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.NewsCategoryMapper;
import com.ruoyi.web.domain.NewsCategory;
import com.ruoyi.web.service.INewsCategoryService;

/**
 * 文章类型Service业务层处理
 * 
 * @author keloid
 * @date 2024-02-28
 */
@Service
public class NewsCategoryServiceImpl implements INewsCategoryService 
{
    @Autowired
    private NewsCategoryMapper newsCategoryMapper;

    /**
     * 查询文章类型
     * 
     * @param newsCategoryId 文章类型主键
     * @return 文章类型
     */
    @Override
    public NewsCategory selectNewsCategoryByNewsCategoryId(Long newsCategoryId)
    {
        return newsCategoryMapper.selectNewsCategoryByNewsCategoryId(newsCategoryId);
    }

    /**
     * 查询文章类型列表
     * 
     * @param newsCategory 文章类型
     * @return 文章类型
     */
    @Override
    public List<NewsCategory> selectNewsCategoryList(NewsCategory newsCategory)
    {
        return newsCategoryMapper.selectNewsCategoryList(newsCategory);
    }

    /**
     * 新增文章类型
     * 
     * @param newsCategory 文章类型
     * @return 结果
     */
    @Override
    public int insertNewsCategory(NewsCategory newsCategory)
    {
        newsCategory.setCreateTime(DateUtils.getNowDate());
        return newsCategoryMapper.insertNewsCategory(newsCategory);
    }

    /**
     * 修改文章类型
     * 
     * @param newsCategory 文章类型
     * @return 结果
     */
    @Override
    public int updateNewsCategory(NewsCategory newsCategory)
    {
        return newsCategoryMapper.updateNewsCategory(newsCategory);
    }

    /**
     * 批量删除文章类型
     * 
     * @param newsCategoryIds 需要删除的文章类型主键
     * @return 结果
     */
    @Override
    public int deleteNewsCategoryByNewsCategoryIds(Long[] newsCategoryIds)
    {
        return newsCategoryMapper.deleteNewsCategoryByNewsCategoryIds(newsCategoryIds);
    }

    /**
     * 删除文章类型信息
     * 
     * @param newsCategoryId 文章类型主键
     * @return 结果
     */
    @Override
    public int deleteNewsCategoryByNewsCategoryId(Long newsCategoryId)
    {
        return newsCategoryMapper.deleteNewsCategoryByNewsCategoryId(newsCategoryId);
    }
}
