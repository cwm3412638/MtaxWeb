package com.mtax.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.Industry;

public interface IndustryService extends IService<Industry> {
    JsonResult getIndustryList();
}
