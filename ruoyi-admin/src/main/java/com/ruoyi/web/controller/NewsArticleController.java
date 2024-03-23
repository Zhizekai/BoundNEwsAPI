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
import com.ruoyi.web.domain.NewsArticle;
import com.ruoyi.web.service.INewsArticleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章Controller
 * 
 * @author keloid
 * @date 2024-02-28
 */
@RestController
@RequestMapping("/web/article")
public class NewsArticleController extends BaseController
{
    @Autowired
    private INewsArticleService newsArticleService;

    /**
     * 查询文章列表
     */
    @PreAuthorize("@ss.hasPermi('web:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsArticle newsArticle)
    {
        startPage();
        List<NewsArticle> list = newsArticleService.selectNewsArticleList(newsArticle);
        return getDataTable(list);
    }

    /**
     * 导出文章列表
     */
    @PreAuthorize("@ss.hasPermi('web:article:export')")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsArticle newsArticle)
    {
        List<NewsArticle> list = newsArticleService.selectNewsArticleList(newsArticle);
        ExcelUtil<NewsArticle> util = new ExcelUtil<NewsArticle>(NewsArticle.class);
        util.exportExcel(response, list, "文章数据");
    }

    /**
     * 获取文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:article:query')")
    @GetMapping(value = "/{newsArticleId}")
    public AjaxResult getInfo(@PathVariable("newsArticleId") Long newsArticleId)
    {
        return success(newsArticleService.selectNewsArticleByNewsArticleId(newsArticleId));
    }

    /**
     * 新增文章
     */
    @PreAuthorize("@ss.hasPermi('web:article:add')")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsArticle newsArticle)
    {
        return toAjax(newsArticleService.insertNewsArticle(newsArticle));
    }

    /**
     * 修改文章
     */
    @PreAuthorize("@ss.hasPermi('web:article:edit')")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsArticle newsArticle)
    {
        return toAjax(newsArticleService.updateNewsArticle(newsArticle));
    }

    /**
     * 删除文章
     */
    @PreAuthorize("@ss.hasPermi('web:article:remove')")
    @Log(title = "文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{newsArticleIds}")
    public AjaxResult remove(@PathVariable Long[] newsArticleIds)
    {
        return toAjax(newsArticleService.deleteNewsArticleByNewsArticleIds(newsArticleIds));
    }
}
