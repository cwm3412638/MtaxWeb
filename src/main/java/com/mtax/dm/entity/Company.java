package com.mtax.dm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;


/**
 * 企业信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_company")
@Accessors(chain = true)
@Slf4j
@ApiModel(value="sys_company对象", description="企业信息实体")
public class Company {
    @TableId
    @ApiModelProperty(value ="主键")
    private String id;
    @ApiModelProperty(value ="企业来源渠道Id")
    private String canalId;
    @ApiModelProperty(value ="企业/公司名称")
    private String name;
    @ApiModelProperty(value ="联系人")
    private String contactMan;
    @ApiModelProperty(value ="联系电话")
    private String contactPhone;
    @ApiModelProperty(value ="企业人数")
    private String countMan;
    @ApiModelProperty(value ="省")
    private String province;
    @ApiModelProperty(value ="市")
    private String city;
    @ApiModelProperty(value ="区")
    private String area;
    @ApiModelProperty(value ="详细地址")
    private String companyAddress;
    @ApiModelProperty(value ="所属行业Id")
    private String industryId;
    @ApiModelProperty(value ="创建时间")
    private Timestamp createTime;
}
