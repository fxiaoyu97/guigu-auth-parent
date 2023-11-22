package com.sangeng.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sangeng.model.system.SysLoginLog;
import com.sangeng.model.vo.SysLoginLogQueryVo;

/**
 * @author: calos
 * @create: 2023-11-22 16:45
 */
public interface SysLoginLogService extends IService<SysLoginLog> {
    // 列表显示
    IPage<SysLoginLog> selectPage(Page<SysLoginLog> pageParam, SysLoginLogQueryVo sysLoginLogQueryVo);
}