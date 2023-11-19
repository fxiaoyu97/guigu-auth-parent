package com.sangeng.system.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.model.system.SysRole;
import com.sangeng.model.vo.AssginRoleVo;
import com.sangeng.model.vo.SysRoleQueryVo;

/**
 * @author: calos
 * @create: 2023-11-18 13:48
 */
public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> selectPage(Page<SysRole> sysRolePage, SysRoleQueryVo roleQueryVo);

    Map<String, Object> getRolesByUserId(Long userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
