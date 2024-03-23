package com.ruoyi.web.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章评论对象 news_comment
 * 
 * @author keloid
 * @date 2024-02-28
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long newsCommentId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 评论是否允许显示。1：显示，0：不显示 */
    @Excel(name = "评论是否允许显示。1：显示，0：不显示")
    private Integer approved;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifiedTime;

    @JsonProperty(value = "parent_id")
    private Long parentId;

    // 前端要穿的文章id
    private Long newsArticleId;

    @JsonIgnore
    private String token;

    @JsonProperty("user")
    private NewsWxUser newsWxUser;


    public NewsWxUser getNewsWxUser() {
        return newsWxUser;
    }

    public void setNewsWxUser(NewsWxUser newsWxUser) {
        this.newsWxUser = newsWxUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getNewsArticleId() {
        return newsArticleId;
    }

    public void setNewsArticleId(Long newsArticleId) {
        this.newsArticleId = newsArticleId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setNewsCommentId(Long newsCommentId)
    {
        this.newsCommentId = newsCommentId;
    }

    public Long getNewsCommentId() 
    {
        return newsCommentId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setApproved(Integer approved) 
    {
        this.approved = approved;
    }

    public Integer getApproved() 
    {
        return approved;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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
            .append("newsCommentId", getNewsCommentId())
            .append("content", getContent())
            .append("approved", getApproved())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("modifiedTime", getModifiedTime())
            .toString();
    }
}
