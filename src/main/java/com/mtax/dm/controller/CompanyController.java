package com.mtax.dm.controller;

import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.Company;
import com.mtax.dm.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "企业信息")
@RestController
@RequestMapping("/info")
@Slf4j
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @PostMapping("/addEnterpriseInfo")
    @ApiOperation(value = "添加企业信息", notes="添加企业信息")
    public JsonResult addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }
}
