package com.mtax.dm.controller;

import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.Canal;
import com.mtax.dm.service.CanalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "渠道信息")
@RestController
@RequestMapping("/canal")
@Slf4j
public class CanalController {
    @Autowired
    private CanalService canalService;
    @ApiOperation(value = "添加渠道信息", notes="添加渠道信息")
    @PostMapping("/addCanal")
    public JsonResult addCanal(@ApiParam(name = "渠道信息实体",value = "canal",required = true) @RequestBody Canal canal){
        return canalService.addCanal(canal);
    }

}
