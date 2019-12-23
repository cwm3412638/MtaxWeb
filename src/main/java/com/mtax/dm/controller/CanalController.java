package com.mtax.dm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.Canal;
import com.mtax.dm.service.CanalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "渠道信息")
@RestController
@RequestMapping("/canal")
@Slf4j
public class CanalController {
    @Autowired
    private CanalService canalService;

    @ApiOperation(value = "添加渠道信息", notes = "添加渠道信息")
    @PostMapping("/addCanal")
    public JsonResult addCanal(@ApiParam(name = "渠道信息实体", value = "canal", required = true) @RequestBody Canal canal) {
        return canalService.addCanal(canal);
    }

    @PostMapping("/updateCanal")
    @ApiOperation(value = "修改渠道信息", notes = "修改渠道信息")
    public JsonResult updateCanal(@ApiParam(name = "渠道信息实体", value = "canal", required = true) @RequestBody Canal canal) {
        return canalService.updateCanal(canal);
    }
    @DeleteMapping("/delCanalById")
    @ApiOperation(value = "根据id删除渠道信息", notes = "根据id删除渠道信息")
    public JsonResult delCanalById(@ApiParam(name = "渠道id", value = "id", required = true)@RequestParam("id") String id){
        return canalService.delCanalById(id);
    }
    @GetMapping("/getAllCount")
    @ApiOperation(value = "查询所有渠道总数", notes = "查询所有渠道总数")
    public JsonResult getAllCount(){
          return canalService.getAllCount();
    }
    @GetMapping("/getCanalList")
    @ApiOperation(value = "查询所有渠道信息", notes = "查询所有渠道信息")
    public JsonResult getCanalList(@RequestBody(required = false) Canal canal){
        return canalService.getCanalList(canal);
    }
    @GetMapping("/getCanalCountList")
    @ApiOperation(value = "查询所有渠道信息和渠道下的企业数", notes = "查询所有渠道信息")
    public JsonResult getCanalCountList(@RequestBody Canal canal){
        return canalService.getCanalCountList(canal);
    }

}
