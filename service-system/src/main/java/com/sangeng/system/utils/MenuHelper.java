package com.sangeng.system.utils;

import java.util.ArrayList;
import java.util.List;

import com.sangeng.model.system.SysMenu;

/**
 * @author: calos
 * @create: 2023-11-21 09:57
 */
public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> resultList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getParentId() == 0) {
                resultList.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return resultList;
    }

    // 从根节点递归查询，查询子节点
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<>());
        for (SysMenu children : sysMenuList) {
            if (Long.parseLong(sysMenu.getId()) == children.getParentId()) {
                sysMenu.getChildren().add(findChildren(children, sysMenuList));
            }
        }
        return sysMenu;
    }
}
