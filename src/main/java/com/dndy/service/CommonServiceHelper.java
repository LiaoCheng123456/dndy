package com.dndy.service;

import com.dndy.dao.IRoleDao;
import com.dndy.dao.IUserDao;
import com.dndy.model.PageData;
import com.dndy.util.LogUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "commonServiceHelper")
public class CommonServiceHelper extends BaseService {

    @Resource(name = "userDaoImpl")
    private IUserDao userDao;

    @Resource(name = "roleDaoImpl")
    private IRoleDao roleDaoImpl;

    /**
     * 传入用户id，验证是否是超级管理员
     */
    public boolean checkIsAdmin(Object uid) {
        if (uid == null) {
            throw new NullPointerException("uid不能为空");
        }
        PageData user = new PageData();
        user.put("id", uid);
        PageData userInfo = userDao.getUser(user);
        if (userInfo == null) {
            LogUtils.error(this.getClass().getSimpleName(), "checkIsAdmin", uid.toString(), "用户不存在", null);
            throw new NullPointerException("用户不存在");
        }
        PageData roleId = getRoleInfo(userInfo.get("roleId"));
        if (roleId != null) {
            return roleId.get("isAdmin") != null && roleId.get("isAdmin").toString().equals("1");
        } else {
            return false;
        }
    }

    /**
     * 检查角色是否存在
     */
    public PageData getRoleInfo(Object roleId) {
        PageData role = new PageData();
        role.put("id", roleId);
        return roleDaoImpl.getRole(role);
    }

}
