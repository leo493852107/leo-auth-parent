package com.leo23.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leo23.model.system.SysMenu;
import com.leo23.model.system.SysRoleMenu;
import com.leo23.model.vo.AssginMenuVo;
import com.leo23.system.exception.MyBusinessException;
import com.leo23.system.mapper.SysMenuMapper;
import com.leo23.system.mapper.SysRoleMenuMapper;
import com.leo23.system.service.SysMenuService;
import com.leo23.system.utils.MenuHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author leo23
 * @since 2022-11-02
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        // 获取所有菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        // 所有菜单转换成要求格式
        List<SysMenu> list = MenuHelper.buildTree(sysMenuList);
        return list;
    }

    @Override
    public void removeMenuById(String id) {
        // 先查询下当前删除菜单下面是否有子菜单
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Long count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            // 有子菜单
            throw new MyBusinessException(201, "请先删除子菜单");
        }
        // 没有子菜单，删除
        baseMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {
        // 获取所有菜单 status = 1
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        List<SysMenu> menuList = baseMapper.selectList(wrapper);
        // 根据角色id查询 角色分配过的菜单列表
        QueryWrapper<SysRoleMenu> roleMenuWrapper = new QueryWrapper<>();
        roleMenuWrapper.eq("role_id", roleId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(roleMenuWrapper);
        // 从第二步查询列表中，获取角色分配所有菜单id
        List<String> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu sysRoleMenu : sysRoleMenus) {
            roleMenuIds.add(sysRoleMenu.getMenuId());
        }
        // 数据处理: isSelect 菜单选中true，否则false
        // 拿着分配菜单id和所有菜单比对，有相同的，让isSelect值true
        for (SysMenu sysMenu : menuList) {
            if (roleMenuIds.contains(sysMenu.getId())) {
                sysMenu.setSelect(true);
            } else {
                sysMenu.setSelect(false);
            }
        }
        // 转换成树形结构方便显示
        List<SysMenu> sysMenus = MenuHelper.buildTree(menuList);
        return sysMenus;
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        // 根据角色id删除菜单权限
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);
        // 遍历菜单id列表，一个一个进行添加
        List<String> menuIdList = assginMenuVo.getMenuIdList();
        for (String menuId : menuIdList) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }
}
