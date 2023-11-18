package com.sangeng.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.model.system.SysRole;
import com.sangeng.model.vo.SysRoleQueryVo;
import com.sangeng.system.mapper.SysRoleMapper;
import com.sangeng.system.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * @author: calos
 * @create: 2023-11-18 13:49
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo roleQueryVo) {
        return baseMapper.selectPage(pageParam, roleQueryVo);
    }
}
