package com.sangeng.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sangeng.model.system.SysOperLog;
import com.sangeng.system.mapper.SysOperLogMapper;
import com.sangeng.system.service.SysOperLogService;

/**
 * @author: calos
 * @create: 2023-11-22 16:30
 */
@Service
public class OperLogServiceImpl implements SysOperLogService {
    @Resource
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public void saveSysLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
