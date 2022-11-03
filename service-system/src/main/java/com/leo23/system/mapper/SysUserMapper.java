package com.leo23.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leo23.model.system.SysUser;
import com.leo23.model.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author leo23
 * @since 2022-11-02
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    IPage<SysUser> selectPage(Page<SysUser> pageParam, @Param("vo") SysUserQueryVo sysUserQueryVo);
}
