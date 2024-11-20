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
import com.tide.cultural.domain.CulturalTag;
import com.tide.cultural.service.ICulturalTagService;
import com.tide.common.utils.poi.ExcelUtil;
import com.tide.common.core.page.TableDataInfo;

/**
 * 文化标签Controller
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
@RestController
@RequestMapping("/cultural/culturalTag")
public class CulturalTagController extends BaseController
{
    @Autowired
    private ICulturalTagService culturalTagService;

    /**
     * 查询文化标签列表
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTag:list')")
    @GetMapping("/list")
    public TableDataInfo list(CulturalTag culturalTag)
    {
        startPage();
        List<CulturalTag> list = culturalTagService.selectCulturalTagList(culturalTag);
        return getDataTable(list);
    }

    /**
     * 导出文化标签列表
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTag:export')")
    @Log(title = "文化标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CulturalTag culturalTag)
    {
        List<CulturalTag> list = culturalTagService.selectCulturalTagList(culturalTag);
        ExcelUtil<CulturalTag> util = new ExcelUtil<CulturalTag>(CulturalTag.class);
        util.exportExcel(response, list, "文化标签数据");
    }

    /**
     * 获取文化标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(culturalTagService.selectCulturalTagById(id));
    }

    /**
     * 新增文化标签
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTag:add')")
    @Log(title = "文化标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CulturalTag culturalTag)
    {
        culturalTag.setCreateBy(getUsername());
        return toAjax(culturalTagService.insertCulturalTag(culturalTag));
    }

    /**
     * 修改文化标签
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTag:edit')")
    @Log(title = "文化标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CulturalTag culturalTag)
    {
        culturalTag.setUpdateBy(getUsername());
        return toAjax(culturalTagService.updateCulturalTag(culturalTag));
    }

    /**
     * 删除文化标签
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTag:remove')")
    @Log(title = "文化标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(culturalTagService.deleteCulturalTagByIds(ids));
    }
}
