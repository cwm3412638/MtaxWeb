package com.mtax.dm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 企业信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("enterprise_info")
@Accessors(chain = true)
@ApiModel(value="enterprise_info对象", description="企业信息实体")
public class EnterpriseInfo {
    @TableId
    @ApiModelProperty(value ="主键")
    private String id;
    @ApiModelProperty(value ="企业名称")
    private String companyName;
    @ApiModelProperty(value ="联系人")
    private String contactPerson;
    @ApiModelProperty(value ="联系电话")
    private String contactNumber;
    @ApiModelProperty(value ="行业人数")
    private String IndustryNumber;
    @ApiModelProperty(value ="省")
    private String province;
    @ApiModelProperty(value ="市")
    private String city;
    @ApiModelProperty(value ="区")
    private String area;
    @ApiModelProperty(value ="详细地址")
    private String address;
    @ApiModelProperty(value ="所属行业Id")
    private String industryId;
    @ApiModelProperty(value ="创建时间")
    private LocalDateTime createTime;
}
