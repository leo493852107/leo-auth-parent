package com.leo23.system;

import com.leo23.model.system.SysRole;
import com.leo23.system.service.SysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class SysRoleServiceTest {
    @Resource
    private SysRoleService sysRoleService;

    @Test
    public void findAll() {
        List<SysRole> list = sysRoleService.list();
        System.out.println(list);
    }

    @Test
    public void testAdd() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色测试Service");
        sysRole.setRoleCode("Service Role");
        sysRole.setDescription("角色测试Service管理员");
        sysRoleService.save(sysRole);
    }

    @Test
    public void testUpdate() {
        SysRole sysRole = sysRoleService.getById("1586533197202141186");
        sysRole.setDescription("角色测试-管理员");
        sysRoleService.updateById(sysRole);
    }

    @Test
    public void testDelete() {
        boolean b = sysRoleService.removeById("1586533197202141186");
    }
}
