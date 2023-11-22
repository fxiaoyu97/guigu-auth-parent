package com.sangeng.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.model.system.SysUser;
import com.sangeng.model.vo.RouterVo;
import com.sangeng.model.vo.SysUserQueryVo;
import com.sangeng.system.mapper.SysUserMapper;
import com.sangeng.system.service.SysMenuService;
import com.sangeng.system.service.SysUserService;

/**
 * @author: calos
 * @create: 2023-11-19 15:11
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo userQueryVo) {

        return sysUserMapper.selectPage(pageParam, userQueryVo);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        SysUser sysUser = sysUserMapper.selectById(id);
        sysUser.setStatus(status);
        sysUserMapper.updateById(sysUser);
    }

    @Override
    public SysUser getByUsername(String username) {
        return sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        // 根据username查询用户基本信息
        SysUser sysUser = this.getByUsername(username);
        // 根据userid查询菜单权限
        List<RouterVo> routerVoList = sysMenuService.getUserMenuList(sysUser.getId());
        // 根据userid查询按钮权限
        List<String> permsList = sysMenuService.getUserButtonList(sysUser.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("name", "admin");
        map.put("avatar", "https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        map.put("routers", routerVoList);
        map.put("buttons", permsList);
        return map;
    }
}
