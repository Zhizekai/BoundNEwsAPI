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
import com.ruoyi.web.domain.NewsArticleAndUserLike;
import com.ruoyi.web.service.INewsArticleAndUserLikeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章和用户点赞的关联Controller
 * 
 * @author keloid
 * @date 2024-03-05
 */
@RestController
@RequestMapping("/web/like")
public class NewsArticleAndUserLikeController extends BaseController
{
    @Autowired
    private INewsArticleAndUserLikeService newsArticleAndUserLikeService;

    /**
     * 查询文章和用户点赞的关联列表
     */
    @PreAuthorize("@ss.hasPermi('web:like:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsArticleAndUserLike newsArticleAndUserLike)
    {
        startPage();
        List<NewsArticleAndUserLike> list = newsArticleAndUserLikeService.selectNewsArticleAndUserLikeList(newsArticleAndUserLike);
        return getDataTable(list);
    }

    /**
     * 导出文章和用户点赞的关联列表
     */
    @PreAuthorize("@ss.hasPermi('web:like:export')")
    @Log(title = "文章和用户点赞的关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsArticleAndUserLike newsArticleAndUserLike)
    {
        List<NewsArticleAndUserLike> list = newsArticleAndUserLikeService.selectNewsArticleAndUserLikeList(newsArticleAndUserLike);
        ExcelUtil<NewsArticleAndUserLike> util = new ExcelUtil<NewsArticleAndUserLike>(NewsArticleAndUserLike.class);
        util.exportExcel(response, list, "文章和用户点赞的关联数据");
    }

    /**
     * 获取文章和用户点赞的关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:like:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newsArticleAndUserLikeService.selectNewsArticleAndUserLikeById(id));
    }

    /**
     * 新增文章和用户点赞的关联
     */
    @PreAuthorize("@ss.hasPermi('web:like:add')")
    @Log(title = "文章和用户点赞的关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsArticleAndUserLike newsArticleAndUserLike)
    {
        return toAjax(newsArticleAndUserLikeService.insertNewsArticleAndUserLike(newsArticleAndUserLike));
    }

    /**
     * 修改文章和用户点赞的关联
     */
    @PreAuthorize("@ss.hasPermi('web:like:edit')")
    @Log(title = "文章和用户点赞的关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsArticleAndUserLike newsArticleAndUserLike)
    {
        return toAjax(newsArticleAndUserLikeService.updateNewsArticleAndUserLike(newsArticleAndUserLike));
    }

    /**
     * 删除文章和用户点赞的关联
     */
    @PreAuthorize("@ss.hasPermi('web:like:remove')")
    @Log(title = "文章和用户点赞的关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newsArticleAndUserLikeService.deleteNewsArticleAndUserLikeByIds(ids));
    }
}
