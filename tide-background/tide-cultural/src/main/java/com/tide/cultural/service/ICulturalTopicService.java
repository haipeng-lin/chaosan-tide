package com.tide.cultural.service;

import java.util.List;
import com.tide.cultural.domain.CulturalTopic;

/**
 * 文化专题Service接口
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
public interface ICulturalTopicService 
{
    /**
     * 查询文化专题
     * 
     * @param id 文化专题主键
     * @return 文化专题
     */
    public CulturalTopic selectCulturalTopicById(Long id);

    /**
     * 查询文化专题列表
     * 
     * @param culturalTopic 文化专题
     * @return 文化专题集合
     */
    public List<CulturalTopic> selectCulturalTopicList(CulturalTopic culturalTopic);

    /**
     * 新增文化专题
     * 
     * @param culturalTopic 文化专题
     * @return 结果
     */
    public int insertCulturalTopic(CulturalTopic culturalTopic);

    /**
     * 修改文化专题
     * 
     * @param culturalTopic 文化专题
     * @return 结果
     */
    public int updateCulturalTopic(CulturalTopic culturalTopic);

    /**
     * 批量删除文化专题
     * 
     * @param ids 需要删除的文化专题主键集合
     * @return 结果
     */
    public int deleteCulturalTopicByIds(Long[] ids);

    /**
     * 删除文化专题信息
     * 
     * @param id 文化专题主键
     * @return 结果
     */
    public int deleteCulturalTopicById(Long id);
}
