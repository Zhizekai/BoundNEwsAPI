package com.ruoyi.web.controller.wxApi;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.controller.WXApiBaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.WXApiTableDataInfo;
import com.ruoyi.web.domain.NewsArticle;
import com.ruoyi.web.domain.NewsCategory;
import com.ruoyi.web.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * 标签管理Controller
 *
 * @author ruoyi
 * @date 2024-02-11
 */
@Api(tags = "小程序配置模块")
@RestController
@RequestMapping("/v1/wx/setting")
public class NewsSettingWXController extends WXApiBaseController {
    @Autowired
    private INewsArticleService newsArticleService;

    @Autowired
    private INewsCategoryService categoryService;

    @Autowired
    private INewsArticleAndUserCollectionService newsArticleAndUserCollectionService;

    @Autowired
    private INewsWxUserService newsWxUserService;

    /* 获取首页前端配置*/
    @ApiOperation("获取首页配置")
    @GetMapping("/home")
    public AjaxResult getHomeSetting(NewsCategory newsCategory) throws IOException {
        startPage();
        // 获取分类列表
        List<NewsCategory> list = categoryService.selectNewsCategoryList(newsCategory);

        // 获取随机轮播图文章列表
        List<NewsArticle> slideList = newsArticleService.selectRandomArticleList(6);

        // 获取随机热门文章列表
        List<NewsArticle> hotList = newsArticleService.selectRandomArticleList(5);

        // 获取json配置文件
        JSONObject jsonobject = getJson("home_setting");
        assert jsonobject != null;
        JSONObject data = (JSONObject) jsonobject.get("data");
        data.put("top_nav", list);
        data.put("slide", slideList);
        data.put("hot", hotList);
        return success(data);
    }

    // 获取resource 下面的json配置文件
    public static JSONObject getJson(String jsonPath) {
        String jsonStr;
        try {
            ClassPathResource classPathResource = new ClassPathResource("json/" + jsonPath + ".json");
            InputStream inputStream = classPathResource.getInputStream();
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            jsonStr = sb.toString();
            return JSON.parseObject(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 获取分类页的配置*/
    @ApiOperation("获取分类配置")
    @GetMapping("/category")
    public WXApiTableDataInfo getCategorySetting(NewsArticle newsArticle) {
        startPage();
        List<NewsArticle> list = newsArticleService.selectWXNewsArticleList(newsArticle);
        return getDataTable(list);
    }


    /* 获取用户中心的配置，这个是随便返回的，前端已经把配置文件写死了*/
    @ApiOperation("获取分类配置")
    @GetMapping("/ucenter")
    public WXApiTableDataInfo getUserCenterSetting(NewsArticle newsArticle) {
        startPage();
        List<NewsArticle> list = newsArticleService.selectWXNewsArticleList(newsArticle);
        return getDataTable(list);
    }

    /* 获取热榜页面的配置文件*/
    @ApiOperation("获取分类配置")
    @GetMapping("/hot")
    public WXApiTableDataInfo geHotSetting(NewsArticle newsArticle) {
        startPage();
        List<NewsArticle> list = newsArticleService.selectWXNewsArticleList(newsArticle);
        return getDataTable(list);
    }

    /* 获取登录的配置文件*/
    @ApiOperation("获取分类配置")
    @GetMapping("/login")
    public WXApiTableDataInfo geLoginSetting(NewsArticle newsArticle) {
        startPage();
        List<NewsArticle> list = newsArticleService.selectWXNewsArticleList(newsArticle);
        return getDataTable(list);
    }
}
