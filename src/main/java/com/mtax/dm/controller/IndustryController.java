package com.mtax.dm.controller;

import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.service.IndustryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "行业信息")
@RestController
@RequestMapping("/industry")
@Slf4j
public class IndustryController {
    @Autowired
    private IndustryService industryService;
    @GetMapping("/getIndustryList")
    @ApiOperation(value = "查询行业信息列表", notes="查询行业信息列表")
    public JsonResult getIndustryList(){
        return industryService.getIndustryList();
    }
}
