package com.ruoyi.web.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.NewsTagMapper;
import com.ruoyi.web.domain.NewsTag;
import com.ruoyi.web.service.INewsTagService;

/**
 * 标签Service业务层处理
 * 
 * @author keloid
 * @date 2024-02-28
 */
@Service
public class NewsTagServiceImpl implements INewsTagService 
{
    @Autowired
    private NewsTagMapper newsTagMapper;

    /**
     * 查询标签
     * 
     * @param newsTagId 标签主键
     * @return 标签
     */
    @Override
    public NewsTag selectNewsTagByNewsTagId(Long newsTagId)
    {
        return newsTagMapper.selectNewsTagByNewsTagId(newsTagId);
    }

    /**
     * 查询标签列表
     * 
     * @param newsTag 标签
     * @return 标签
     */
    @Override
    public List<NewsTag> selectNewsTagList(NewsTag newsTag)
    {
        return newsTagMapper.selectNewsTagList(newsTag);
    }

    /**
     * 新增标签
     * 
     * @param newsTag 标签
     * @return 结果
     */
    @Override
    public int insertNewsTag(NewsTag newsTag)
    {
        newsTag.setCreateTime(DateUtils.getNowDate());
        return newsTagMapper.insertNewsTag(newsTag);
    }

    /**
     * 修改标签
     * 
     * @param newsTag 标签
     * @return 结果
     */
    @Override
    public int updateNewsTag(NewsTag newsTag)
    {
        return newsTagMapper.updateNewsTag(newsTag);
    }

    /**
     * 批量删除标签
     * 
     * @param newsTagIds 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteNewsTagByNewsTagIds(Long[] newsTagIds)
    {
        return newsTagMapper.deleteNewsTagByNewsTagIds(newsTagIds);
    }

    /**
     * 删除标签信息
     * 
     * @param newsTagId 标签主键
     * @return 结果
     */
    @Override
    public int deleteNewsTagByNewsTagId(Long newsTagId)
    {
        return newsTagMapper.deleteNewsTagByNewsTagId(newsTagId);
    }
}
