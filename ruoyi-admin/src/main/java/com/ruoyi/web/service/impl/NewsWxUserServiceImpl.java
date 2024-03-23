package com.ruoyi.web.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.web.mapper.NewsWxUserMapper;
import com.ruoyi.web.domain.NewsWxUser;
import com.ruoyi.web.service.INewsWxUserService;

/**
 * 微信用户Service业务层处理
 * 
 * @author keloid
 * @date 2024-02-28
 */
@Service
public class NewsWxUserServiceImpl implements INewsWxUserService 
{
    @Autowired
    private NewsWxUserMapper newsWxUserMapper;

    /**
     * 查询微信用户
     * 
     * @param newsWxUserId 微信用户主键
     * @return 微信用户
     */
    @Override
    public NewsWxUser selectNewsWxUserByNewsWxUserId(Long newsWxUserId)
    {
        return newsWxUserMapper.selectNewsWxUserByNewsWxUserId(newsWxUserId);
    }

    /**
     * 查询微信用户列表
     * 
     * @param newsWxUser 微信用户
     * @return 微信用户
     */
    @Override
    public List<NewsWxUser> selectNewsWxUserList(NewsWxUser newsWxUser)
    {
        return newsWxUserMapper.selectNewsWxUserList(newsWxUser);
    }



    /**
     * 新增微信用户
     * 
     * @param newsWxUser 微信用户
     * @return 结果
     */
    @Override
    public int insertNewsWxUser(NewsWxUser newsWxUser)
    {
        newsWxUser.setCreateTime(DateUtils.getNowDate());
        return newsWxUserMapper.insertNewsWxUser(newsWxUser);
    }

    /**
     * 修改微信用户
     * 
     * @param newsWxUser 微信用户
     * @return 结果
     */
    @Override
    public int updateNewsWxUser(NewsWxUser newsWxUser)
    {
        return newsWxUserMapper.updateNewsWxUser(newsWxUser);
    }

    /**
     * 批量删除微信用户
     * 
     * @param newsWxUserIds 需要删除的微信用户主键
     * @return 结果
     */
    @Override
    public int deleteNewsWxUserByNewsWxUserIds(Long[] newsWxUserIds)
    {
        return newsWxUserMapper.deleteNewsWxUserByNewsWxUserIds(newsWxUserIds);
    }

    /**
     * 删除微信用户信息
     * 
     * @param newsWxUserId 微信用户主键
     * @return 结果
     */
    @Override
    public int deleteNewsWxUserByNewsWxUserId(Long newsWxUserId)
    {
        return newsWxUserMapper.deleteNewsWxUserByNewsWxUserId(newsWxUserId);
    }
}
