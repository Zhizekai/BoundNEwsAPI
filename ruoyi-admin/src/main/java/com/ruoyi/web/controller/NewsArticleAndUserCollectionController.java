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
import com.ruoyi.web.domain.NewsArticleAndUserCollection;
import com.ruoyi.web.service.INewsArticleAndUserCollectionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户收藏Controller
 * 
 * @author keloid
 * @date 2024-03-05
 */
@RestController
@RequestMapping("/web/collection")
public class NewsArticleAndUserCollectionController extends BaseController
{
    @Autowired
    private INewsArticleAndUserCollectionService newsArticleAndUserCollectionService;

    /**
     * 查询用户收藏列表
     */
    @PreAuthorize("@ss.hasPermi('web:collection:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsArticleAndUserCollection newsArticleAndUserCollection)
    {
        startPage();
        List<NewsArticleAndUserCollection> list = newsArticleAndUserCollectionService.selectNewsArticleAndUserCollectionList(newsArticleAndUserCollection);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏列表
     */
    @PreAuthorize("@ss.hasPermi('web:collection:export')")
    @Log(title = "用户收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsArticleAndUserCollection newsArticleAndUserCollection)
    {
        List<NewsArticleAndUserCollection> list = newsArticleAndUserCollectionService.selectNewsArticleAndUserCollectionList(newsArticleAndUserCollection);
        ExcelUtil<NewsArticleAndUserCollection> util = new ExcelUtil<NewsArticleAndUserCollection>(NewsArticleAndUserCollection.class);
        util.exportExcel(response, list, "用户收藏数据");
    }

    /**
     * 获取用户收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:collection:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newsArticleAndUserCollectionService.selectNewsArticleAndUserCollectionById(id));
    }

    /**
     * 新增用户收藏
     */
    @PreAuthorize("@ss.hasPermi('web:collection:add')")
    @Log(title = "用户收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsArticleAndUserCollection newsArticleAndUserCollection)
    {
        return toAjax(newsArticleAndUserCollectionService.insertNewsArticleAndUserCollection(newsArticleAndUserCollection));
    }

    /**
     * 修改用户收藏
     */
    @PreAuthorize("@ss.hasPermi('web:collection:edit')")
    @Log(title = "用户收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsArticleAndUserCollection newsArticleAndUserCollection)
    {
        return toAjax(newsArticleAndUserCollectionService.updateNewsArticleAndUserCollection(newsArticleAndUserCollection));
    }

    /**
     * 删除用户收藏
     */
    @PreAuthorize("@ss.hasPermi('web:collection:remove')")
    @Log(title = "用户收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newsArticleAndUserCollectionService.deleteNewsArticleAndUserCollectionByIds(ids));
    }
}
