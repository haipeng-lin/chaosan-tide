package com.tide.cultural.service.impl;

import java.util.List;
import com.tide.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tide.cultural.mapper.FestivalMapper;
import com.tide.cultural.domain.Festival;
import com.tide.cultural.service.IFestivalService;

/**
 * 节日Service业务层处理
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
@Service
public class FestivalServiceImpl implements IFestivalService 
{
    @Autowired
    private FestivalMapper festivalMapper;

    /**
     * 查询节日
     * 
     * @param id 节日主键
     * @return 节日
     */
    @Override
    public Festival selectFestivalById(String id)
    {
        return festivalMapper.selectFestivalById(id);
    }

    /**
     * 查询节日列表
     * 
     * @param festival 节日
     * @return 节日
     */
    @Override
    public List<Festival> selectFestivalList(Festival festival)
    {
        return festivalMapper.selectFestivalList(festival);
    }

    /**
     * 新增节日
     * 
     * @param festival 节日
     * @return 结果
     */
    @Override
    public int insertFestival(Festival festival)
    {
        festival.setCreateTime(DateUtils.getNowDate());
        return festivalMapper.insertFestival(festival);
    }

    /**
     * 修改节日
     * 
     * @param festival 节日
     * @return 结果
     */
    @Override
    public int updateFestival(Festival festival)
    {
        festival.setUpdateTime(DateUtils.getNowDate());
        return festivalMapper.updateFestival(festival);
    }

    /**
     * 批量删除节日
     * 
     * @param ids 需要删除的节日主键
     * @return 结果
     */
    @Override
    public int deleteFestivalByIds(String[] ids)
    {
        return festivalMapper.deleteFestivalByIds(ids);
    }

    /**
     * 删除节日信息
     * 
     * @param id 节日主键
     * @return 结果
     */
    @Override
    public int deleteFestivalById(String id)
    {
        return festivalMapper.deleteFestivalById(id);
    }
}
