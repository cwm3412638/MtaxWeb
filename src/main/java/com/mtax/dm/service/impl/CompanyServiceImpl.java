package com.mtax.dm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.Company;
import com.mtax.dm.mapper.CompanyMapper;
import com.mtax.dm.service.CompanyService;
import com.mtax.dm.utils.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    @Override
    public JsonResult addCompany(Company company) {
        company.setId(IdUtils.getId());
        int insert = baseMapper.insert(company);
        if (insert==1){
            return new JsonResult(true);
        }else {
            return new JsonResult(false);
        }
    }
}
