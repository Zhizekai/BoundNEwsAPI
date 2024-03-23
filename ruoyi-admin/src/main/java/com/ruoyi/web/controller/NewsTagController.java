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
import com.ruoyi.web.domain.NewsTag;
import com.ruoyi.web.service.INewsTagService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 标签Controller
 * 
 * @author keloid
 * @date 2024-02-28
 */
@RestController
@RequestMapping("/web/tag")
public class NewsTagController extends BaseController
{
    @Autowired
    private INewsTagService newsTagService;

    /**
     * 查询标签列表
     */
    @PreAuthorize("@ss.hasPermi('web:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsTag newsTag)
    {
        startPage();
        List<NewsTag> list = newsTagService.selectNewsTagList(newsTag);
        return getDataTable(list);
    }

    /**
     * 导出标签列表
     */
    @PreAuthorize("@ss.hasPermi('web:tag:export')")
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsTag newsTag)
    {
        List<NewsTag> list = newsTagService.selectNewsTagList(newsTag);
        ExcelUtil<NewsTag> util = new ExcelUtil<NewsTag>(NewsTag.class);
        util.exportExcel(response, list, "标签数据");
    }

    /**
     * 获取标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:tag:query')")
    @GetMapping(value = "/{newsTagId}")
    public AjaxResult getInfo(@PathVariable("newsTagId") Long newsTagId)
    {
        return success(newsTagService.selectNewsTagByNewsTagId(newsTagId));
    }

    /**
     * 新增标签
     */
    @PreAuthorize("@ss.hasPermi('web:tag:add')")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsTag newsTag)
    {
        return toAjax(newsTagService.insertNewsTag(newsTag));
    }

    /**
     * 修改标签
     */
    @PreAuthorize("@ss.hasPermi('web:tag:edit')")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsTag newsTag)
    {
        return toAjax(newsTagService.updateNewsTag(newsTag));
    }

    /**
     * 删除标签
     */
    @PreAuthorize("@ss.hasPermi('web:tag:remove')")
    @Log(title = "标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{newsTagIds}")
    public AjaxResult remove(@PathVariable Long[] newsTagIds)
    {
        return toAjax(newsTagService.deleteNewsTagByNewsTagIds(newsTagIds));
    }
}
