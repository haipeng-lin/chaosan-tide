package com.tide.cultural.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tide.common.annotation.Excel;
import com.tide.common.core.domain.BaseEntity;

/**
 * 文化标签对象 tide_cultural_tag
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
public class CulturalTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderNum;

    /** 显示状态（1-显示，2-不显示） */
    @Excel(name = "显示状态", readConverterExp = "1=-显示，2-不显示")
    private Long status;

    /** 删除标志（1-未删除，2-已删除） */
    private Long delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTagName(String tagName) 
    {
        this.tagName = tagName;
    }

    public String getTagName() 
    {
        return tagName;
    }
    public void setOrderNum(Long orderNum) 
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum() 
    {
        return orderNum;
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
            .append("tagName", getTagName())
            .append("orderNum", getOrderNum())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
