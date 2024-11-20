package com.tide.web.controller.cultural;

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
import com.tide.common.annotation.Log;
import com.tide.common.core.controller.BaseController;
import com.tide.common.core.domain.AjaxResult;
import com.tide.common.enums.BusinessType;
import com.tide.cultural.domain.FestivalCategory;
import com.tide.cultural.service.IFestivalCategoryService;
import com.tide.common.utils.poi.ExcelUtil;
import com.tide.common.core.page.TableDataInfo;

/**
 * 节日类别Controller
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
@RestController
@RequestMapping("/cultural/culturalCategory")
public class FestivalCategoryController extends BaseController
{
    @Autowired
    private IFestivalCategoryService festivalCategoryService;

    /**
     * 查询节日类别列表
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalCategory:list')")
    @GetMapping("/list")
    public TableDataInfo list(FestivalCategory festivalCategory)
    {
        startPage();
        List<FestivalCategory> list = festivalCategoryService.selectFestivalCategoryList(festivalCategory);
        return getDataTable(list);
    }

    /**
     * 导出节日类别列表
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalCategory:export')")
    @Log(title = "节日类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FestivalCategory festivalCategory)
    {
        List<FestivalCategory> list = festivalCategoryService.selectFestivalCategoryList(festivalCategory);
        ExcelUtil<FestivalCategory> util = new ExcelUtil<FestivalCategory>(FestivalCategory.class);
        util.exportExcel(response, list, "节日类别数据");
    }

    /**
     * 获取节日类别详细信息
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalCategory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(festivalCategoryService.selectFestivalCategoryById(id));
    }

    /**
     * 新增节日类别
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalCategory:add')")
    @Log(title = "节日类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FestivalCategory festivalCategory)
    {
        festivalCategory.setCreateBy(getUsername());
        return toAjax(festivalCategoryService.insertFestivalCategory(festivalCategory));
    }

    /**
     * 修改节日类别
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalCategory:edit')")
    @Log(title = "节日类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FestivalCategory festivalCategory)
    {
        festivalCategory.setUpdateBy(getUsername());
        return toAjax(festivalCategoryService.updateFestivalCategory(festivalCategory));
    }

    /**
     * 删除节日类别
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalCategory:remove')")
    @Log(title = "节日类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(festivalCategoryService.deleteFestivalCategoryByIds(ids));
    }
}
