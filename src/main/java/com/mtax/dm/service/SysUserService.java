package com.mtax.dm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    SysUser findUserByUserName(String userName);
    int updatePassWord(SysUser loginUser);
    JsonResult addSysUser(SysUser sysUser);
    SysUser finUserByCanalId(String canalId);
}
