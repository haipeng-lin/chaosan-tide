package com.tide.cultural.service;

import java.util.List;
import com.tide.cultural.domain.FestivalCategory;

/**
 * 节日类别Service接口
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
public interface IFestivalCategoryService 
{
    /**
     * 查询节日类别
     * 
     * @param id 节日类别主键
     * @return 节日类别
     */
    public FestivalCategory selectFestivalCategoryById(String id);

    /**
     * 查询节日类别列表
     * 
     * @param festivalCategory 节日类别
     * @return 节日类别集合
     */
    public List<FestivalCategory> selectFestivalCategoryList(FestivalCategory festivalCategory);

    /**
     * 新增节日类别
     * 
     * @param festivalCategory 节日类别
     * @return 结果
     */
    public int insertFestivalCategory(FestivalCategory festivalCategory);

    /**
     * 修改节日类别
     * 
     * @param festivalCategory 节日类别
     * @return 结果
     */
    public int updateFestivalCategory(FestivalCategory festivalCategory);

    /**
     * 批量删除节日类别
     * 
     * @param ids 需要删除的节日类别主键集合
     * @return 结果
     */
    public int deleteFestivalCategoryByIds(String[] ids);

    /**
     * 删除节日类别信息
     * 
     * @param id 节日类别主键
     * @return 结果
     */
    public int deleteFestivalCategoryById(String id);
}
