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
@TableName("sys_industry")
@Accessors(chain = true)
@Slf4j
@ApiModel(value="sys_industry对象", description="企业所属行业")
public class Industry {
    @TableId
    @ApiModelProperty(value ="主键")
    private String id;
    @ApiModelProperty(value ="行业名称")
    private String industryName;
    @ApiModelProperty(value ="逻辑删除")
    private Integer delFlg;
    @ApiModelProperty(value ="创建时间")
    private Date createTime;
}
