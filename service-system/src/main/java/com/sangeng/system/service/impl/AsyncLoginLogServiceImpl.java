package com.sangeng.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sangeng.model.system.SysLoginLog;
import com.sangeng.system.mapper.SysLoginLogMapper;
import com.sangeng.system.service.AsyncLoginLogService;

/**
 * @author: calos
 * @create: 2023-11-22 15:53
 */
@Service
public class AsyncLoginLogServiceImpl implements AsyncLoginLogService {
    @Resource
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public void recordLoginLog(String username, Integer status, String ipaddr, String message) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUsername(username);
        sysLoginLog.setIpaddr(ipaddr);
        sysLoginLog.setMsg(message);
        // 日志状态
        sysLoginLog.setStatus(status);
        sysLoginLogMapper.insert(sysLoginLog);
    }
}
