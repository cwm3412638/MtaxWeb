package com.mtax.dm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.common.result.ResultCode;
import com.mtax.dm.entity.Canal;
import com.mtax.dm.entity.SysUser;
import com.mtax.dm.mapper.CanalMapper;
import com.mtax.dm.service.CanalService;
import com.mtax.dm.service.SysUserService;
import com.mtax.dm.utils.IdUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

@Service
@Transactional(rollbackFor = Exception.class)
public class CanalServiceImpl extends ServiceImpl<CanalMapper, Canal> implements CanalService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public JsonResult addCanal(Canal canal) {
        //生成id
        canal.setId(IdUtils.getId());
        Subject subject = SecurityUtils.getSubject();
        SysUser loginUser = (SysUser) subject.getSession().getAttribute("loginUser");
        canal.setCreateBy(loginUser.getId());
        int insert = baseMapper.insert(canal);
        if (insert==1){
            SysUser sysUser = new SysUser();
            sysUser.setUserName(canal.getName());
            sysUser.setPassWord(canal.getPassWord()==null?canal.getName():canal.getPassWord());
            sysUser.setType(1);
            sysUser.setCanalId(canal.getId());
            sysUserService.addSysUser(sysUser);
            return new JsonResult(true, ResultCode.SUCCESS);
        }else {
            return new JsonResult(false,ResultCode.COMMON_FAIL);
        }
    }

    @Override
    public JsonResult updateCanal(Canal canal) {
        Canal canal1 = baseMapper.selectById(canal.getId());
        if (!canal1.getPassWord().equals(canal.getPassWord())){
            //修改密码
            SysUser sysUser = sysUserService.finUserByCanalId(canal.getId());
            sysUser.setPassWord(canal.getPassWord());
            sysUserService.updatePassWord(sysUser);
        }
        Subject subject = SecurityUtils.getSubject();
        SysUser loginUser = (SysUser) subject.getSession().getAttribute("loginUser");
        canal.setUpdateBy(loginUser.getId());
        canal.setUpdateBy(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
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
}
