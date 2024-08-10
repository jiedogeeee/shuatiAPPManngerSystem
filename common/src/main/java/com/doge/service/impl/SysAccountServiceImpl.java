package com.doge.service.impl;

import com.doge.entity.config.AppConfig;
import com.doge.entity.dto.SessionUserAdminDto;
import com.doge.entity.enums.MenuTypeEnum;
import com.doge.entity.enums.PageSize;
import com.doge.entity.enums.SysAccountStatusEnum;
import com.doge.entity.enums.UserStatusEnum;
import com.doge.entity.po.SysAccount;
import com.doge.entity.po.SysMenu;
import com.doge.entity.query.SimplePage;
import com.doge.entity.query.SysAccountQuery;
import com.doge.entity.query.SysMenuQuery;
import com.doge.entity.vo.PaginationResultVO;
import com.doge.entity.vo.SysMenuVo;
import com.doge.exception.BusinessException;
import com.doge.mappers.SysAccountMapper;
import com.doge.service.SysAccountService;
import com.doge.service.SysMenuService;
import com.doge.utils.CopyTools;
import com.doge.utils.StringTools;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:账号信息Service
 * @Author:我爱你
 * @date:2024/07/08
 */
@Service("sysAccountService")
public class SysAccountServiceImpl implements SysAccountService {

    @Resource
    private SysAccountMapper<SysAccount, SysAccountQuery> sysAccountMapper;

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private AppConfig appConfig;

    /**
     * 根据条件查询列表
     */


    @Override
    public List<SysAccount> findListByParam(SysAccountQuery query) {
        return this.sysAccountMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */


    @Override
    public Integer findCountByParam(SysAccountQuery query) {
        return this.sysAccountMapper.selectCount(query);

    }

    /**
     * 分页查询
     */

    @Override
    public PaginationResultVO<SysAccount> findListByPage(SysAccountQuery query) {
        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<SysAccount> list = this.findListByParam(query);
        PaginationResultVO<SysAccount> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    /**
     * 新增
     */

    @Override
    public Integer add(SysAccount bean) {
        return this.sysAccountMapper.insert(bean);
    }

    /**
     * 批量新增
     */

    @Override
    public Integer addBatch(List<SysAccount> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.sysAccountMapper.insertBatch(ListBean);
    }

    /**
     * 批量新增或者更新
     */

    @Override
    public Integer addBatchOrUpdate(List<SysAccount> ListBean) {
        if (ListBean == null || ListBean.isEmpty()) {
            return 0;
        }
        return this.sysAccountMapper.insertOrUpdateBatch(ListBean);
    }

    /**
     * 根据UserId查询
     */
    @Override
    public SysAccount getByUserId(Integer userId) {
        return this.sysAccountMapper.selectByUserId(userId);
    }

    /**
     * 根据UserId更新
     */
    @Override
    public Integer updateByUserId(SysAccount bean, Integer userId) {
        return this.sysAccountMapper.updateByUserId(bean, userId);
    }

    /**
     * 根据UserId删除
     */
    @Override
    public Integer deleteByUserId(Integer userId) {
        return this.sysAccountMapper.deleteByUserId(userId);
    }

    /**
     * 根据Phone查询
     */
    @Override
    public SysAccount getByPhone(String phone) {
        return this.sysAccountMapper.selectByPhone(phone);
    }

    /**
     * 根据Phone更新
     */
    @Override
    public Integer updateByPhone(SysAccount bean, String phone) {
        return this.sysAccountMapper.updateByPhone(bean, phone);
    }

    /**
     * 根据Phone删除
     */
    @Override
    public Integer deleteByPhone(String phone) {

        return this.sysAccountMapper.deleteByPhone(phone);
    }

    @Override
    public SessionUserAdminDto login(String phone, String password) throws BusinessException {
        SysAccount sysAccount = this.sysAccountMapper.selectByPhone(phone);
        if (sysAccount == null) {
            throw new BusinessException("账号或密码错误");
        }
        if (!sysAccount.getPassword().equals(password)) {
            throw new BusinessException("账号或密码错误");
        }
        if (SysAccountStatusEnum.DISABLE.equals(sysAccount.getStatus())) {
            throw new BusinessException("账号已禁用");
        }

        SessionUserAdminDto adminDto = new SessionUserAdminDto();

        adminDto.setUserName(sysAccount.getUserName());
        adminDto.setUserId(sysAccount.getUserId());

        List<SysMenu> allMenus;
        if (!StringTools.isEmpty(appConfig.getSuperAdminPhones()) &&
                ArrayUtils.contains(appConfig.getSuperAdminPhones().split(","), phone)) {
            adminDto.setSuperAdmin(true);
            SysMenuQuery query = new SysMenuQuery();
            query.setFormat2Tree(false);
            query.setOrderBy("sort asc");
            allMenus = sysMenuService.findListByParam(query);
        } else {
            adminDto.setSuperAdmin(false);
            allMenus = sysMenuService.getALlMenuByRoleIds(sysAccount.getRoles());
        }
        List<String> permissionCodeList = new ArrayList();
        List<SysMenu> menuList = new ArrayList<>();
        for (SysMenu sysMenu : allMenus) {
            if (MenuTypeEnum.MENU.getType().equals(sysMenu.getMenuType())) {
                menuList.add(sysMenu);
            }
            permissionCodeList.add(sysMenu.getPermissionCode());
        }
        menuList = sysMenuService.convertLine2Tree4Menu(menuList, 0);
        if (menuList.isEmpty()) {
            throw new BusinessException("请联系管理员分配角色");
        }
        List<SysMenuVo> menuVoList = new ArrayList();
        menuList.forEach(item -> {
            SysMenuVo menuVo = CopyTools.copy(item, SysMenuVo.class);
            menuVo.setChildren(CopyTools.copyList(item.getChildren(), SysMenuVo.class));
            menuVoList.add(menuVo);
        });
        adminDto.setMenuList(menuVoList);
        adminDto.setPermissionCodeList(permissionCodeList);

        return adminDto;
    }

    @Override
    public void saveSysAccount(SysAccount sysAccount) throws BusinessException {
        SysAccount phoneDb = sysAccountMapper.selectByPhone(sysAccount.getPhone());
        if (phoneDb != null && (sysAccount.getUserId() == null || !sysAccount.getUserId().equals(phoneDb.getUserId()))) {
            throw new BusinessException("手机号已存在");
        }
        if (sysAccount.getUserId() == null) {
            sysAccount.setCreateTime(new Date());
            sysAccount.setStatus(UserStatusEnum.ABLE.getStatus());
            sysAccount.setPassword(StringTools.encodeByMD5(sysAccount.getPassword()));
            this.sysAccountMapper.insert(sysAccount);
        } else {
            sysAccount.setCreateTime(null);
            sysAccount.setStatus(null);
            this.sysAccountMapper.updateByUserId(sysAccount, sysAccount.getUserId());
        }
    }
}