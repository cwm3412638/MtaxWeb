package com.mtax.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.Canal;

public interface CanalService extends IService<Canal> {
    JsonResult addCanal(Canal canal);
}
