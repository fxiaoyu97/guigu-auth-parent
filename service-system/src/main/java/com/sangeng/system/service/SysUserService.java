package com.sangeng.system.service;

import java.util.Map;

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

    SysUser getByUsername(String username);

    /**
     * 根据用户名获取用户登录信息
     * 
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo(String username);
}
