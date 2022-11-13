package com.leo23.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.leo23.model.system.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author leo23
 * @since 2022-11-02
 */
public interface SysMenuService extends IService<SysMenu> {
    // 菜单列表(树形)
    List<SysMenu> findNodes();

    // 删除菜单
    void removeMenuById(String id);
}
