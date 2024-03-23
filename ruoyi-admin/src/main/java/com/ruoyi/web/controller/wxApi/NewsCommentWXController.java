package com.ruoyi.web.controller.wxApi;

import com.ruoyi.common.core.controller.WXApiBaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.WXApiTableDataInfo;
import com.ruoyi.web.domain.NewsArticleAndComment;
import com.ruoyi.web.domain.NewsComment;
import com.ruoyi.web.domain.NewsWxUser;
import com.ruoyi.web.service.INewsArticleAndCommentService;
import com.ruoyi.web.service.INewsCommentService;
import com.ruoyi.web.service.INewsWxUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 文章评论Controller
 *
 * @author keloid
 * @date 2024-02-11
 */
@Api(tags = "小程序评论模块")
@RestController
@RequestMapping("/v1/wx")
public class NewsCommentWXController extends WXApiBaseController {
    @Autowired
    private INewsCommentService newsCommentService;

    @Autowired
    private INewsArticleAndCommentService aCommentService;


    @Autowired
    private INewsWxUserService newsWxUserService;

    /**
     * 获取某一个文章的评论列表
     */
//    @PreAuthorize("@ss.hasPermi('web:comment:list')")
    @ApiOperation("获取某一个文章的评论列表")
    @GetMapping("/comment/index")
    public WXApiTableDataInfo list(@RequestParam(required = false) Long newsArticleId) {
        startPage();
        List<NewsComment> list = newsCommentService.selectCommentListByArticleId(newsArticleId);
        return getDataTable(list);
    }

    /* 用户发送评论*/
    @ApiOperation("用户添加评论")
    @PostMapping("/comment/add")
    public AjaxResult addComment(@RequestBody NewsComment newsComment) {

        // 获取用户id
        String token = newsComment.getToken();
        List<NewsWxUser> newsWxUsers = newsWxUserService.selectNewsWxUserList(new NewsWxUser(token));
        Long uId = newsWxUsers.get(0).getNewsWxUserId();
        newsComment.setUserId(uId);

        // 插入评论表并且返回新增的id
        newsCommentService.insertWXNewsComment(newsComment);
        Long commentId = newsComment.getNewsCommentId();

        // 插入关联表
        NewsArticleAndComment newsArticleAndComment = new NewsArticleAndComment();
        newsArticleAndComment.setArticleId(newsComment.getNewsArticleId());
        newsArticleAndComment.setCommentId(commentId);
        int i = aCommentService.insertNewsArticleAndComment(newsArticleAndComment);
        System.out.println(i);

        return success(newsComment.getNewsCommentId());
    }


}
