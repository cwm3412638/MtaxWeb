package com.mtax.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.EnterpriseInfo;

public interface EnterpriseInfoService extends IService<EnterpriseInfo> {
    JsonResult addEnterpriseInfo(EnterpriseInfo enterpriseInfo);
}
