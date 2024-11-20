package com.tide.cultural.mapper;

import java.util.List;
import com.tide.cultural.domain.Festival;

/**
 * 节日Mapper接口
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
public interface FestivalMapper 
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
     * 删除节日
     * 
     * @param id 节日主键
     * @return 结果
     */
    public int deleteFestivalById(String id);

    /**
     * 批量删除节日
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFestivalByIds(String[] ids);
}
