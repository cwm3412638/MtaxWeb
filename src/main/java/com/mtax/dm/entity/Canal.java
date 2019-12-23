package com.mtax.dm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_canal")
@Accessors(chain = true)
@Slf4j
@ApiModel(value="sys_canal对象", description="渠道信息表")
public class Canal {
    @TableId
    @ApiModelProperty(value ="主键")
    private String id;
    @ApiModelProperty(value ="渠道名称")
    private String name;
    @ApiModelProperty(value ="省")
    private String province;
    @ApiModelProperty(value ="市")
    private String city;
    @ApiModelProperty(value ="区")
    private String area;
    @ApiModelProperty(value ="创建时间")
    private Timestamp createTime;
    @ApiModelProperty(value ="创建人")
    private String createBy;
    @ApiModelProperty(value ="修改时间")
    private Timestamp updateTime;
    @ApiModelProperty(value ="修改人")
    private String updateBy;
    @ApiModelProperty(value ="备注")
    private String remark;
    @ApiModelProperty(value ="逻辑删除")
    @TableLogic
    private Integer delFlg;
}
