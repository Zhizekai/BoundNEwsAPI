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
import com.ruoyi.web.domain.NewsCategory;
import com.ruoyi.web.service.INewsCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章类型Controller
 * 
 * @author keloid
 * @date 2024-02-28
 */
@RestController
@RequestMapping("/web/category")
public class NewsCategoryController extends BaseController
{
    @Autowired
    private INewsCategoryService newsCategoryService;

    /**
     * 查询文章类型列表
     */
    @PreAuthorize("@ss.hasPermi('web:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsCategory newsCategory)
    {
        startPage();
        List<NewsCategory> list = newsCategoryService.selectNewsCategoryList(newsCategory);
        return getDataTable(list);
    }

    /**
     * 导出文章类型列表
     */
    @PreAuthorize("@ss.hasPermi('web:category:export')")
    @Log(title = "文章类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsCategory newsCategory)
    {
        List<NewsCategory> list = newsCategoryService.selectNewsCategoryList(newsCategory);
        ExcelUtil<NewsCategory> util = new ExcelUtil<NewsCategory>(NewsCategory.class);
        util.exportExcel(response, list, "文章类型数据");
    }

    /**
     * 获取文章类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:category:query')")
    @GetMapping(value = "/{newsCategoryId}")
    public AjaxResult getInfo(@PathVariable("newsCategoryId") Long newsCategoryId)
    {
        return success(newsCategoryService.selectNewsCategoryByNewsCategoryId(newsCategoryId));
    }

    /**
     * 新增文章类型
     */
    @PreAuthorize("@ss.hasPermi('web:category:add')")
    @Log(title = "文章类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsCategory newsCategory)
    {
        return toAjax(newsCategoryService.insertNewsCategory(newsCategory));
    }

    /**
     * 修改文章类型
     */
    @PreAuthorize("@ss.hasPermi('web:category:edit')")
    @Log(title = "文章类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsCategory newsCategory)
    {
        return toAjax(newsCategoryService.updateNewsCategory(newsCategory));
    }

    /**
     * 删除文章类型
     */
    @PreAuthorize("@ss.hasPermi('web:category:remove')")
    @Log(title = "文章类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{newsCategoryIds}")
    public AjaxResult remove(@PathVariable Long[] newsCategoryIds)
    {
        return toAjax(newsCategoryService.deleteNewsCategoryByNewsCategoryIds(newsCategoryIds));
    }
}
