package com.tide.cultural.service.impl;

import java.util.List;
import com.tide.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tide.cultural.mapper.CulturalTagMapper;
import com.tide.cultural.domain.CulturalTag;
import com.tide.cultural.service.ICulturalTagService;

/**
 * 文化标签Service业务层处理
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
@Service
public class CulturalTagServiceImpl implements ICulturalTagService 
{
    @Autowired
    private CulturalTagMapper culturalTagMapper;

    /**
     * 查询文化标签
     * 
     * @param id 文化标签主键
     * @return 文化标签
     */
    @Override
    public CulturalTag selectCulturalTagById(Long id)
    {
        return culturalTagMapper.selectCulturalTagById(id);
    }

    /**
     * 查询文化标签列表
     * 
     * @param culturalTag 文化标签
     * @return 文化标签
     */
    @Override
    public List<CulturalTag> selectCulturalTagList(CulturalTag culturalTag)
    {
        return culturalTagMapper.selectCulturalTagList(culturalTag);
    }

    /**
     * 新增文化标签
     * 
     * @param culturalTag 文化标签
     * @return 结果
     */
    @Override
    public int insertCulturalTag(CulturalTag culturalTag)
    {
        culturalTag.setCreateTime(DateUtils.getNowDate());
        return culturalTagMapper.insertCulturalTag(culturalTag);
    }

    /**
     * 修改文化标签
     * 
     * @param culturalTag 文化标签
     * @return 结果
     */
    @Override
    public int updateCulturalTag(CulturalTag culturalTag)
    {
        culturalTag.setUpdateTime(DateUtils.getNowDate());
        return culturalTagMapper.updateCulturalTag(culturalTag);
    }

    /**
     * 批量删除文化标签
     * 
     * @param ids 需要删除的文化标签主键
     * @return 结果
     */
    @Override
    public int deleteCulturalTagByIds(Long[] ids)
    {
        return culturalTagMapper.deleteCulturalTagByIds(ids);
    }

    /**
     * 删除文化标签信息
     * 
     * @param id 文化标签主键
     * @return 结果
     */
    @Override
    public int deleteCulturalTagById(Long id)
    {
        return culturalTagMapper.deleteCulturalTagById(id);
    }
}
