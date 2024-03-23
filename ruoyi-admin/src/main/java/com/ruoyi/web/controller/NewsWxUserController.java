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
import com.ruoyi.web.domain.NewsWxUser;
import com.ruoyi.web.service.INewsWxUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 微信用户Controller
 * 
 * @author keloid
 * @date 2024-02-28
 */
@RestController
@RequestMapping("/web/user")
public class NewsWxUserController extends BaseController
{
    @Autowired
    private INewsWxUserService newsWxUserService;

    /**
     * 查询微信用户列表
     */
    @PreAuthorize("@ss.hasPermi('web:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsWxUser newsWxUser)
    {
        startPage();
        List<NewsWxUser> list = newsWxUserService.selectNewsWxUserList(newsWxUser);
        return getDataTable(list);
    }

    /**
     * 导出微信用户列表
     */
    @PreAuthorize("@ss.hasPermi('web:user:export')")
    @Log(title = "微信用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsWxUser newsWxUser)
    {
        List<NewsWxUser> list = newsWxUserService.selectNewsWxUserList(newsWxUser);
        ExcelUtil<NewsWxUser> util = new ExcelUtil<NewsWxUser>(NewsWxUser.class);
        util.exportExcel(response, list, "微信用户数据");
    }

    /**
     * 获取微信用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('web:user:query')")
    @GetMapping(value = "/{newsWxUserId}")
    public AjaxResult getInfo(@PathVariable("newsWxUserId") Long newsWxUserId)
    {
        return success(newsWxUserService.selectNewsWxUserByNewsWxUserId(newsWxUserId));
    }

    /**
     * 新增微信用户
     */
    @PreAuthorize("@ss.hasPermi('web:user:add')")
    @Log(title = "微信用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsWxUser newsWxUser)
    {
        return toAjax(newsWxUserService.insertNewsWxUser(newsWxUser));
    }

    /**
     * 修改微信用户
     */
    @PreAuthorize("@ss.hasPermi('web:user:edit')")
    @Log(title = "微信用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsWxUser newsWxUser)
    {
        return toAjax(newsWxUserService.updateNewsWxUser(newsWxUser));
    }

    /**
     * 删除微信用户
     */
    @PreAuthorize("@ss.hasPermi('web:user:remove')")
    @Log(title = "微信用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{newsWxUserIds}")
    public AjaxResult remove(@PathVariable Long[] newsWxUserIds)
    {
        return toAjax(newsWxUserService.deleteNewsWxUserByNewsWxUserIds(newsWxUserIds));
    }
}
