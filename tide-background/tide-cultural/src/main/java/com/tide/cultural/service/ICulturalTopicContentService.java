package com.tide.cultural.service;

import java.util.List;
import com.tide.cultural.domain.CulturalTopicContent;

/**
 * 文化专题内容Service接口
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
public interface ICulturalTopicContentService 
{
    /**
     * 查询文化专题内容
     * 
     * @param id 文化专题内容主键
     * @return 文化专题内容
     */
    public CulturalTopicContent selectCulturalTopicContentById(String id);

    /**
     * 查询文化专题内容列表
     * 
     * @param culturalTopicContent 文化专题内容
     * @return 文化专题内容集合
     */
    public List<CulturalTopicContent> selectCulturalTopicContentList(CulturalTopicContent culturalTopicContent);

    /**
     * 新增文化专题内容
     * 
     * @param culturalTopicContent 文化专题内容
     * @return 结果
     */
    public int insertCulturalTopicContent(CulturalTopicContent culturalTopicContent);

    /**
     * 修改文化专题内容
     * 
     * @param culturalTopicContent 文化专题内容
     * @return 结果
     */
    public int updateCulturalTopicContent(CulturalTopicContent culturalTopicContent);

    /**
     * 批量删除文化专题内容
     * 
     * @param ids 需要删除的文化专题内容主键集合
     * @return 结果
     */
    public int deleteCulturalTopicContentByIds(String[] ids);

    /**
     * 删除文化专题内容信息
     * 
     * @param id 文化专题内容主键
     * @return 结果
     */
    public int deleteCulturalTopicContentById(String id);
}
