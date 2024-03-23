package com.ruoyi.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.web.domain.NewsArticleAndComment;
import com.ruoyi.web.service.INewsArticleAndCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 关联表Controller
 * 
 * @author keloid
 * @date 2024-03-06
 */
@Api(tags = "评论文章关联表模块")
@RestController
@RequestMapping("/v1/wx/web/commentAndArticle")
public class NewsArticleAndCommentController extends BaseController
{
    @Autowired
    private INewsArticleAndCommentService newsArticleAndCommentService;

    /**
     * 查询关联表列表
     */
    @PreAuthorize("@ss.hasPermi('web:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsArticleAndComment newsArticleAndComment)
    {
        startPage();
        List<NewsArticleAndComment> list = newsArticleAndCommentService.selectNewsArticleAndCommentList(newsArticleAndComment);
        return getDataTable(list);
    }

    /**
     * 导出关联表列表
     */
    @PreAuthorize("@ss.hasPermi('web:comment:export')")
    @Log(title = "关联表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsArticleAndComment newsArticleAndComment)
    {
        List<NewsArticleAndComment> list = newsArticleAndCommentService.selectNewsArticleAndCommentList(newsArticleAndComment);
        ExcelUtil<NewsArticleAndComment> util = new ExcelUtil<NewsArticleAndComment>(NewsArticleAndComment.class);
        util.exportExcel(response, list, "关联表数据");
    }

    /**
     * 获取关联表详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:comment:query')")
    @GetMapping(value = "/{articleAndCommentId}")
    public AjaxResult getInfo(@PathVariable("articleAndCommentId") Long articleAndCommentId)
    {
        return success(newsArticleAndCommentService.selectNewsArticleAndCommentByArticleAndCommentId(articleAndCommentId));
    }

    /**
     * 新增关联表
     */
    @ApiOperation("新增关联")
    @Log(title = "关联表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsArticleAndComment newsArticleAndComment)
    {
        System.out.println(newsArticleAndComment.toString());
        return toAjax(newsArticleAndCommentService.insertNewsArticleAndComment(newsArticleAndComment));
    }

    /**
     * 修改关联表
     */
    @PreAuthorize("@ss.hasPermi('web:comment:edit')")
    @Log(title = "关联表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsArticleAndComment newsArticleAndComment)
    {
        return toAjax(newsArticleAndCommentService.updateNewsArticleAndComment(newsArticleAndComment));
    }

    /**
     * 删除关联表
     */
    @PreAuthorize("@ss.hasPermi('web:comment:remove')")
    @Log(title = "关联表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{articleAndCommentIds}")
    public AjaxResult remove(@PathVariable Long[] articleAndCommentIds)
    {
        return toAjax(newsArticleAndCommentService.deleteNewsArticleAndCommentByArticleAndCommentIds(articleAndCommentIds));
    }
}
