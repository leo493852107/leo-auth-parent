package com.leo23.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.leo23.model.system.SysRole;
import com.leo23.system.mapper.SysRoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Test
    public void testFindAll() {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }
    }

    @Test
    public void testAdd() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试角色");
        sysRole.setRoleCode("testManager");
        sysRole.setDescription("测试角色");
        int row = sysRoleMapper.insert(sysRole);
        System.out.println(row);
    }

    @Test
    public void testUpdate() {
        SysRole sysRole = sysRoleMapper.selectById("1586178604239163393");
        sysRole.setDescription("测试角色11");
        sysRoleMapper.updateById(sysRole);
    }

    @Test
    public void testDelete() {
        int row = sysRoleMapper.deleteById("1586523079060316161");
        System.out.println(row);
    }

    @Test
    public void testSelect() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.like("role_name", "管理员");
        List<SysRole> sysRoles = sysRoleMapper.selectList(wrapper);
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }
    }

    @Test
    public void testSelectDelete() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.like("role_name", "角色");
        int delete = sysRoleMapper.delete(wrapper);
        System.out.println(delete);
    }
}
