package com.ruoyi.web.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章类型对象 news_category
 * 
 * @author keloid
 * @date 2024-02-28
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long newsCategoryId;

    /** 分类的名字 */
    @Excel(name = "分类的名字")
    private String name;

    /** 分类描述 */
    @Excel(name = "分类描述")
    private String description;

    /** 分类的封面 */
    @Excel(name = "分类的封面")
    private String cover;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifiedTime;

    public void setNewsCategoryId(Long newsCategoryId) 
    {
        this.newsCategoryId = newsCategoryId;
    }

    public Long getNewsCategoryId() 
    {
        return newsCategoryId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setCover(String cover) 
    {
        this.cover = cover;
    }

    public String getCover() 
    {
        return cover;
    }
    public void setModifiedTime(Date modifiedTime) 
    {
        this.modifiedTime = modifiedTime;
    }

    public Date getModifiedTime() 
    {
        return modifiedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("newsCategoryId", getNewsCategoryId())
            .append("name", getName())
            .append("description", getDescription())
            .append("cover", getCover())
            .append("createTime", getCreateTime())
            .append("modifiedTime", getModifiedTime())
            .toString();
    }
}
