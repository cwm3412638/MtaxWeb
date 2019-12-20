package com.mtax.dm.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.EnterpriseInfo;
import com.mtax.dm.mapper.EnterpriseInfoMapper;
import com.mtax.dm.service.EnterpriseInfoService;
import com.mtax.dm.utils.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class EnterpriseInfoServiceImpl extends ServiceImpl<EnterpriseInfoMapper, EnterpriseInfo> implements EnterpriseInfoService {
    @Override
    public JsonResult addEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
        enterpriseInfo.setId(IdUtils.getId());
        int insert = baseMapper.insert(enterpriseInfo);
        if (insert==1){
            return new JsonResult(true);
        }else {
            return new JsonResult(false);
        }
    }
}
