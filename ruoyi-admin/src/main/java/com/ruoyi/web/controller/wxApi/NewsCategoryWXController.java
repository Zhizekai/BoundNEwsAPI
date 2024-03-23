package com.ruoyi.web.controller.wxApi;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.controller.WXApiBaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.WXApiTableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.domain.NewsArticle;
import com.ruoyi.web.domain.NewsCategory;
import com.ruoyi.web.service.INewsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文章类型Controller
 * 
 * @author keloid
 * @date 2024-02-11
 */
@Api(tags = "小程序文章分类模块")
@RestController
@RequestMapping("/v1/wx")
public class NewsCategoryWXController extends WXApiBaseController
{
    @Autowired
    private INewsCategoryService newsCategoryService;

    @ApiOperation("获取分类列表")
    @GetMapping("/category/index")
    public WXApiTableDataInfo getCategoryList(NewsCategory newsCategory) {
        startPage();
        List<NewsCategory> list = newsCategoryService.selectNewsCategoryList(newsCategory);
        return getDataTable(list);
    }

    /**
     * 导出文章类型列表
     */
//    @PreAuthorize("@ss.hasPermi('web:category:export')")
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
//    @PreAuthorize("@ss.hasPermi('web:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newsCategoryService.selectNewsCategoryByNewsCategoryId(id));
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
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newsCategoryService.deleteNewsCategoryByNewsCategoryIds(ids));
    }
}
