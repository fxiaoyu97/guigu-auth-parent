package com.sangeng.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.model.system.SysMenu;
import com.sangeng.model.system.SysRoleMenu;
import com.sangeng.model.vo.AssignMenuVo;
import com.sangeng.model.vo.RouterVo;
import com.sangeng.system.exception.SanException;
import com.sangeng.system.mapper.SysMenuMapper;
import com.sangeng.system.mapper.SysRoleMenuMapper;
import com.sangeng.system.service.SysMenuService;
import com.sangeng.system.utils.MenuHelper;
import com.sangeng.system.utils.RouterHelper;

/**
 * @author: calos
 * @create: 2023-11-19 16:53
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        // 获取所有菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        List<SysMenu> result = MenuHelper.buildTree(sysMenuList);
        return result;
    }

    @Override
    public void removeMenuById(Long id) {
        // 查询当前删除菜单下是否存在子菜单
        Integer count = baseMapper.selectCount(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, id));
        if (count > 0) {
            throw new SanException(201, "请先删除子菜单");
        }
        baseMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> findSysMenuByRoleId(Long roleId) {
        // 获取所有菜单 status=1
        List<SysMenu> menuList = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getStatus, 1));
        // 根据角色id查询， 角色分配过的菜单
        List<SysRoleMenu> roleMenuList =
            sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        // 获取角色分配所有菜单
        List<String> roleMenuIdList = roleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        // 数据处理：isSelect 如果菜单选中就是true，否则就是false
        menuList.forEach(menu -> menu.setSelect(roleMenuIdList.contains(menu.getId())));
        return MenuHelper.buildTree(menuList);
    }

    @Override
    public void doAssign(AssignMenuVo assignMenuVo) {
        sysRoleMenuMapper
            .delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, assignMenuVo.getRoleId()));
        // 遍历所有已选择的权限id
        for (String menuId : assignMenuVo.getMenuIdList()) {
            if (menuId != null) {
                // 创建SysRoleMenu对象
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(assignMenuVo.getRoleId());
                // 添加新权限
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }
    }

    @Override
    public List<RouterVo> getUserMenuList(String userId) {
        // 超级管理员admin账号id为：1
        List<SysMenu> sysMenuList = null;
        if (Objects.equals(userId, "1")) {
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("status", 1);
            wrapper.orderByAsc("sort_value");
            sysMenuList = baseMapper.selectList(wrapper);
        } else {
            sysMenuList = baseMapper.findListByUserId(userId);
        }
        // 构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);

        // 构建路由
        return RouterHelper.buildRouters(sysMenuTreeList);
    }

    @Override
    public List<String> getUserButtonList(String userId) {
        // 超级管理员admin账号id为：1
        List<SysMenu> sysMenuList = null;
        if (Objects.equals(userId, "1")) {
            sysMenuList = baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        } else {
            sysMenuList = baseMapper.findListByUserId(userId);
        }
        // 创建返回的集合
        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getType() == 2) {
                permissionList.add(sysMenu.getPerms());
            }
        }
        return permissionList;
    }
}
