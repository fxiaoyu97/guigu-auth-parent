package com.sangeng.system;

import com.sangeng.model.system.SysRole;
import com.sangeng.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: calos
 * @create: 2023-11-18 12:33
 */
@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Test
    public void testAdd(){
        List<SysRole> sysRoleList = sysRoleMapper.selectList(null);
        sysRoleList.forEach(System.out::println);
    }
}
