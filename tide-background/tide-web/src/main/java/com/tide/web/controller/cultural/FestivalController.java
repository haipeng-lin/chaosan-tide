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
import com.tide.cultural.domain.Festival;
import com.tide.cultural.service.IFestivalService;
import com.tide.common.utils.poi.ExcelUtil;
import com.tide.common.core.page.TableDataInfo;

/**
 * 节日Controller
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
@RestController
@RequestMapping("/cultural/culturalFestival")
public class FestivalController extends BaseController
{
    @Autowired
    private IFestivalService festivalService;

    /**
     * 查询节日列表
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalFestival:list')")
    @GetMapping("/list")
    public TableDataInfo list(Festival festival)
    {
        startPage();
        List<Festival> list = festivalService.selectFestivalList(festival);
        return getDataTable(list);
    }

    /**
     * 导出节日列表
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalFestival:export')")
    @Log(title = "节日", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Festival festival)
    {
        List<Festival> list = festivalService.selectFestivalList(festival);
        ExcelUtil<Festival> util = new ExcelUtil<Festival>(Festival.class);
        util.exportExcel(response, list, "节日数据");
    }

    /**
     * 获取节日详细信息
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalFestival:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(festivalService.selectFestivalById(id));
    }

    /**
     * 新增节日
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalFestival:add')")
    @Log(title = "节日", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Festival festival)
    {
        festival.setCreateBy(getUsername());
        return toAjax(festivalService.insertFestival(festival));
    }

    /**
     * 修改节日
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalFestival:edit')")
    @Log(title = "节日", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Festival festival)
    {
        festival.setUpdateBy(getUsername());
        return toAjax(festivalService.updateFestival(festival));
    }

    /**
     * 删除节日
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalFestival:remove')")
    @Log(title = "节日", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(festivalService.deleteFestivalByIds(ids));
    }
}
