package com.tide.cultural.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tide.common.annotation.Excel;
import com.tide.common.core.domain.BaseEntity;

/**
 * 文化专题内容对象 tide_cultural_topic_content
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
public class CulturalTopicContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 文化专题id */
    @Excel(name = "文化专题id")
    private String culturalTopicId;

    /** 标签id1 */
    @Excel(name = "标签id1")
    private String tagId1;

    /** 标签id2 */
    @Excel(name = "标签id2")
    private String tagId2;

    /** 标签id3 */
    @Excel(name = "标签id3")
    private String tagId3;

    /** 封面 */
    @Excel(name = "封面")
    private String coverImg;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 1-置顶，0-不置顶 */
    @Excel(name = "1-置顶，0-不置顶")
    private Long isTop;

    /** 1-推荐，0-不推荐 */
    @Excel(name = "1-推荐，0-不推荐")
    private Long isRecommend;

    /** 显示状态（1-显示，2-不显示） */
    @Excel(name = "显示状态", readConverterExp = "1=-显示，2-不显示")
    private Long status;

    /** 删除标志（1-未删除，2-已删除） */
    private Long delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCulturalTopicId(String culturalTopicId) 
    {
        this.culturalTopicId = culturalTopicId;
    }

    public String getCulturalTopicId() 
    {
        return culturalTopicId;
    }
    public void setTagId1(String tagId1) 
    {
        this.tagId1 = tagId1;
    }

    public String getTagId1() 
    {
        return tagId1;
    }
    public void setTagId2(String tagId2) 
    {
        this.tagId2 = tagId2;
    }

    public String getTagId2() 
    {
        return tagId2;
    }
    public void setTagId3(String tagId3) 
    {
        this.tagId3 = tagId3;
    }

    public String getTagId3() 
    {
        return tagId3;
    }
    public void setCoverImg(String coverImg) 
    {
        this.coverImg = coverImg;
    }

    public String getCoverImg() 
    {
        return coverImg;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setIsTop(Long isTop) 
    {
        this.isTop = isTop;
    }

    public Long getIsTop() 
    {
        return isTop;
    }
    public void setIsRecommend(Long isRecommend) 
    {
        this.isRecommend = isRecommend;
    }

    public Long getIsRecommend() 
    {
        return isRecommend;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("culturalTopicId", getCulturalTopicId())
            .append("tagId1", getTagId1())
            .append("tagId2", getTagId2())
            .append("tagId3", getTagId3())
            .append("coverImg", getCoverImg())
            .append("content", getContent())
            .append("isTop", getIsTop())
            .append("isRecommend", getIsRecommend())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
