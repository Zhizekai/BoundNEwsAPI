package com.ruoyi.web.controller.wxApi;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.WXApiBaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.WXApiTableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.tool.CreateRandomStr;
import com.ruoyi.web.domain.NewsArticle;
import com.ruoyi.web.domain.NewsArticleAndUserCollection;
import com.ruoyi.web.domain.NewsArticleAndUserLike;
import com.ruoyi.web.domain.NewsWxUser;
import com.ruoyi.web.service.INewsArticleAndUserCollectionService;
import com.ruoyi.web.service.INewsArticleAndUserLikeService;
import com.ruoyi.web.service.INewsArticleService;
import com.ruoyi.web.service.INewsWxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 微信用户Controller
 *
 * @author keloid
 * @date 2024-02-11
 */
@Api(tags = "小程序用户登录模块")
@RestController
@RequestMapping("/v1/wx")
public class NewsWxUserWXController extends WXApiBaseController {
    @Autowired
    private INewsWxUserService newsWxUserService;


    @Autowired
    private INewsArticleService newsArticleService;

    // 用户点赞表
    @Autowired
    private INewsArticleAndUserLikeService uLikeService;

    // 用户收藏表
    @Autowired
    private INewsArticleAndUserCollectionService uCollectionService;


    // 发送请求
    @Autowired
    private RestTemplate restTemplate;

    @Value("${wx.appId}")
    private String AppId;
    @Value("${wx.appSecret}")
    private String AppSecret;

    /* 小程序微信用户登录*/
    @ApiOperation("小程序用户登录")
    @GetMapping("/user/login3")
    public AjaxResult wxLogin(@RequestParam("code") String code) throws Exception {

        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + AppId +
                "&secret=" + AppSecret +
                "&js_code=" + code +
                "&grant_type=authorization_code";
        //利用spring原生http请求工具对接口进行请求
        String jsonData = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        //请求返回的是Json类型的数据 所以我们需要用到fastjson
        //这个判断是判断我们的请求是否出错，如果没有出错的话就能够拿到openid
        if (StringUtils.contains(jsonData, "errcode")) {
            return error();
        }
        String openid = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");
        String unionid = jsonObject.getString("unionid");

        // 存储openid，如果已经有openid就直接返回用户信息，如果没有openid就创建新的用户id并且返回默认的用户信息
        NewsWxUser newsWxUser = new NewsWxUser();
        newsWxUser.setOpenid(openid);
        List<NewsWxUser> newsWxUsers = newsWxUserService.selectNewsWxUserList(newsWxUser);
//
//        System.out.println(openid);
//        System.out.println(sessionKey);
//        System.out.println(unionid);

        if (newsWxUsers.size() == 0) {
            Date date = new Date();
            System.out.println(date);
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            newsWxUser.setNickname("新的微信用户" + ft.format(date));
            newsWxUser.setAvatar("http://financeadmin.hogtiedhub.cn/wp-content/plugins/jiangqie-api/public/images/default_avatar.jpg");
            newsWxUser.setToken(CreateRandomStr.createRandomStr3(12));  // 生成随机字符串
            int i = newsWxUserService.insertNewsWxUser(newsWxUser);
            if (i == 1) {
                return success(newsWxUser);
            }
        } else {
            newsWxUser = newsWxUsers.get(0);
            return success(newsWxUser);
        }
        return error("登录错误");
    }

    /* 获取微信用户收藏文章列表*/
    @ApiOperation("微信用户收藏和点赞文章列表")
    @GetMapping("/posts/my")
    public WXApiTableDataInfo getMyArticleList(@RequestParam String track, @RequestParam String token) {
        startPage();

        List<NewsArticle> list = new ArrayList<NewsArticle>();
        if (Objects.equals(track, "favorites")) {
            list = newsArticleService.selectWXNewsArticleCollectionList(token);
        }

        if (Objects.equals(track, "likes")) {
            list = newsArticleService.selectWXNewsArticleLikesList(token);
        }

        return getDataTable(list);
    }


    /* 用户点赞文章*/
    @ApiOperation("微信用户点赞文章")
    @GetMapping("/user/like")
    public AjaxResult userLikeArticle(@RequestParam Long post_id, @RequestParam String token) {

        // 获取用户id
        NewsWxUser newsWxUser = new NewsWxUser();
        newsWxUser.setToken(token);
        List<NewsWxUser> newsWxUsers = newsWxUserService.selectNewsWxUserList(newsWxUser);
        Long userId = newsWxUsers.get(0).getNewsWxUserId();


        NewsArticleAndUserLike newsArticleAndUserLike = new NewsArticleAndUserLike();
        newsArticleAndUserLike.setArticleId(post_id);
        newsArticleAndUserLike.setUserId(userId);

        List<NewsArticleAndUserLike> newsArticleAndUserLikes =
                uLikeService.selectNewsArticleAndUserLikeList(newsArticleAndUserLike);
        // 如果点赞过就取消点赞
        if (newsArticleAndUserLikes.size() == 1) {
            NewsArticleAndUserLike item = newsArticleAndUserLikes.get(0);
            int i = uLikeService.deleteNewsArticleAndUserLikeById(item.getId());
            if (i == 1) {
                return success("取消点赞");
            }
        } else {
            // 如果没点赞过关联表插入
            int i = uLikeService.insertNewsArticleAndUserLike(newsArticleAndUserLike);
            if (i == 1) {
                return success("点赞成功");
            }
        }

        return error();
    }


    /* 用户收藏文章*/
    @ApiOperation("微信用户收藏文章")
    @GetMapping("/user/favorite")
    public AjaxResult userFavoriteArticle(@RequestParam Long post_id, @RequestParam String token) {
        // 获取用户id
        NewsWxUser newsWxUser = new NewsWxUser();
        newsWxUser.setToken(token);
        List<NewsWxUser> newsWxUsers = newsWxUserService.selectNewsWxUserList(newsWxUser);
        Long userId = newsWxUsers.get(0).getNewsWxUserId();

        NewsArticleAndUserCollection newsArticleAndUserCollection = new NewsArticleAndUserCollection();
        newsArticleAndUserCollection.setArticleId(post_id);
        newsArticleAndUserCollection.setWxUserId(userId);



        List<NewsArticleAndUserCollection> newsArticleAndUserCollections =
                uCollectionService.selectNewsArticleAndUserCollectionList(newsArticleAndUserCollection);

        // 如果收藏过就取消收藏
        if (newsArticleAndUserCollections.size() == 1) {
            NewsArticleAndUserCollection item = newsArticleAndUserCollections.get(0);
            if (uCollectionService.deleteNewsArticleAndUserCollectionById(item.getId()) == 1) {
                return success("取消收藏");
            }
        }else {
            // 如果没有收藏过就收藏
            if (uCollectionService.insertNewsArticleAndUserCollection(newsArticleAndUserCollection) == 1) {
                return success("收藏成功");
            }
        }

        return error();
    }

    /**
     * 查询微信用户列表
     */
    @GetMapping("/user/list")
    public WXApiTableDataInfo list(NewsWxUser newsWxUser) {
        startPage();
        List<NewsWxUser> list = newsWxUserService.selectNewsWxUserList(newsWxUser);
        return getDataTable(list);
    }

    /**
     * 导出微信用户列表
     */
    @PreAuthorize("@ss.hasPermi('web:user:export')")
    @Log(title = "微信用户", businessType = BusinessType.EXPORT)
    @PostMapping("/user/export")
    public void export(HttpServletResponse response, NewsWxUser newsWxUser) {
        List<NewsWxUser> list = newsWxUserService.selectNewsWxUserList(newsWxUser);
        ExcelUtil<NewsWxUser> util = new ExcelUtil<NewsWxUser>(NewsWxUser.class);
        util.exportExcel(response, list, "微信用户数据");
    }

    /**
     * 获取微信用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:user:query')")
    @GetMapping(value = "/user/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(newsWxUserService.selectNewsWxUserByNewsWxUserId(id));
    }

    /**
     * 新增微信用户
     */
    @PreAuthorize("@ss.hasPermi('web:user:add')")
    @Log(title = "微信用户", businessType = BusinessType.INSERT)
    @PostMapping("/user/change")
    public AjaxResult add(@RequestBody NewsWxUser newsWxUser) {
        return toAjax(newsWxUserService.insertNewsWxUser(newsWxUser));
    }

    /**
     * 修改微信用户
     */
    @PreAuthorize("@ss.hasPermi('web:user:edit')")
    @Log(title = "微信用户", businessType = BusinessType.UPDATE)
    @PutMapping("/user/put")
    public AjaxResult edit(@RequestBody NewsWxUser newsWxUser) {
        return toAjax(newsWxUserService.updateNewsWxUser(newsWxUser));
    }

    /**
     * 删除微信用户
     */
    @PreAuthorize("@ss.hasPermi('web:user:remove')")
    @Log(title = "微信用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/user/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(newsWxUserService.deleteNewsWxUserByNewsWxUserIds(ids));
    }
}
