package com.ruoyi.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.web.domain.NewsComment;
import com.ruoyi.web.service.INewsCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章评论Controller
 * 
 * @author keloid
 * @date 2024-02-28
 */
@RestController
@RequestMapping("/web/comment")
public class NewsCommentController extends BaseController
{
    @Autowired
    private INewsCommentService newsCommentService;

    /**
     * 查询文章评论列表
     */
    @PreAuthorize("@ss.hasPermi('web:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsComment newsComment)
    {
        startPage();
        List<NewsComment> list = newsCommentService.selectNewsCommentList(newsComment);
        return getDataTable(list);
    }

    /**
     * 导出文章评论列表
     */
    @PreAuthorize("@ss.hasPermi('web:comment:export')")
    @Log(title = "文章评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsComment newsComment)
    {
        List<NewsComment> list = newsCommentService.selectNewsCommentList(newsComment);
        ExcelUtil<NewsComment> util = new ExcelUtil<NewsComment>(NewsComment.class);
        util.exportExcel(response, list, "文章评论数据");
    }

    /**
     * 获取文章评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:comment:query')")
    @GetMapping(value = "/{newsCommentId}")
    public AjaxResult getInfo(@PathVariable("newsCommentId") Long newsCommentId)
    {
        return success(newsCommentService.selectNewsCommentByNewsCommentId(newsCommentId));
    }

    /**
     * 新增文章评论
     */
    @PreAuthorize("@ss.hasPermi('web:comment:add')")
    @Log(title = "文章评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsComment newsComment)
    {
        return toAjax(newsCommentService.insertNewsComment(newsComment));
    }

    /**
     * 修改文章评论
     */
    @PreAuthorize("@ss.hasPermi('web:comment:edit')")
    @Log(title = "文章评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsComment newsComment)
    {
        return toAjax(newsCommentService.updateNewsComment(newsComment));
    }

    /**
     * 删除文章评论
     */
    @PreAuthorize("@ss.hasPermi('web:comment:remove')")
    @Log(title = "文章评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{newsCommentIds}")
    public AjaxResult remove(@PathVariable Long[] newsCommentIds)
    {
        return toAjax(newsCommentService.deleteNewsCommentByNewsCommentIds(newsCommentIds));
    }
}
