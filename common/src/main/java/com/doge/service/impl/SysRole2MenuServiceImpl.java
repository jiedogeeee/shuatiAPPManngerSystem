package com.doge.service.impl;

import com.doge.entity.enums.PageSize;
import com.doge.entity.po.SysRole2Menu;
import com.doge.entity.query.SimplePage;
import com.doge.entity.query.SysRole2MenuQuery;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.mappers.SysRole2MenuMapper;
import com.doge.service.SysRole2MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:角色对应的菜单权限表Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("sysRole2MenuService")
public class SysRole2MenuServiceImpl implements SysRole2MenuService {

    @Resource
    private SysRole2MenuMapper<SysRole2Menu, SysRole2MenuQuery> sysRole2MenuMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<SysRole2Menu> findListByParam(SysRole2MenuQuery query) {
        return this.sysRole2MenuMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(SysRole2MenuQuery query) {
        return this.sysRole2MenuMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<SysRole2Menu> findListByPage(SysRole2MenuQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<SysRole2Menu> list = this.findListByParam(query);
        PaginationResultVO<SysRole2Menu> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(SysRole2Menu bean) {
        return this.sysRole2MenuMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<SysRole2Menu> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.sysRole2MenuMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<SysRole2Menu> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.sysRole2MenuMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据RoleIdAndMenuId查询
     */
    @Override
    public SysRole2Menu getByRoleIdAndMenuId(Integer roleId, Integer menuId) {
        return this.sysRole2MenuMapper.selectByRoleIdAndMenuId(roleId, menuId);
    }

    /**
     * 根据RoleIdAndMenuId更新
     */
    @Override
    public Integer updateByRoleIdAndMenuId(SysRole2Menu bean, Integer roleId, Integer menuId) {
        return this.sysRole2MenuMapper.updateByRoleIdAndMenuId(bean, roleId, menuId);
    }

    /**
     * 根据RoleIdAndMenuId删除
     */
    @Override
    public Integer deleteByRoleIdAndMenuId(Integer roleId, Integer menuId) {
        return this.sysRole2MenuMapper.deleteByRoleIdAndMenuId(roleId, menuId);
    }

}