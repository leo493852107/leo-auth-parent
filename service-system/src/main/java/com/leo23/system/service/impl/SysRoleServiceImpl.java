package com.leo23.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leo23.model.system.SysRole;
import com.leo23.model.system.SysUserRole;
import com.leo23.model.vo.AssginRoleVo;
import com.leo23.model.vo.SysRoleQueryVo;
import com.leo23.system.mapper.SysRoleMapper;
import com.leo23.system.mapper.SysUserRoleMapper;
import com.leo23.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParam, sysRoleQueryVo);
        return pageModel;
    }

    @Override
    public Map<String, Object> getRolesByUserId(String userId) {
        // 获取所有角色
        List<SysRole> roles = baseMapper.selectList(null);
        // 根据用户id查询已经分配的角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(wrapper);
        // 从userRoles集合获取所有的角色id
        List<String> userRoleIds = new ArrayList<>();
        for (SysUserRole userRole : userRoles) {
            String roleId = userRole.getRoleId();
            userRoleIds.add(roleId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("allRoles", roles);
        map.put("userRoleIds", userRoleIds);
        return map;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        // 根据用户id删除之前分配的角色
        QueryWrapper<SysUserRole> wrapper  = new QueryWrapper<>();
        wrapper.eq("user_id", assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);
        // 获取所有角色id，添加角色用户关系表
        // 角色id列表
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for (String roleId : roleIdList) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(assginRoleVo.getUserId());
            userRole.setRoleId(roleId);
            sysUserRoleMapper.insert(userRole);
        }
    }
}
