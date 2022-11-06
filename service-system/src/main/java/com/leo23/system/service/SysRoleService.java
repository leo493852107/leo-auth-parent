package com.leo23.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leo23.model.system.SysRole;
import com.leo23.model.vo.AssginRoleVo;
import com.leo23.model.vo.SysRoleQueryVo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole> {
    // 条件分页查询
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);

    // 获取用户角色数据
    Map<String, Object> getRolesByUserId(String userId);

    // 用户分配角色
    void doAssign(AssginRoleVo assginRoleVo);
}
