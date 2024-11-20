package com.tide.cultural.service.impl;

import java.util.List;
import com.tide.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tide.cultural.mapper.FestivalCategoryMapper;
import com.tide.cultural.domain.FestivalCategory;
import com.tide.cultural.service.IFestivalCategoryService;

/**
 * 节日类别Service业务层处理
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
@Service
public class FestivalCategoryServiceImpl implements IFestivalCategoryService 
{
    @Autowired
    private FestivalCategoryMapper festivalCategoryMapper;

    /**
     * 查询节日类别
     * 
     * @param id 节日类别主键
     * @return 节日类别
     */
    @Override
    public FestivalCategory selectFestivalCategoryById(String id)
    {
        return festivalCategoryMapper.selectFestivalCategoryById(id);
    }

    /**
     * 查询节日类别列表
     * 
     * @param festivalCategory 节日类别
     * @return 节日类别
     */
    @Override
    public List<FestivalCategory> selectFestivalCategoryList(FestivalCategory festivalCategory)
    {
        return festivalCategoryMapper.selectFestivalCategoryList(festivalCategory);
    }

    /**
     * 新增节日类别
     * 
     * @param festivalCategory 节日类别
     * @return 结果
     */
    @Override
    public int insertFestivalCategory(FestivalCategory festivalCategory)
    {
        festivalCategory.setCreateTime(DateUtils.getNowDate());
        return festivalCategoryMapper.insertFestivalCategory(festivalCategory);
    }

    /**
     * 修改节日类别
     * 
     * @param festivalCategory 节日类别
     * @return 结果
     */
    @Override
    public int updateFestivalCategory(FestivalCategory festivalCategory)
    {
        festivalCategory.setUpdateTime(DateUtils.getNowDate());
        return festivalCategoryMapper.updateFestivalCategory(festivalCategory);
    }

    /**
     * 批量删除节日类别
     * 
     * @param ids 需要删除的节日类别主键
     * @return 结果
     */
    @Override
    public int deleteFestivalCategoryByIds(String[] ids)
    {
        return festivalCategoryMapper.deleteFestivalCategoryByIds(ids);
    }

    /**
     * 删除节日类别信息
     * 
     * @param id 节日类别主键
     * @return 结果
     */
    @Override
    public int deleteFestivalCategoryById(String id)
    {
        return festivalCategoryMapper.deleteFestivalCategoryById(id);
    }
}
