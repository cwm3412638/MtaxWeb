package com.mtax.dm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtax.dm.entity.SysUser;
import com.mtax.dm.mapper.SysUserMapper;
import com.mtax.dm.service.SysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public SysUser findUserByUserName(String userName) {
        return baseMapper.selectOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUserName,userName));
    }

    @Override
    public int updatePassWord(SysUser loginUser) {
        return baseMapper.updateById(loginUser);
    }
}
