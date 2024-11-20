package com.tide.cultural.service.impl;

import java.util.List;
import com.tide.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tide.cultural.mapper.CulturalTopicContentMapper;
import com.tide.cultural.domain.CulturalTopicContent;
import com.tide.cultural.service.ICulturalTopicContentService;

/**
 * 文化专题内容Service业务层处理
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
@Service
public class CulturalTopicContentServiceImpl implements ICulturalTopicContentService 
{
    @Autowired
    private CulturalTopicContentMapper culturalTopicContentMapper;

    /**
     * 查询文化专题内容
     * 
     * @param id 文化专题内容主键
     * @return 文化专题内容
     */
    @Override
    public CulturalTopicContent selectCulturalTopicContentById(String id)
    {
        return culturalTopicContentMapper.selectCulturalTopicContentById(id);
    }

    /**
     * 查询文化专题内容列表
     * 
     * @param culturalTopicContent 文化专题内容
     * @return 文化专题内容
     */
    @Override
    public List<CulturalTopicContent> selectCulturalTopicContentList(CulturalTopicContent culturalTopicContent)
    {
        return culturalTopicContentMapper.selectCulturalTopicContentList(culturalTopicContent);
    }

    /**
     * 新增文化专题内容
     * 
     * @param culturalTopicContent 文化专题内容
     * @return 结果
     */
    @Override
    public int insertCulturalTopicContent(CulturalTopicContent culturalTopicContent)
    {
        culturalTopicContent.setCreateTime(DateUtils.getNowDate());
        return culturalTopicContentMapper.insertCulturalTopicContent(culturalTopicContent);
    }

    /**
     * 修改文化专题内容
     * 
     * @param culturalTopicContent 文化专题内容
     * @return 结果
     */
    @Override
    public int updateCulturalTopicContent(CulturalTopicContent culturalTopicContent)
    {
        culturalTopicContent.setUpdateTime(DateUtils.getNowDate());
        return culturalTopicContentMapper.updateCulturalTopicContent(culturalTopicContent);
    }

    /**
     * 批量删除文化专题内容
     * 
     * @param ids 需要删除的文化专题内容主键
     * @return 结果
     */
    @Override
    public int deleteCulturalTopicContentByIds(String[] ids)
    {
        return culturalTopicContentMapper.deleteCulturalTopicContentByIds(ids);
    }

    /**
     * 删除文化专题内容信息
     * 
     * @param id 文化专题内容主键
     * @return 结果
     */
    @Override
    public int deleteCulturalTopicContentById(String id)
    {
        return culturalTopicContentMapper.deleteCulturalTopicContentById(id);
    }
}
