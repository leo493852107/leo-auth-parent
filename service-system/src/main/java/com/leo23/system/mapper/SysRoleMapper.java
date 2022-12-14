package com.leo23.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leo23.model.system.SysRole;
import com.leo23.model.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    // 条件分页查询
    IPage<SysRole> selectPage(Page<SysRole> pageParam, @Param("vo") SysRoleQueryVo sysRoleQueryVo);
}
