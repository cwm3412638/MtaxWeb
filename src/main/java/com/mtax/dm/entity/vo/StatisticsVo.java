package com.mtax.dm.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Data
@Slf4j
@ApiModel(value="statisticsVo对象", description="总渠道信息统计扩展实体")
public class StatisticsVo {
    private List<CanalVo> canalVos;
    private Integer count;
    private Integer payCount;
}
