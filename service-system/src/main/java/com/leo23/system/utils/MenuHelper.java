package com.leo23.system.utils;

import com.leo23.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    // 构建树形结构
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        // 创建集合封装最终数据
        List<SysMenu> trees = new ArrayList<>();
        // 遍历所有菜单集合
        for (SysMenu sysMenu : sysMenuList) {
            // 递归入口，parent_id=0
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    // 从根节点递归查询，查询子节点
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        // 数据初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());

        // 遍历递归查找
        for (SysMenu treeNode : treeNodes) {
            String id = sysMenu.getId();
            long currentId = Long.parseLong(id);
            Long parentId = treeNode.getParentId();
            if (currentId == parentId) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(treeNode, treeNodes));
            }
        }
        return sysMenu;
    }
}
