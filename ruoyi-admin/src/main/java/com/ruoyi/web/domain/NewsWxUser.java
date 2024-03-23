package com.ruoyi.web.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 微信用户对象 news_wx_user
 * 
 * @author keloid
 * @date 2024-02-28
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsWxUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long newsWxUserId;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String nickname;

    /** 微信的openid */
    @Excel(name = "微信的openid")
    private String openid;

    /**  电话号 */
    @Excel(name = " 电话号")
    private String phoneNumber;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String avatar;

    private String code;


    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifiedTime;

    public void setNewsWxUserId(Long newsWxUserId) 
    {
        this.newsWxUserId = newsWxUserId;
    }

    public Long getNewsWxUserId() 
    {
        return newsWxUserId;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setModifiedTime(Date modifiedTime) 
    {
        this.modifiedTime = modifiedTime;
    }

    public Date getModifiedTime() 
    {
        return modifiedTime;
    }


    public NewsWxUser(String token) {
        this.token = token;
    }

    public NewsWxUser() {

    }



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("newsWxUserId", getNewsWxUserId())
            .append("nickname", getNickname())
            .append("openid", getOpenid())
            .append("phoneNumber", getPhoneNumber())
            .append("avatar", getAvatar())
            .append("createTime", getCreateTime())
            .append("modifiedTime", getModifiedTime())
            .toString();
    }
}
