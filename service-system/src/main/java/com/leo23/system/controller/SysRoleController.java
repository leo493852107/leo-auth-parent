package com.leo23.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leo23.common.result.Result;
import com.leo23.model.system.SysRole;
import com.leo23.model.vo.SysRoleQueryVo;
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
    public Result list() {
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }

    @ApiOperation("逻辑删除接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean b = sysRoleService.removeById(id);
        if (b) {
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page, @PathVariable Long limit, SysRoleQueryVo sysRoleQueryVo) {
        Page<SysRole> pageParam = new Page<>(page, limit);
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        return Result.ok(pageModel);
    }
}
