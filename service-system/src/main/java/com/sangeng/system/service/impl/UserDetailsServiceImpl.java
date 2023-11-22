package com.sangeng.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sangeng.model.system.SysUser;
import com.sangeng.system.custom.CustomUser;
import com.sangeng.system.service.SysMenuService;
import com.sangeng.system.service.SysUserService;

/**
 * @author: calos
 * @create: 2023-11-21 16:31
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (null == sysUser) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        if (sysUser.getStatus() == 0) {
            throw new RuntimeException("账号已停用");
        }
        // 根据userid查询操作权限数据
        List<String> userPermsList = sysMenuService.getUserButtonList(sysUser.getId());
        // 转换security要求格式数据
        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        userPermsList.forEach(item -> authorityList.add(new SimpleGrantedAuthority(item.trim())));
        return new CustomUser(sysUser, authorityList);
    }
}
