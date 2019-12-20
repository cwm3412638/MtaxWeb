package com.mtax.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.Company;

public interface CompanyService extends IService<Company> {
    JsonResult addCompany(Company company);
}
