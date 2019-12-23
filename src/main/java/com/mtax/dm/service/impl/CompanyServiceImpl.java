package com.mtax.dm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.common.result.ResultCode;
import com.mtax.dm.entity.Company;
import com.mtax.dm.entity.SysUser;
import com.mtax.dm.mapper.CompanyMapper;
import com.mtax.dm.service.CompanyService;
import com.mtax.dm.utils.IdUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    @Override
    public JsonResult addCompany(Company company) {
        company.setId(IdUtils.getId());
        int insert = baseMapper.insert(company);
        if (insert==1){
            return new JsonResult(true);
        }else {
            return new JsonResult(false);
        }
    }

    @Override
    public List<Company> getCompanyListByCanalId(String canalId) {
        return  baseMapper.selectList(Wrappers.<Company>query().lambda().eq(Company::getCanalId,canalId));
    }

    @Override
    public Integer getCompanyPayCount(String canalId) {
        return baseMapper.selectCount(Wrappers.<Company>query().lambda().eq(Company::getCanalId,canalId).eq(Company::getPayType,1));
    }

    @Override
    public JsonResult getCompanyBySearch(String companyName,String start,String end) {
        return new JsonResult(true, ResultCode.SUCCESS,baseMapper.selectList(Wrappers.<Company>query().lambda().eq(Company::getName,companyName).ge(Company::getCreateTime,start).le(Company::getCreateTime,end)));
    }

    @Override
    public JsonResult getCompanyBysearchAndCanal(String start,String end) {
        Subject subject = SecurityUtils.getSubject();
        SysUser loginUser = (SysUser) subject.getSession().getAttribute("loginUser");
        return new JsonResult(true,ResultCode.SUCCESS,baseMapper.selectList(Wrappers.<Company>query().lambda().eq(Company::getCanalId,loginUser.getCanalId()).ge(Company::getCreateTime,start).le(Company::getCreateTime,end)));
    }
}
