package com.mtax.dm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.common.result.ResultCode;
import com.mtax.dm.entity.Industry;
import com.mtax.dm.mapper.IndustryMapper;
import com.mtax.dm.service.IndustryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class IndustryServiceImpl extends ServiceImpl<IndustryMapper, Industry> implements IndustryService {
    @Override
    public JsonResult getIndustryList() {
        List<Industry> industries = baseMapper.selectList(Wrappers.query());
        return new JsonResult<>(true, ResultCode.SUCCESS,industries);
    }
}
