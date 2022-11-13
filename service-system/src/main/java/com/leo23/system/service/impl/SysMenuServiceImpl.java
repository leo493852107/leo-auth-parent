package com.leo23.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leo23.model.system.SysMenu;
import com.leo23.system.exception.MyBusinessException;
import com.leo23.system.mapper.SysMenuMapper;
import com.leo23.system.service.SysMenuService;
import com.leo23.system.utils.MenuHelper;
import org.springframework.stereotype.Service;

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
}
