package com.mtax.dm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.common.result.ResultCode;
import com.mtax.dm.entity.Canal;
import com.mtax.dm.entity.SysUser;
import com.mtax.dm.mapper.CanalMapper;
import com.mtax.dm.service.CanalService;
import com.mtax.dm.utils.IdUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CanalServiceImpl extends ServiceImpl<CanalMapper, Canal> implements CanalService {
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
}
