package com.ruoyi.web.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 关联表对象 news_article_and_comment
 * 
 * @author keloid
 * @date 2024-03-06
 */
public class NewsArticleAndComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关联表id */
    private Long articleAndCommentId;

    /** 文章表id */
    @Excel(name = "文章表id")
    private Long articleId;

    /** 评论表id */
    @Excel(name = "评论表id")
    private Long commentId;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifiedTime;

    public void setArticleAndCommentId(Long articleAndCommentId) 
    {
        this.articleAndCommentId = articleAndCommentId;
    }

    public Long getArticleAndCommentId() 
    {
        return articleAndCommentId;
    }
    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
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
            .append("articleAndCommentId", getArticleAndCommentId())
            .append("articleId", getArticleId())
            .append("commentId", getCommentId())
            .append("createTime", getCreateTime())
            .append("modifiedTime", getModifiedTime())
            .toString();
    }
}
