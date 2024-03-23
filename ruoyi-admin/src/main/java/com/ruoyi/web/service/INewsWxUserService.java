package com.ruoyi.web.service;

import java.util.List;
import com.ruoyi.web.domain.NewsWxUser;

/**
 * 微信用户Service接口
 * 
 * @author keloid
 * @date 2024-02-28
 */
public interface INewsWxUserService 
{
    /**
     * 查询微信用户
     * 
     * @param newsWxUserId 微信用户主键
     * @return 微信用户
     */
    public NewsWxUser selectNewsWxUserByNewsWxUserId(Long newsWxUserId);

    /**
     * 查询微信用户列表
     * 
     * @param newsWxUser 微信用户
     * @return 微信用户集合
     */
    public List<NewsWxUser> selectNewsWxUserList(NewsWxUser newsWxUser);

    /**
     * 新增微信用户
     * 
     * @param newsWxUser 微信用户
     * @return 结果
     */
    public int insertNewsWxUser(NewsWxUser newsWxUser);

    /**
     * 修改微信用户
     * 
     * @param newsWxUser 微信用户
     * @return 结果
     */
    public int updateNewsWxUser(NewsWxUser newsWxUser);

    /**
     * 批量删除微信用户
     * 
     * @param newsWxUserIds 需要删除的微信用户主键集合
     * @return 结果
     */
    public int deleteNewsWxUserByNewsWxUserIds(Long[] newsWxUserIds);

    /**
     * 删除微信用户信息
     * 
     * @param newsWxUserId 微信用户主键
     * @return 结果
     */
    public int deleteNewsWxUserByNewsWxUserId(Long newsWxUserId);
}
