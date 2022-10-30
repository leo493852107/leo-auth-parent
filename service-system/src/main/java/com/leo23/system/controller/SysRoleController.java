package com.leo23.system.controller;

import com.leo23.model.system.SysRole;
import com.leo23.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @ApiOperation("查询所有角色")
    @GetMapping("/list")
    public List<SysRole> list() {
        List<SysRole> list = sysRoleService.list();
        return list;
    }

    @ApiOperation("逻辑删除接口")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        boolean b = sysRoleService.removeById(id);
        return b;
    }
}
