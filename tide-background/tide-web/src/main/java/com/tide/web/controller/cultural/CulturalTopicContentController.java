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
import com.tide.cultural.domain.CulturalTopicContent;
import com.tide.cultural.service.ICulturalTopicContentService;
import com.tide.common.utils.poi.ExcelUtil;
import com.tide.common.core.page.TableDataInfo;

/**
 * 文化专题内容Controller
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
@RestController
@RequestMapping("/cultural/culturalTopicContent")
public class CulturalTopicContentController extends BaseController
{
    @Autowired
    private ICulturalTopicContentService culturalTopicContentService;

    /**
     * 查询文化专题内容列表
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTopicContent:list')")
    @GetMapping("/list")
    public TableDataInfo list(CulturalTopicContent culturalTopicContent)
    {
        startPage();
        List<CulturalTopicContent> list = culturalTopicContentService.selectCulturalTopicContentList(culturalTopicContent);
        return getDataTable(list);
    }

    /**
     * 导出文化专题内容列表
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTopicContent:export')")
    @Log(title = "文化专题内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CulturalTopicContent culturalTopicContent)
    {
        List<CulturalTopicContent> list = culturalTopicContentService.selectCulturalTopicContentList(culturalTopicContent);
        ExcelUtil<CulturalTopicContent> util = new ExcelUtil<CulturalTopicContent>(CulturalTopicContent.class);
        util.exportExcel(response, list, "文化专题内容数据");
    }

    /**
     * 获取文化专题内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTopicContent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(culturalTopicContentService.selectCulturalTopicContentById(id));
    }

    /**
     * 新增文化专题内容
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTopicContent:add')")
    @Log(title = "文化专题内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CulturalTopicContent culturalTopicContent)
    {
        culturalTopicContent.setCreateBy(getUsername());
        return toAjax(culturalTopicContentService.insertCulturalTopicContent(culturalTopicContent));
    }

    /**
     * 修改文化专题内容
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTopicContent:edit')")
    @Log(title = "文化专题内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CulturalTopicContent culturalTopicContent)
    {
        culturalTopicContent.setUpdateBy(getUsername());
        return toAjax(culturalTopicContentService.updateCulturalTopicContent(culturalTopicContent));
    }

    /**
     * 删除文化专题内容
     */
    @PreAuthorize("@ss.hasPermi('cultural:culturalTopicContent:remove')")
    @Log(title = "文化专题内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(culturalTopicContentService.deleteCulturalTopicContentByIds(ids));
    }
}
