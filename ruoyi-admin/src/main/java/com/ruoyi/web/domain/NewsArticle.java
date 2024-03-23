package com.ruoyi.web.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章对象 news_article
 * 
 * @author keloid
 * @date 2024-02-28
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long newsArticleId;

    /** 文章的评论数 */
    @Excel(name = "文章的评论数")
    @JsonProperty("comment_count")
    private Integer commentCount;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String content;

    /** 文章节选 */
    @Excel(name = "文章节选")
    private String excerpt;

    /** 文章点赞数 */
    @Excel(name = "文章点赞数")
    @JsonProperty("favorite_count")
    private Integer favoriteCount;

    /** 下一篇文章数 */
    @Excel(name = "下一篇文章数")
    private Integer next;

    /** 上一篇文章数 */
    @Excel(name = "上一篇文章数")
    private Integer pre;

    /** 1：true，0：false。暂时还不知道干什么用 */
    @Excel(name = "1：true，0：false。暂时还不知道干什么用")
    private Integer preNext;

    /** 控制是否显示评论输入栏*/
    @JsonProperty(value = "switch_comment")
    @Excel(name = "控制是否显示评论输入栏")
    private Integer switchComment;

    /** 缩略图 */
    @Excel(name = "缩略图")
    private String thumbnail;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String title;

    /** 文章访问量 */
    @Excel(name = "文章访问量")
    private Integer views;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifiedTime;

    private Integer likes;

    /* tag列表*/
    private List<NewsTag> newsTagList;

    @JsonProperty(value = "like_list")
    /* 喜欢列表*/
    private List<NewsWxUser> likeList;

    @JsonProperty(value = "is_like")
    private Integer isLike;
    @JsonProperty(value = "is_favorite")
    private Integer isFavorite;


    public Integer getIsLike() {
        return isLike;
    }

    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
    }

    public Integer getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Integer isFavorite) {
        this.isFavorite = isFavorite;
    }

    public List<NewsWxUser> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<NewsWxUser> likeList) {
        this.likeList = likeList;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public List<NewsTag> getNewsTagList() {
        return newsTagList;
    }

    public void setNewsTagList(List<NewsTag> newsTagList) {
        this.newsTagList = newsTagList;
    }

    public void setNewsArticleId(Long newsArticleId)
    {
        this.newsArticleId = newsArticleId;
    }

    public Long getNewsArticleId() 
    {
        return newsArticleId;
    }
    public void setCommentCount(Integer commentCount) 
    {
        this.commentCount = commentCount;
    }

    public Integer getCommentCount() 
    {
        return commentCount;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setExcerpt(String excerpt) 
    {
        this.excerpt = excerpt;
    }

    public String getExcerpt() 
    {
        return excerpt;
    }
    public void setFavoriteCount(Integer favoriteCount) 
    {
        this.favoriteCount = favoriteCount;
    }

    public Integer getFavoriteCount() 
    {
        return favoriteCount;
    }
    public void setNext(Integer next) 
    {
        this.next = next;
    }

    public Integer getNext() 
    {
        return next;
    }
    public void setPre(Integer pre) 
    {
        this.pre = pre;
    }

    public Integer getPre() 
    {
        return pre;
    }
    public void setPreNext(Integer preNext) 
    {
        this.preNext = preNext;
    }

    public Integer getPreNext() 
    {
        return preNext;
    }
    public void setSwitchComment(Integer switchComment) 
    {
        this.switchComment = switchComment;
    }

    public Integer getSwitchComment() 
    {
        return switchComment;
    }
    public void setThumbnail(String thumbnail) 
    {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() 
    {
        return thumbnail;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setViews(Integer views) 
    {
        this.views = views;
    }

    public Integer getViews() 
    {
        return views;
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
            .append("newsArticleId", getNewsArticleId())
            .append("commentCount", getCommentCount())
            .append("content", getContent())
            .append("excerpt", getExcerpt())
            .append("favoriteCount", getFavoriteCount())
            .append("next", getNext())
            .append("pre", getPre())
            .append("preNext", getPreNext())
            .append("switchComment", getSwitchComment())
            .append("thumbnail", getThumbnail())
            .append("title", getTitle())
            .append("views", getViews())
            .append("createTime", getCreateTime())
            .append("modifiedTime", getModifiedTime())
            .toString();
    }
}
