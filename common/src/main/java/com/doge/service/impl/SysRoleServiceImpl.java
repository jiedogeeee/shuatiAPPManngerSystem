package com.doge.service.impl;

import com.doge.entity.enums.MenuCheckTypeEnum;
import com.doge.entity.enums.PageSize;
import com.doge.entity.enums.ResponseCodeEnum;
import com.doge.entity.po.SysAccount;
import com.doge.entity.po.SysRole;
import com.doge.entity.po.SysRole2Menu;
import com.doge.entity.query.SimplePage;
import com.doge.entity.query.SysAccountQuery;
import com.doge.entity.query.SysRole2MenuQuery;
import com.doge.entity.query.SysRoleQuery;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.exception.BusinessException;
import com.doge.mappers.SysAccountMapper;
import com.doge.mappers.SysRole2MenuMapper;
import com.doge.mappers.SysRoleMapper;
import com.doge.service.SysRoleService;
import com.doge.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:系统角色表Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    private static final Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Resource
    private SysRoleMapper<SysRole, SysRoleQuery> sysRoleMapper;

    @Resource
    private SysRole2MenuMapper<SysRole2Menu, SysRole2MenuQuery> sysRole2MenuMapper;


    @Resource
    private SysAccountMapper<SysAccount, SysAccountQuery> sysAccountMapper;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<SysRole> findListByParam(SysRoleQuery query) {
        return this.sysRoleMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(SysRoleQuery query) {
        return this.sysRoleMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<SysRole> findListByPage(SysRoleQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<SysRole> list = this.findListByParam(query);
        PaginationResultVO<SysRole> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(SysRole bean) {
        return this.sysRoleMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<SysRole> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.sysRoleMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<SysRole> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.sysRoleMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据RoleId查询
     */
    @Override
    public SysRole getByRoleId(Integer roleId) {
        SysRole sysRole = this.sysRoleMapper.selectByRoleId(roleId);
        List<Integer> selectMenuIds = this.sysRole2MenuMapper.selectMenuIdsByRoleIds(new String[]{String.valueOf(roleId)});
        sysRole.setMenuIds(selectMenuIds);
        return sysRole;
    }

    /**
     * 根据RoleId更新
     */
    @Override
    public Integer updateByRoleId(SysRole bean, Integer roleId) {
        return this.sysRoleMapper.updateByRoleId(bean, roleId);
    }

    /**
     * 根据RoleId删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteByRoleId(Integer roleId) throws BusinessException {
        SysAccountQuery query = new SysAccountQuery();
        query.setRoles(String.valueOf(roleId));
        Integer count = sysAccountMapper.selectCount(query);
        if (count > 0) {
            throw new BusinessException("该角色正在被使用,无法删除");
        }
        count = this.sysRoleMapper.deleteByRoleId(roleId);
        SysRole2MenuQuery role2MenuQuery = new SysRole2MenuQuery();
        role2MenuQuery.setRoleId(roleId);
        sysRole2MenuMapper.deleteByParam(role2MenuQuery);
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRole sysRole, String menuIds, String halfMenuIds) throws BusinessException {
        Integer roleId = sysRole.getRoleId();
        Boolean addMenu = false;
        if (null == roleId) {
            Date curDate = new Date();
            sysRole.setCreateTime(curDate);
            sysRole.setLastUpdateTime(curDate);
            this.sysRoleMapper.insert(sysRole);
            roleId = sysRole.getRoleId();
            addMenu = true;
        } else {

            sysRole.setCreateTime(null);
            sysRole.setLastUpdateTime(new Date());
            this.sysRoleMapper.updateByRoleId(sysRole, roleId);
        }

        SysRoleQuery roleQuery = new SysRoleQuery();
        roleQuery.setRoleName(sysRole.getRoleName());
        Integer countRole = this.sysRoleMapper.selectCount(roleQuery);
        if (countRole > 1) {
            throw new BusinessException("角色已存在");
        }
        if (addMenu) {
            saveRoleManu(roleId, menuIds, halfMenuIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRoleManu(Integer roleId, String menuIds, String halfMenuIds) throws BusinessException {
        if (roleId == null || menuIds == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        List<SysRole2Menu> sysRole2MenuList = new ArrayList();
        SysRole2MenuQuery sysRole2MenuQuery = new SysRole2MenuQuery();
        sysRole2MenuQuery.setRoleId(roleId);
        sysRole2MenuMapper.deleteByParam(sysRole2MenuQuery);

        String[] menuIdsArray = menuIds.split(",");
        String[] halfMenuIdsArray = StringTools.isEmpty(halfMenuIds) ? new String[]{} : halfMenuIds.split(",");
        convertMenuId2RoleMenu(sysRole2MenuList, roleId, menuIdsArray, MenuCheckTypeEnum.ALL);
        convertMenuId2RoleMenu(sysRole2MenuList, roleId, halfMenuIdsArray, MenuCheckTypeEnum.HALF);
        if (!sysRole2MenuList.isEmpty()) {
            sysRole2MenuMapper.insertBatch(sysRole2MenuList);
        }
    }

    private void convertMenuId2RoleMenu(List<SysRole2Menu> sysRole2MenuList, Integer roleId, String[] menuIdsArray, MenuCheckTypeEnum menuCheckTypeEnum) {
        for (String menuId : menuIdsArray) {
            SysRole2Menu sysRole2Menu = new SysRole2Menu();
            sysRole2Menu.setMenuId(Integer.parseInt(menuId));
            sysRole2Menu.setRoleId(roleId);
            sysRole2Menu.setCheckType(menuCheckTypeEnum.getType());
            sysRole2MenuList.add(sysRole2Menu);
        }
    }
}