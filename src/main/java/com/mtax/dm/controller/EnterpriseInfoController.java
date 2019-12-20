package com.mtax.dm.controller;

import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.EnterpriseInfo;
import com.mtax.dm.service.EnterpriseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "企业信息")
@RestController
@RequestMapping("/info")
public class EnterpriseInfoController {
    @Autowired
    private EnterpriseInfoService enterpriseInfoService;
    @PostMapping("/addEnterpriseInfo")
    @ApiOperation(value = "添加企业信息", notes="添加企业信息")
    public JsonResult addEnterpriseInfo(@RequestBody EnterpriseInfo enterpriseInfo){
        return enterpriseInfoService.addEnterpriseInfo(enterpriseInfo);
    }
}
