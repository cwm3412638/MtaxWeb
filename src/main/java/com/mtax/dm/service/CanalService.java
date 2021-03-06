package com.mtax.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.Canal;

public interface CanalService extends IService<Canal> {
    JsonResult addCanal(Canal canal);
    JsonResult updateCanal(Canal canal);
    JsonResult delCanalById(String id);
    JsonResult getAllCount();
    JsonResult getCanalList(String province,String city);
    JsonResult getCanalCountList(String province,String city);
}
