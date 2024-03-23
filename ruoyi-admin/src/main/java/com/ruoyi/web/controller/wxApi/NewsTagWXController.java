package com.ruoyi.web.controller.wxApi;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.controller.WXApiBaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.WXApiTableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.domain.NewsTag;
import com.ruoyi.web.service.INewsTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 标签管理Controller
 * 
 * @author ruoyi
 * @date 2024-02-11
 */
@Api(tags = "小程序标签模块")
@RestController
@RequestMapping("/v1/wx/tag")
public class NewsTagWXController extends WXApiBaseController
{
    @Autowired
    private INewsTagService newsTagService;

    /**
     * 查询标签管理列表
     */
//    @PreAuthorize("@ss.hasPermi('web:tag:list')")
    @ApiOperation("获取标签列表")
    @GetMapping("/list")
    public WXApiTableDataInfo list(NewsTag newsTag)
    {
        startPage();
        List<NewsTag> list = newsTagService.selectNewsTagList(newsTag);
        return getDataTable(list);
    }

    /**
     * 导出标签管理列表
     */
    @PreAuthorize("@ss.hasPermi('web:tag:export')")
    @Log(title = "标签管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsTag newsTag)
    {
        List<NewsTag> list = newsTagService.selectNewsTagList(newsTag);
        ExcelUtil<NewsTag> util = new ExcelUtil<NewsTag>(NewsTag.class);
        util.exportExcel(response, list, "标签管理数据");
    }

    /**
     * 获取标签管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newsTagService.selectNewsTagByNewsTagId(id));
    }

    /**
     * 新增标签管理
     */
    @PreAuthorize("@ss.hasPermi('web:tag:add')")
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsTag newsTag)
    {
        return toAjax(newsTagService.insertNewsTag(newsTag));
    }

    /**
     * 修改标签管理
     */
    @PreAuthorize("@ss.hasPermi('web:tag:edit')")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsTag newsTag)
    {
        return toAjax(newsTagService.updateNewsTag(newsTag));
    }

    /**
     * 删除标签管理
     */
    @PreAuthorize("@ss.hasPermi('web:tag:remove')")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newsTagService.deleteNewsTagByNewsTagIds(ids));
    }
}
