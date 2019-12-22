package com.mtax.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.Company;
import java.util.List;

public interface CompanyService extends IService<Company> {
    JsonResult addCompany(Company company);
    List<Company> getCompanyListByCanalId(String canalId);
    Integer getCompanyPayCount(String canalId);
    JsonResult getCompanyBySearch(String companyName);
    JsonResult getCompanyBysearchAndCanal();
}
