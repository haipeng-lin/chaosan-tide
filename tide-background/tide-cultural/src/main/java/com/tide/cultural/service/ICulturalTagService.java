package com.tide.cultural.service;

import java.util.List;
import com.tide.cultural.domain.CulturalTag;

/**
 * 文化标签Service接口
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
public interface ICulturalTagService 
{
    /**
     * 查询文化标签
     * 
     * @param id 文化标签主键
     * @return 文化标签
     */
    public CulturalTag selectCulturalTagById(Long id);

    /**
     * 查询文化标签列表
     * 
     * @param culturalTag 文化标签
     * @return 文化标签集合
     */
    public List<CulturalTag> selectCulturalTagList(CulturalTag culturalTag);

    /**
     * 新增文化标签
     * 
     * @param culturalTag 文化标签
     * @return 结果
     */
    public int insertCulturalTag(CulturalTag culturalTag);

    /**
     * 修改文化标签
     * 
     * @param culturalTag 文化标签
     * @return 结果
     */
    public int updateCulturalTag(CulturalTag culturalTag);

    /**
     * 批量删除文化标签
     * 
     * @param ids 需要删除的文化标签主键集合
     * @return 结果
     */
    public int deleteCulturalTagByIds(Long[] ids);

    /**
     * 删除文化标签信息
     * 
     * @param id 文化标签主键
     * @return 结果
     */
    public int deleteCulturalTagById(Long id);
}
