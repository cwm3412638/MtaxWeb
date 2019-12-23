package com.mtax.dm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.common.result.ResultCode;
import com.mtax.dm.entity.SysUser;
import com.mtax.dm.mapper.SysUserMapper;
import com.mtax.dm.service.SysUserService;
import com.mtax.dm.utils.IdUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public SysUser findUserByUserName(String userName) {
        return baseMapper.selectOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUserName,userName));
    }

    @Override
    public int updatePassWord(SysUser loginUser) {
        loginUser.setPassWord(new SimpleHash("md5", loginUser.getPassWord(), ByteSource.Util.bytes(loginUser.getUserName()), 2).toHex());
        return baseMapper.updateById(loginUser);
    }

    @Override
    public JsonResult addSysUser(SysUser sysUser) {
        sysUser.setId(IdUtils.getId());
        sysUser.setPassWord(new SimpleHash("md5", sysUser.getPassWord(), ByteSource.Util.bytes(sysUser.getUserName()), 2).toHex());
        int insert = baseMapper.insert(sysUser);
        if (insert==1){
            return new JsonResult(true, ResultCode.SUCCESS);
        }else {
            return new JsonResult(false,ResultCode.COMMON_FAIL);
        }
    }

    @Override
    public SysUser finUserByCanalId(String canalId) {
        return baseMapper.selectOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getCanalId,canalId));
    }

    @Override
    public JsonResult getUserCount() {
        return new JsonResult<>(true,ResultCode.SUCCESS,baseMapper.selectList(Wrappers.<SysUser>query()));
    }
}
