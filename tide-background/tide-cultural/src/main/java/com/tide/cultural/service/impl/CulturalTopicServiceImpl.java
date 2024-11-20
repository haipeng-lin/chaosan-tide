package com.tide.cultural.service.impl;

import java.util.List;
import com.tide.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tide.cultural.mapper.CulturalTopicMapper;
import com.tide.cultural.domain.CulturalTopic;
import com.tide.cultural.service.ICulturalTopicService;

/**
 * 文化专题Service业务层处理
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
@Service
public class CulturalTopicServiceImpl implements ICulturalTopicService 
{
    @Autowired
    private CulturalTopicMapper culturalTopicMapper;

    /**
     * 查询文化专题
     * 
     * @param id 文化专题主键
     * @return 文化专题
     */
    @Override
    public CulturalTopic selectCulturalTopicById(Long id)
    {
        return culturalTopicMapper.selectCulturalTopicById(id);
    }

    /**
     * 查询文化专题列表
     * 
     * @param culturalTopic 文化专题
     * @return 文化专题
     */
    @Override
    public List<CulturalTopic> selectCulturalTopicList(CulturalTopic culturalTopic)
    {
        return culturalTopicMapper.selectCulturalTopicList(culturalTopic);
    }

    /**
     * 新增文化专题
     * 
     * @param culturalTopic 文化专题
     * @return 结果
     */
    @Override
    public int insertCulturalTopic(CulturalTopic culturalTopic)
    {
        culturalTopic.setCreateTime(DateUtils.getNowDate());
        return culturalTopicMapper.insertCulturalTopic(culturalTopic);
    }

    /**
     * 修改文化专题
     * 
     * @param culturalTopic 文化专题
     * @return 结果
     */
    @Override
    public int updateCulturalTopic(CulturalTopic culturalTopic)
    {
        culturalTopic.setUpdateTime(DateUtils.getNowDate());
        return culturalTopicMapper.updateCulturalTopic(culturalTopic);
    }

    /**
     * 批量删除文化专题
     * 
     * @param ids 需要删除的文化专题主键
     * @return 结果
     */
    @Override
    public int deleteCulturalTopicByIds(Long[] ids)
    {
        return culturalTopicMapper.deleteCulturalTopicByIds(ids);
    }

    /**
     * 删除文化专题信息
     * 
     * @param id 文化专题主键
     * @return 结果
     */
    @Override
    public int deleteCulturalTopicById(Long id)
    {
        return culturalTopicMapper.deleteCulturalTopicById(id);
    }
}
