package com.sangeng.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.model.system.SysMenu;

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

}
