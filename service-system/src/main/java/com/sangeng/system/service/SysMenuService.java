package com.sangeng.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.model.system.SysMenu;
import com.sangeng.model.vo.AssignMenuVo;
import com.sangeng.model.vo.RouterVo;

/**
 * @author: calos
 * @create: 2023-11-19 16:53
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 菜单树形数据
     *
     * @return
     */
    List<SysMenu> findNodes();

    void removeMenuById(Long id);

    List<SysMenu> findSysMenuByRoleId(Long roleId);

    void doAssign(AssignMenuVo assignMenuVo);

    List<RouterVo> getUserMenuList(String userId);

    List<String> getUserButtonList(String userId);
}
