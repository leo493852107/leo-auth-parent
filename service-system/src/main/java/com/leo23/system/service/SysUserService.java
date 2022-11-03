package com.leo23.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.leo23.model.system.SysUser;
import com.leo23.model.vo.SysUserQueryVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author leo23
 * @since 2022-11-02
 */
public interface SysUserService extends IService<SysUser> {
    // 用户列表
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);
}
