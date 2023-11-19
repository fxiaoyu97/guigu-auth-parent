package com.sangeng.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.model.system.SysMenu;
import com.sangeng.system.mapper.SysMenuMapper;
import com.sangeng.system.service.SysMenuService;

/**
 * @author: calos
 * @create: 2023-11-19 16:53
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findNodes() {

        return null;
    }
}
