package com.mtax.dm.controller;

import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.Company;
import com.mtax.dm.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "企业信息")
@RestController
@RequestMapping("/info")
@Slf4j
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/addEnterpriseInfo")
    @ApiOperation(value = "添加企业信息", notes = "添加企业信息")
    public JsonResult addCompany(@ApiParam(name = "企业信息实体", value = "company", required = true) @RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @GetMapping("/getCompanyBySearch")
    @ApiOperation(value = "条件查询所有公司信息", notes = "条件查询所有公司信息")
    public JsonResult getCompanyBySearch(@ApiParam(value = "companyName", name = "公司名称", required = false) @RequestParam(required = false) String companyName) {
        return companyService.getCompanyBySearch(companyName);
    }
    @GetMapping("/getCompanyBysearchAndCanal")
    @ApiOperation(value = "渠道后台登录条件查询", notes = "渠道后台登录条件查询")
    public JsonResult getCompanyBysearchAndCanal(){
        return companyService.getCompanyBysearchAndCanal();
    }
}
