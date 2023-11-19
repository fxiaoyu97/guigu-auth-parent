package com.sangeng.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.model.system.SysUser;
import com.sangeng.model.vo.SysUserQueryVo;

/**
 * @author: calos
 * @create: 2023-11-19 15:10
 */
public interface SysUserService extends IService<SysUser> {
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo adminQueryVo);

    void updateStatus(Long id, Integer status);
}
