package com.mtax.dm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.common.result.ResultCode;
import com.mtax.dm.entity.Canal;
import com.mtax.dm.entity.Company;
import com.mtax.dm.entity.SysUser;
import com.mtax.dm.entity.vo.CanalVo;
import com.mtax.dm.entity.vo.StatisticsVo;
import com.mtax.dm.mapper.CanalMapper;
import com.mtax.dm.service.CanalService;
import com.mtax.dm.service.CompanyService;
import com.mtax.dm.utils.IdUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional(rollbackFor = Exception.class)
public class CanalServiceImpl extends ServiceImpl<CanalMapper, Canal> implements CanalService {
    @Autowired
    private CompanyService companyService;
    @Override
    public JsonResult addCanal(Canal canal) {
        //生成id
        canal.setId(IdUtils.getId());
        Subject subject = SecurityUtils.getSubject();
        SysUser loginUser = (SysUser) subject.getSession().getAttribute("loginUser");
        canal.setCreateBy(loginUser.getId());
        int insert = baseMapper.insert(canal);
        if (insert==1){
            return new JsonResult(true, ResultCode.SUCCESS);
        }else {
            return new JsonResult(false,ResultCode.COMMON_FAIL);
        }
    }

    @Override
    public JsonResult updateCanal(Canal canal) {
        Subject subject = SecurityUtils.getSubject();
        SysUser loginUser = (SysUser) subject.getSession().getAttribute("loginUser");
        canal.setUpdateBy(loginUser.getId());
        canal.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int i = baseMapper.updateById(canal);
        if (i==1){
            return new JsonResult(true,ResultCode.SUCCESS);
        }else {
            return new JsonResult(false,ResultCode.COMMON_FAIL);
        }
    }

    @Override
    public JsonResult delCanalById(String id) {
        int i = baseMapper.deleteById(id);
        if (i==1){
            return new JsonResult(true,ResultCode.SUCCESS);
        }else {
            return new JsonResult(false,ResultCode.COMMON_FAIL);
        }
    }

    @Override
    public JsonResult getAllCount() {
        return new JsonResult<>(true,ResultCode.SUCCESS,baseMapper.selectCount(Wrappers.query()));
    }

    @Override
    public JsonResult getCanalList(Canal canal) {
        return new JsonResult(true,ResultCode.SUCCESS,baseMapper.selectList(Wrappers.<Canal>query().lambda().eq(Canal::getProvince,canal.getProvince()).eq(Canal::getCity,canal.getCity()).eq(Canal::getArea,canal.getArea())));
    }

    @Override
    public JsonResult getCanalCountList(Canal canal) {
      AtomicReference<Integer> CompanyCount= new AtomicReference<>(0);
        AtomicReference<Integer> payCount= new AtomicReference<>(0);
        ArrayList<CanalVo> objects = Lists.newArrayList();
        List<Canal> canals = baseMapper.selectList(Wrappers.<Canal>query().lambda().eq(Canal::getProvince,canal.getProvince()).eq(Canal::getArea,canal.getArea()).eq(Canal::getCity,canal.getCity()));
        canals.stream().forEach(item->{
            List<Company> companyListByCanalId = companyService.getCompanyListByCanalId(item.getId());
            CompanyCount.updateAndGet(v -> v + companyListByCanalId.size());
            Integer companyPayCount = companyService.getCompanyPayCount(item.getId());
            payCount.updateAndGet(v -> v + companyPayCount);
            CanalVo canalVo = new CanalVo();
            canalVo.setName(item.getName());
            canalVo.setCompanyCount(companyListByCanalId.size());
            canalVo.setPayCount(companyPayCount);
            objects.add(canalVo);
        });
        StatisticsVo statisticsVo = new StatisticsVo();
        statisticsVo.setCanalVos(objects);
        statisticsVo.setCount(CompanyCount.get());
        statisticsVo.setPayCount(payCount.get());

        return new JsonResult(true,ResultCode.SUCCESS,statisticsVo);
    }
}
