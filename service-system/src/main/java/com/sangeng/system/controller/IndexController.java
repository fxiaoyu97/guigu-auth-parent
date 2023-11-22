package com.sangeng.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sangeng.common.result.Result;
import com.sangeng.common.result.ResultCodeEnum;
import com.sangeng.common.utils.JwtHelper;
import com.sangeng.common.utils.MD5;
import com.sangeng.model.system.SysUser;
import com.sangeng.model.vo.LoginVo;
import com.sangeng.system.exception.SanException;
import com.sangeng.system.service.SysUserService;

import io.swagger.annotations.Api;

/**
 * @author: calos
 * @create: 2023-11-19 10:12
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     * 
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        SysUser sysUser = sysUserService.getByUsername(loginVo.getUsername());
        if (null == sysUser) {
            throw new SanException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (!MD5.encrypt(loginVo.getPassword()).equals(sysUser.getPassword())) {
            throw new SanException(ResultCodeEnum.PASSWORD_ERROR);
        }
        if (sysUser.getStatus() == 0) {
            throw new SanException(ResultCodeEnum.ACCOUNT_STOP);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtHelper.createToken(sysUser.getId(), sysUser.getUsername()));
        return Result.ok(map);
    }

    /**
     * 获取用户信息
     * 
     * @return
     */
    @GetMapping("/info")
    public Result info(HttpServletRequest request) {
        // 获取请求头token字符串
        String token = request.getHeader("token");
        // 从token字符串获取用户名称(id)
        String username = JwtHelper.getUsername(token);
        // 根据用户名称获取用户信息（基本信息、菜单权限、按钮权限）
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return Result.ok(map);
    }

    /**
     * 退出
     * 
     * @return
     */
    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }
}
