package com.ruoyi.web.mapper;

import java.util.List;
import com.ruoyi.web.domain.NewsTag;

/**
 * 标签Mapper接口
 * 
 * @author keloid
 * @date 2024-02-28
 */
public interface NewsTagMapper 
{
    /**
     * 查询标签
     * 
     * @param newsTagId 标签主键
     * @return 标签
     */
    public NewsTag selectNewsTagByNewsTagId(Long newsTagId);

    /**
     * 查询标签列表
     * 
     * @param newsTag 标签
     * @return 标签集合
     */
    public List<NewsTag> selectNewsTagList(NewsTag newsTag);

    /**
     * 新增标签
     * 
     * @param newsTag 标签
     * @return 结果
     */
    public int insertNewsTag(NewsTag newsTag);

    /**
     * 修改标签
     * 
     * @param newsTag 标签
     * @return 结果
     */
    public int updateNewsTag(NewsTag newsTag);

    /**
     * 删除标签
     * 
     * @param newsTagId 标签主键
     * @return 结果
     */
    public int deleteNewsTagByNewsTagId(Long newsTagId);

    /**
     * 批量删除标签
     * 
     * @param newsTagIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsTagByNewsTagIds(Long[] newsTagIds);
}
