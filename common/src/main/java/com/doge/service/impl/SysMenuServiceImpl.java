package com.doge.service.impl;

import com.doge.entity.enums.PageSize;
import com.doge.entity.po.SysMenu;
import com.doge.entity.query.SimplePage;
import com.doge.entity.query.SysMenuQuery;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.SysMenuMapper;
import com.doge.service.SysMenuService;
import com.doge.utils.StringTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:菜单表Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    private static final Integer ROOT_MENU_ID = 0;
    private static final String ROOT_MENU_NAME = "所有菜单";

    @Resource
    private SysMenuMapper<SysMenu, SysMenuQuery> sysMenuMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<SysMenu> findListByParam(SysMenuQuery query) {
        List<SysMenu> sysMenuList = this.sysMenuMapper.selectList(query);
        if (query.getFormat2Tree() != null && query.getFormat2Tree()) {
            SysMenu root = new SysMenu();
            root.setMenuId(ROOT_MENU_ID);
            root.setpId(-1);
            root.setMenuName(ROOT_MENU_NAME);
            sysMenuList.add(root);
            sysMenuList = convertLine2Tree4Menu(sysMenuList, -1);
        }
        return sysMenuList;
    }

    @Override
    public List<SysMenu> convertLine2Tree4Menu(List<SysMenu> dataList, Integer pId) {
        List<SysMenu> children = new ArrayList();
        for (SysMenu m : dataList) {
            if (m.getMenuId() != null && m.getpId() != null && m.getpId().equals(pId)) {
                m.setChildren(convertLine2Tree4Menu(dataList, m.getMenuId()));
                children.add(m);
            }
        }
        return children;
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(SysMenuQuery query) {
        return this.sysMenuMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<SysMenu> findListByPage(SysMenuQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<SysMenu> list = this.findListByParam(query);
        PaginationResultVO<SysMenu> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(SysMenu bean) {
        return this.sysMenuMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<SysMenu> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.sysMenuMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<SysMenu> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.sysMenuMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据MenuId查询
     */
    @Override
    public SysMenu getByMenuId(Integer menuId) {
        return this.sysMenuMapper.selectByMenuId(menuId);
    }

    /**
     * 根据MenuId更新
     */
    @Override
    public Integer updateByMenuId(SysMenu bean, Integer menuId) {
        return this.sysMenuMapper.updateByMenuId(bean, menuId);
    }

    /**
     * 根据MenuId删除
     */
    @Override
    public Integer deleteByMenuId(Integer menuId) {
        return this.sysMenuMapper.deleteByMenuId(menuId);
    }

    @Override
    public void saveMenu(SysMenu sysMenu) {
        if (sysMenu.getMenuId() == null) {
            sysMenuMapper.insert(sysMenu);
        } else {
            sysMenuMapper.updateByMenuId(sysMenu, sysMenu.getMenuId());
        }
    }

    @Override
    public List<SysMenu> getALlMenuByRoleIds(String roleIds) {
        if (StringTools.isEmpty(roleIds)) {
            return new ArrayList();
        }
        int[] roleIdArray = Arrays.stream(roleIds.split(",")).mapToInt(Integer::valueOf).toArray();
        return sysMenuMapper.selectALlMenuByRoleIds(roleIdArray);
    }
}