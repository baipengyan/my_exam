package com.exam.service.impl;

import com.exam.entity.SysUser;
import com.exam.dao.SysUserDao;
import com.exam.service.SysUserService;
import com.exam.utils.Constants;
import com.exam.utils.Convert;
import com.exam.utils.StringUtils;
import com.exam.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2019-05-01 00:15:15
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser selectSysyUserById(Integer id) {
        return this.sysUserDao.selectUserById(id);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 受影响的行数
     */
    @Override
    public int insert(SysUser sysUser) {
        if (StringUtils.isBlank(sysUser.getPassword())) {
            sysUser.setPassword(Constants.DEFAULT_PASSWORD);
        }
        WebUtils.entryptPassword(sysUser);
        return sysUserDao.insert(sysUser);
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SysUser sysUser) {
        return this.sysUserDao.update(sysUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysUserDao.deleteById(id) > 0;
    }

    @Override
    public SysUser findSysUserByLoginName(String loginName) {
        SysUser sysUser = sysUserDao.findSysUserByLoginName(loginName);
        return sysUser;
    }

    @Override
    public List<SysUser> selectUserList(SysUser sysUser) {
        return sysUserDao.queryAll(sysUser);
    }

    @Override
    public int deleteSysUserByIds(String ids) {
        return sysUserDao.deleteByIds(Convert.toIntArray(ids));
    }
}