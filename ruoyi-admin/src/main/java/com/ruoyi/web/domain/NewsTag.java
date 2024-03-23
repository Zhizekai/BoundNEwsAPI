package com.ruoyi.web.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 标签对象 news_tag
 * 
 * @author keloid
 * @date 2024-02-28
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long newsTagId;

    /** 标签名 */
    @Excel(name = "标签名")
    private String tagName;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifiedTime;

    public void setNewsTagId(Long newsTagId) 
    {
        this.newsTagId = newsTagId;
    }

    public Long getNewsTagId() 
    {
        return newsTagId;
    }
    public void setTagName(String tagName) 
    {
        this.tagName = tagName;
    }

    public String getTagName() 
    {
        return tagName;
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
            .append("newsTagId", getNewsTagId())
            .append("tagName", getTagName())
            .append("createTime", getCreateTime())
            .append("modifiedTime", getModifiedTime())
            .toString();
    }
}
