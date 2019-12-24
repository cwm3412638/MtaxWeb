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

import java.util.ArrayList;
import java.util.Date;
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
        canal.setId(IdUtils.getUUID());
        Subject subject = SecurityUtils.getSubject();
        SysUser loginUser = (SysUser) subject.getSession().getAttribute("loginUser");
        canal.setCreateBy(loginUser.getId());
        int insert = baseMapper.insert(canal);
        if (insert == 1) {
            return new JsonResult(true, ResultCode.SUCCESS);
        } else {
            return new JsonResult(false, ResultCode.COMMON_FAIL);
        }
    }

    @Override
    public JsonResult updateCanal(Canal canal) {
        Subject subject = SecurityUtils.getSubject();
        SysUser loginUser = (SysUser) subject.getSession().getAttribute("loginUser");
        canal.setUpdateBy(loginUser.getId());
        canal.setUpdateTime(new Date());
        int i = baseMapper.updateById(canal);
        if (i == 1) {
            return new JsonResult(true, ResultCode.SUCCESS);
        } else {
            return new JsonResult(false, ResultCode.COMMON_FAIL);
        }
    }

    @Override
    public JsonResult delCanalById(String id) {
        int i = baseMapper.deleteById(id);
        if (i == 1) {
            return new JsonResult(true, ResultCode.SUCCESS);
        } else {
            return new JsonResult(false, ResultCode.COMMON_FAIL);
        }
    }

    @Override
    public JsonResult getAllCount() {
        return new JsonResult<>(true, ResultCode.SUCCESS, baseMapper.selectCount(Wrappers.query()));
    }

    @Override
    public JsonResult getCanalList(String province, String city) {
        if (null == province || null == city || province.equals("") || city.equals("")) {
            return new JsonResult(true, ResultCode.SUCCESS, this.list());
        }
        if (null != province) {
            if (null == city) {
                List<Canal> c1 = this.list(Wrappers.<Canal>query()
                        .lambda()
                        .eq(Canal::getProvince, province));
                return new JsonResult(true, ResultCode.SUCCESS, c1);
            }
            List<Canal> c2 = this.list(Wrappers.<Canal>query()
                    .lambda()
                    .eq(Canal::getProvince, province)
                    .eq(Canal::getCity, city)
            );
            return new JsonResult(true, ResultCode.SUCCESS, c2);
        }
        return null;
    }

    @Override
    public JsonResult getCanalCountList(String province, String city) {
        AtomicReference<Integer> CompanyCount = new AtomicReference<>(0);
        AtomicReference<Integer> payCount = new AtomicReference<>(0);
        ArrayList<CanalVo> objects = Lists.newArrayList();
        List<Canal> canals;
        if (null != province) {
            canals = this.list(Wrappers.<Canal>query().lambda().eq(Canal::getProvince, province));
            if (null != city) {
                canals = this.list(Wrappers.<Canal>query()
                        .lambda()
                        .eq(Canal::getProvince, province)
                        .eq(Canal::getCity, city)
                );
            }
        } else {
            canals = this.list();
        }
        canals.forEach(item -> {
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

        return new JsonResult(true, ResultCode.SUCCESS, statisticsVo);
    }
}
