package com.leo23.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leo23.common.result.Result;
import com.leo23.model.system.SysUser;
import com.leo23.model.vo.SysUserQueryVo;
import com.leo23.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author leo23
 * @since 2022-11-02
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @ApiOperation("用户列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Long page, @PathVariable Long limit, SysUserQueryVo sysUserQueryVo) {
        Page<SysUser> pageParam = new Page<>(page, limit);
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, sysUserQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("添加用户")
    @PostMapping("/save")
    public Result saveRole(@RequestBody SysUser sysUser) {
        boolean save = sysUserService.save(sysUser);
        if (save) {
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("查询角色")
    @GetMapping("/findUserById/{id}")
    public Result findUserById(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        return Result.ok(sysUser);
    }

    @ApiOperation("修改用户")
    @PostMapping("/updateRoleById")
    public Result updateRoleById(@RequestBody SysUser sysUser) {
        boolean b = sysUserService.updateById(sysUser);
        if (b) {
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        boolean b = sysUserService.removeById(id);
        if (b) {
            return Result.ok();
        }
        return Result.fail();
    }

}
