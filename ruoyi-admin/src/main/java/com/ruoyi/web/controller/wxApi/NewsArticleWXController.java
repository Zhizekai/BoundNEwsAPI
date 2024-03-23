package com.ruoyi.web.controller.wxApi;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.controller.WXApiBaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.WXApiTableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.domain.*;
import com.ruoyi.web.service.INewsArticleAndUserCollectionService;
import com.ruoyi.web.service.INewsArticleAndUserLikeService;
import com.ruoyi.web.service.INewsArticleService;
import com.ruoyi.web.service.INewsWxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 文章Controller
 *
 * @author ruoyi
 * @date 2024-02-11
 */
@Api(tags = "小程序文章管理", value = "文章管理模块")
@RestController
@RequestMapping("/v1/wx")
public class NewsArticleWXController extends WXApiBaseController {
    @Autowired
    private INewsArticleService newsArticleService;

    @Autowired
    private INewsArticleAndUserLikeService newsArticleAndUserLikeService;

    @Autowired
    private INewsArticleAndUserCollectionService newsArticleAndUserCollectionService;

    @Autowired
    private INewsWxUserService newsWxUserService;


    /* 获取某一种类下的文章列表 cat_id offset */
    @ApiOperation("获取某一种类下的文章列表")
    @GetMapping("/posts/category")
    public WXApiTableDataInfo getArticleListByCategoryId(Long cat_id) {
        startPage();
        List<NewsArticle> list = newsArticleService.selectNewsArticleListByCategoryId(cat_id);

        return getDataTable(list);
    }

    /*获取某个tag下的文章*/
    @ApiOperation("获取某一个tag底下的文章列表")
    @GetMapping("/posts/tag")
    public WXApiTableDataInfo getArticleByTagId(@RequestParam(required = false) Long newsTagId) {
        startPage();
        List<NewsArticle> list = newsArticleService.selectNewsArticleListByTagId(newsTagId);
        return getDataTable(list);
    }

    /* 获取微信最新文章列表*/
    @ApiOperation("获取最新文章列表")
//    @PreAuthorize("@ss.hasPermi('web:article:list')")
    @GetMapping("/posts/last")
    public WXApiTableDataInfo getWXNewsArticleList(NewsArticle newsArticle) {
        startPage();
        List<NewsArticle> list = newsArticleService.selectWXNewsArticleList(newsArticle);
        return getDataTable(list);
    }

    /* 随机获取指定数量的文章*/
    @ApiOperation("获取最新文章列表")
//    @PreAuthorize("@ss.hasPermi('web:article:list')")
    @GetMapping("/posts/random")
    public WXApiTableDataInfo getWXRandomNewsArticleList(Integer articleNum) {
        List<NewsArticle> list = newsArticleService.selectRandomArticleList(articleNum);
        return getDataTable(list);
    }

    /* 搜索文章列表*/
    @ApiOperation("关键词搜索文章列表")
    @GetMapping("/posts/search")
    public WXApiTableDataInfo searchWXNewsArticleList(String search) {

        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setTitle(search);
        startPage();
        List<NewsArticle> list = newsArticleService.selectWXNewsArticleList(newsArticle);
        return getDataTable(list);
    }

    /*获取小程序文章详细信息*/
    @ApiOperation("获取文章详细信息")
    @GetMapping("/posts/detail")
    public AjaxResult getWXArticleDetail(@RequestParam Long newsArticleId, @RequestParam(required = false) String token) {
        NewsArticle newsArticle = newsArticleService.selectNewsArticleByNewsArticleId(newsArticleId);

        // 获取文章评论和收藏数
        newsArticle.setCommentCount(newsArticleService.selectArticleCommentCount(newsArticleId));
        newsArticle.setFavoriteCount(newsArticleService.selectArticleCollectionCount(newsArticleId));

        // 登录之后获取用户关于文章的信息
        if (!token.equals("false")) {
            List<NewsWxUser> newsWxUsers = newsWxUserService.selectNewsWxUserList(new NewsWxUser(token));
            Long userId = newsWxUsers.get(0).getNewsWxUserId();

            // 获取用户是否已经点赞
            NewsArticleAndUserLike newsArticleAndUserLike = new NewsArticleAndUserLike();
            newsArticleAndUserLike.setUserId(userId);
            newsArticleAndUserLike.setArticleId(newsArticleId);
            List<NewsArticleAndUserLike> newsArticleAndUserLikes =
                    newsArticleAndUserLikeService.selectNewsArticleAndUserLikeList(newsArticleAndUserLike);

            if (newsArticleAndUserLikes.size() != 0) {
                newsArticle.setIsLike(1);
            } else {
                newsArticle.setIsLike(0);
            }

            //获取用户是否已经收藏过该文章
            NewsArticleAndUserCollection newsArticleAndUserCollection = new NewsArticleAndUserCollection();
            newsArticleAndUserCollection.setWxUserId(userId);
            newsArticleAndUserCollection.setArticleId(newsArticleId);
            List<NewsArticleAndUserCollection> newsArticleAndUserCollections =
                    newsArticleAndUserCollectionService.selectNewsArticleAndUserCollectionList(newsArticleAndUserCollection);
            if (newsArticleAndUserCollections.size() != 0) {
                newsArticle.setIsFavorite(1);
            } else {
                newsArticle.setIsFavorite(0);
            }
        } else {
            newsArticle.setIsLike(0);
            newsArticle.setIsFavorite(0);
        }
        return success(newsArticle);
    }


    /* 获取热榜列表*/
    @ApiOperation("获取最新文章列表")
//    @PreAuthorize("@ss.hasPermi('web:article:list')")
    @GetMapping("/posts/hot")
    public WXApiTableDataInfo getWXNewsArticleHotList(NewsArticle newsArticle) {
        startPage();
        List<NewsArticle> list = newsArticleService.selectWXNewsArticleList(newsArticle);
        return getDataTable(list);
    }

    @ApiOperation("获取文章评论数")
    @GetMapping("/posts/commentCount")
    /* 获取文章评论数*/
    public AjaxResult getArticleCommentCount(Long newsArticleId) {
        return success(newsArticleService.selectArticleCommentCount(newsArticleId));
    }

    @ApiOperation("获取文章收藏数")
    @GetMapping("/posts/collectionCount")
    /* 获取文章收藏数*/
    public AjaxResult getArticleCollectionCount(Long newsArticleId) {
        return success(newsArticleService.selectArticleCollectionCount(newsArticleId));

    }

    /**
     * 新增文章
     */
    @ApiOperation("新增文章")
//    @PreAuthorize("@ss.hasPermi('web:article:add')")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping("/article")
    public AjaxResult add(@RequestBody NewsArticle newsArticle) {
        return toAjax(newsArticleService.insertNewsArticle(newsArticle));
    }

    /**
     * 修改文章
     */
    @ApiOperation("修改文章")
//    @PreAuthorize("@ss.hasPermi('web:article:edit')")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping("/article")
    public AjaxResult edit(@RequestBody NewsArticle newsArticle) {
        return toAjax(newsArticleService.updateNewsArticle(newsArticle));
    }

    /**
     * 删除文章
     */
    @ApiOperation("删除文章")
//    @PreAuthorize("@ss.hasPermi('web:article:remove')")
    @Log(title = "文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/article/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(newsArticleService.deleteNewsArticleByNewsArticleIds(ids));
    }
}
