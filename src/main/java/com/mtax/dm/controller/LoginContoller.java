package com.mtax.dm.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mtax.dm.common.result.JsonResult;
import com.mtax.dm.common.result.ResultCode;
import com.mtax.dm.entity.SysUser;
import com.mtax.dm.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.webresources.JarResourceSet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "登录操作")
@Slf4j
public class LoginContoller {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public JsonResult login(@RequestBody SysUser sysUser) {
        if (sysUser == null || sysUser.getUserName() == null || sysUser.getPassWord() == null) {
            return new JsonResult(false, ResultCode.PARAM_IS_BLANK);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUserName(), sysUser.getPassWord());
        // remembermMe记住密码
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
            return new JsonResult(false, ResultCode.USER_CREDENTIALS_ERROR);
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
            return new JsonResult(false, ResultCode.USER_ACCOUNT_NOT_EXIST);
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
            return new JsonResult(false, ResultCode.USER_ACCOUNT_LOCKED);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, ResultCode.USER_ACCOUNT_LOCKED);
        }
        SysUser userByUserName = sysUserService.findUserByUserName(sysUser.getUserName());
        subject.getSession().setAttribute("loginUser", userByUserName);
        log.info("用户：{}" + userByUserName + "登录成功");
        return new JsonResult(true, ResultCode.SUCCESS, userByUserName);
    }

    @PostMapping("/logOut")
    @ApiOperation(value = "退出", notes = "退出")
    public JsonResult logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new JsonResult(true, ResultCode.SUCCESS);
    }

    /**
     * 修改密码
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/updatePassWord")
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public JsonResult updatePassWord(@RequestBody SysUser sysUser) {
        if (sysUser == null) {
            return new JsonResult(false, ResultCode.PARAM_IS_BLANK);
        }
        if (sysUser.getPassWord() == null || sysUser.getPassWord().length() < 0) {
            return new JsonResult(false, ResultCode.PARAM_IS_BLANK);
        }
        Subject subject = SecurityUtils.getSubject();
        SysUser loginUser = (SysUser) subject.getSession().getAttribute("loginUser");
        loginUser.setPassWord(sysUser.getPassWord());
        int i = sysUserService.updatePassWord(loginUser);
        if (i == 0) {
            return new JsonResult(false, ResultCode.COMMON_FAIL);
        }
        subject.logout();
        return new JsonResult(true, ResultCode.SUCCESS);
    }

    @GetMapping("/user/list")
    @ApiOperation(value = "用户列表", notes = "用户列表")
    public JsonResult list(@RequestParam String canalId) {
        if (canalId == null || canalId.equals("")) {
            return new JsonResult(true, sysUserService.list());
        } else {
            return new JsonResult(true, sysUserService.list(Wrappers.<SysUser>query()
                    .lambda()
                    .eq(SysUser::getCanalId, canalId)));
        }
    }

    @PostMapping("/user/addUser")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    public JsonResult addUser(@RequestBody SysUser sysUser) {
        if (sysUser == null) {
            return new JsonResult(false, ResultCode.PARAM_IS_BLANK);
        }
        if (sysUser.getUserName() == null) {
            return new JsonResult(false, ResultCode.PARAM_NOT_COMPLETE);
        }
        if (sysUser.getPassWord() == null) {
            return new JsonResult(false, ResultCode.PARAM_NOT_COMPLETE);
        }
        return sysUserService.addSysUser(sysUser);
    }

    @PutMapping("/user/editUser")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    public JsonResult editUser(@RequestBody SysUser sysUser) {
        if (sysUser.getId() != null) {
            return sysUserService.editUser(sysUser);
        }
        return new JsonResult(false, ResultCode.PARAM_NOT_COMPLETE);
    }

    @DeleteMapping("/user/deleteUser/{id}")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    public JsonResult deleteUser(@PathVariable String id) {
        if (null != id) {
            return new JsonResult(sysUserService.removeById(id));
        }
        return new JsonResult(false, ResultCode.PARAM_NOT_COMPLETE);
    }

    @GetMapping("/user/getUserCount")
    @ApiOperation(value = "获取用户数量", notes = "获取用户数量")
    public JsonResult getUserCount() {
        return sysUserService.getUserCount();
    }
}
