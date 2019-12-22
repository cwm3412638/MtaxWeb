package com.mtax.dm.entity.vo;

import com.mtax.dm.entity.Canal;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ApiModel(value="CanalVo对象", description="渠道信息统计扩展实体")
public class CanalVo extends Canal {
    private Integer CompanyCount;
    private Integer payCount;
}
