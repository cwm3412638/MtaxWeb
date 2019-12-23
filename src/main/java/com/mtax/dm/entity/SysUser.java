package com.mtax.dm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
@Accessors(chain = true)
@Slf4j
@ApiModel(value="sys_user对象", description="用户信息表")
public class SysUser {
    @TableId
    @ApiModelProperty(value ="主键")
    private String id;
    @ApiModelProperty(value ="用户名")
    private String userName;
    @ApiModelProperty(value ="密码")
    private String passWord;
    @ApiModelProperty(value ="状态(0-正常，1-禁用)")
    private Integer status;
    @ApiModelProperty(value ="逻辑删除标记（0-正常，1-删除）")
    private Integer delFlag;
    @ApiModelProperty(value ="创建者")
    private String createBy;
    @ApiModelProperty(value ="创建时间")
    private Date createTime;
    @ApiModelProperty(value ="修改者")
    private String updateBy;
    @ApiModelProperty(value ="修改时间")
    private Date updateTime;
    @ApiModelProperty(value ="备注")
    private String remark;
    @ApiModelProperty(value ="用户类型")
    private Integer type;
    @ApiModelProperty(value ="渠道id")
    private String canalId;

}
