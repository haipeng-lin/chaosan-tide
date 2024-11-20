package com.tide.cultural.service;

import java.util.List;
import com.tide.cultural.domain.Festival;

/**
 * 节日Service接口
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
public interface IFestivalService 
{
    /**
     * 查询节日
     * 
     * @param id 节日主键
     * @return 节日
     */
    public Festival selectFestivalById(String id);

    /**
     * 查询节日列表
     * 
     * @param festival 节日
     * @return 节日集合
     */
    public List<Festival> selectFestivalList(Festival festival);

    /**
     * 新增节日
     * 
     * @param festival 节日
     * @return 结果
     */
    public int insertFestival(Festival festival);

    /**
     * 修改节日
     * 
     * @param festival 节日
     * @return 结果
     */
    public int updateFestival(Festival festival);

    /**
     * 批量删除节日
     * 
     * @param ids 需要删除的节日主键集合
     * @return 结果
     */
    public int deleteFestivalByIds(String[] ids);

    /**
     * 删除节日信息
     * 
     * @param id 节日主键
     * @return 结果
     */
    public int deleteFestivalById(String id);
}
