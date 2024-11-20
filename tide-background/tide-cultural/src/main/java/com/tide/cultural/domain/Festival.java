package com.tide.cultural.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tide.common.annotation.Excel;
import com.tide.common.core.domain.BaseEntity;

/**
 * 节日对象 tide_festival
 * 
 * @author haipeng-lin
 * @date 2024-11-20
 */
public class Festival extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 节日类别id */
    @Excel(name = "节日类别id")
    private String festivalCategoryId;

    /** 节日名称 */
    @Excel(name = "节日名称")
    private String name;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 节日时间 */
    @Excel(name = "节日时间")
    private String time;

    /** 举办地点 */
    @Excel(name = "举办地点")
    private String location;

    /** 封面 */
    @Excel(name = "封面")
    private String coverImg;

    /** 主办单位 */
    @Excel(name = "主办单位")
    private String organizer;

    /** 1-置顶，0-不置顶 */
    @Excel(name = "1-置顶，0-不置顶")
    private Long isTop;

    /** 1-推荐，0-不推荐 */
    @Excel(name = "1-推荐，0-不推荐")
    private Long isRecommend;

    /** 审批状态（1-未审批，2-审批成功，3-审批失败） */
    @Excel(name = "审批状态", readConverterExp = "1=-未审批，2-审批成功，3-审批失败")
    private Long approvalStat;

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
    public void setFestivalCategoryId(String festivalCategoryId) 
    {
        this.festivalCategoryId = festivalCategoryId;
    }

    public String getFestivalCategoryId() 
    {
        return festivalCategoryId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setTime(String time) 
    {
        this.time = time;
    }

    public String getTime() 
    {
        return time;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setCoverImg(String coverImg) 
    {
        this.coverImg = coverImg;
    }

    public String getCoverImg() 
    {
        return coverImg;
    }
    public void setOrganizer(String organizer) 
    {
        this.organizer = organizer;
    }

    public String getOrganizer() 
    {
        return organizer;
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
    public void setApprovalStat(Long approvalStat) 
    {
        this.approvalStat = approvalStat;
    }

    public Long getApprovalStat() 
    {
        return approvalStat;
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
            .append("festivalCategoryId", getFestivalCategoryId())
            .append("name", getName())
            .append("description", getDescription())
            .append("time", getTime())
            .append("location", getLocation())
            .append("coverImg", getCoverImg())
            .append("organizer", getOrganizer())
            .append("isTop", getIsTop())
            .append("isRecommend", getIsRecommend())
            .append("approvalStat", getApprovalStat())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
