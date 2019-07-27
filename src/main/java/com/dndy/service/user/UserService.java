package com.dndy.service.user;

import com.dndy.dao.*;
import com.dndy.model.PageData;
import com.dndy.model.ResultPageModel;
import com.dndy.service.BaseService;
import com.dndy.service.CommonServiceHelper;
import com.dndy.util.Const;
import com.dndy.util.LogUtils;
import com.dndy.util.ParameterUtils;
import com.dndy.util.jwt.JwtService;
import com.dndy.util.redis.RedisService;
import com.wsp.core.WSPCode;
import com.wsp.core.WSPResult;
import com.wsp.utils.WSPDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserService extends BaseService {
    @Resource(name = "userDaoImpl")
    private IUserDao userDaoImpl;

    @Resource(name = "permissionDaoImpl")
    private IPermissionDao permissionDaoImpl;

    @Resource(name = "roleDaoImpl")
    private IRoleDao roleDaoImpl;

    @Resource(name = "baseDao")
    private BaseDao baseDao;

    @Resource(name = "roleInPermissionDaoImpl")
    private IRoleInPermissionDao roleInPermissionDaoImpl;

    @Resource(name = "commonServiceHelper")
    private CommonServiceHelper commonServiceHelper;

    @Resource(name = "jwtService")
    private JwtService jwtService;

    @Resource(name = "redisService")
    private RedisService redisService;

    /**
     * 添加用户
     */
    public String addUser(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        // 请求参数验证
        String checkResult = ParameterUtils.checkParam(pd, "username", "password");
        if (checkResult != null) {
            return checkResult;
        }

        // 特殊字符过滤
        String specialParam = ParameterUtils.checkSpecialParam(pd, "username", "password");
        if (specialParam != null) {
            return specialParam;
        }

        try {

            // 检查账户是否存在
            PageData userName = new PageData();
            userName.put("username", pd.get("username"));
            PageData userInfo = userDaoImpl.getUser(userName);
            if (userInfo != null) {
                return json.toJSONString(new WSPResult("用户名已存在"));
            }

            // 如果有角色id需要验证角色是否存在
            if (pd.get("roleId") != null) {
                PageData role = commonServiceHelper.getRoleInfo(pd.get("roleId"));
                if (role == null) {
                    return LogUtils.error(this.getClass().getSimpleName(), "addUser", param, "角色不存在", null);
                }
            }

            // 设置密码
            String password = pd.get("password").toString();
            pd.put("password", ParameterUtils.md5(password.toUpperCase()).toUpperCase());
            pd.put("id", getLongID());
            pd.put("addBy", pd.get("uid"));
            pd.put("updateTime", WSPDate.getCurrentTimestemp());
            pd.put("addTime", WSPDate.getCurrentTimestemp());
            userDaoImpl.addUser(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "addUser", param, "添加用户失败", e);
        }
        return json.toJSONString(wspResult);
    }


    /**
     * 修改用户
     */
    public String modifyUser(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        // 请求参数验证
        String checkResult = ParameterUtils.checkParam(pd, "id");

        if (checkResult != null) {
            return checkResult;
        }
        try {
            // 检查账户是否存在
            PageData userName = new PageData();
            userName.put("id", pd.get("id"));
            PageData userInfo = userDaoImpl.getUser(userName);
            if (userInfo == null) {
                return json.toJSONString(new WSPResult("用户不存在"));
            }

            // 检查密码是否为空，如果为空就置为默认值
            if (!ParameterUtils.getResult(pd.get("password"))) {
                pd.put("password", ParameterUtils.md5(pd.get("password").toString()).toUpperCase());
            }

            // 如果有角色id需要验证角色是否存在
            if (pd.get("roleId") != null) {
                PageData role = commonServiceHelper.getRoleInfo(pd.get("roleId"));
                if (role == null) {
                    return LogUtils.error(this.getClass().getSimpleName(), "modifyUser", param, "角色不存在", null);
                }
            }

            pd.put("updateTime", WSPDate.getCurrentTimestemp());
            userDaoImpl.modifyUser(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "modifyUser", param, "编辑用户失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 删除用户
     */
    public String deleteUser(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        // 请求参数验证
        String checkResult = ParameterUtils.checkParam(pd, "id", "uid");

        if (checkResult != null) {
            return checkResult;
        }
        try {
            // 检查账户是否存在
            PageData userName = new PageData();
            userName.put("id", pd.get("id"));
            PageData userInfo = userDaoImpl.getUser(userName);
            if (userInfo == null) {
                return json.toJSONString(new WSPResult("用户不存在"));
            }

            // 检查是否时超级管理员
            if (!commonServiceHelper.checkIsAdmin(pd.get("uid"))) {
                LogUtils.info(this.getClass().getSimpleName(), "deleteUser", param, String.format("无权限的用户进行删除用户，用户为： %s ", pd.get("uid")));
                return LogUtils.error(this.getClass().getSimpleName(), "deleteUser", param, "您没有权限进行此操作", null);
            }


            PageData del = new PageData();
            del.put("isDel", 1);
            del.put("id", pd.get("id"));
            del.put("updateTime", WSPDate.getCurrentTimestemp());
            userDaoImpl.modifyUser(del);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "deleteUser", param, "删除用户失败", e);
        }
        return json.toJSONString(wspResult);
    }


    /**
     * 获取用户信息
     */
    public String getUser(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        try {
            // 检查账户是否存在
            PageData userName = new PageData();
            userName.put("id", pd.get("id"));
            PageData userInfo = userDaoImpl.getUser(userName);
            if (userInfo == null) {
                return json.toJSONString(new WSPResult("用户不存在"));
            }

            // 设置角色信息
            setRoleInfo(Long.parseLong(pd.get("id").toString()), userInfo);
            wspResult.setData(userInfo);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getUser", param, "获取用户信息失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取用户信息
     */
    public String getLoginUserInfo(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        try {
            // 检查账户是否存在
            PageData userName = new PageData();
            userName.put("id", pd.get("id"));
            PageData userInfo = userDaoImpl.getUser(userName);
            if (userInfo == null) {
                return json.toJSONString(new WSPResult("用户不存在"));
            }

            // 设置角色信息
            setRoleInfo(Long.parseLong(pd.get("id").toString()), userInfo);
            wspResult.setData(userInfo);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getLoginUserInfo", param, "获取当前登陆用户信息失败", e);
        }
        return json.toJSONString(wspResult);
    }


    /**
     * 获取用户列表信息
     */
    public String getUserList(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try {
            // 添加分页信息
            ParameterUtils.addPageInfo(pd);
            List<PageData> pageDataList = userDaoImpl.getUserList(pd);

            ParameterUtils.removePageInfo(pd);
            List<PageData> totalSize = userDaoImpl.getUserList(pd);

            // 查询角色信息
            for (PageData item: pageDataList) {
                setRoleInfo(Long.parseLong(item.get("id").toString()), item);
            }
            // 结果
            ResultPageModel re = new ResultPageModel();
            re.setTotalResult(pageDataList);
            re.setTotalSize(totalSize.size());
            wspResult.setData(re);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getUserList", param, "获取用户列表失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 传入一个用户id获取他的角色和权限信息
     */
    public void setRoleInfo(Long userId, PageData pd) {
        // 通过用户id查询角色信息
        PageData info  = new PageData();
        PageData roleInfo = new PageData();
        info.put("id", userId);
        List<PageData> rolePermissionList = new ArrayList<>();
        try {
            PageData userInfo = userDaoImpl.getUser(info);
            if (userInfo != null && userInfo.get("roleId") != null && !userInfo.get("roleId").toString().equals("")) {
                info.put("id", userInfo.get("roleId"));
                roleInfo = roleDaoImpl.getRole(info);

                // 获取权限信息
                if (roleInfo != null) {
                    PageData roleInPermission = new PageData();
                    roleInPermission.put("roleId", userInfo.get("roleId"));
                    List<PageData> rpList = roleInPermissionDaoImpl.getRoleInPermissionList(roleInPermission);
                    for (PageData item : rpList) {
                        PageData per = new PageData();
                        per.put("id", item.get("permissionId"));
                        per.put("exclude", 1);
                        PageData permission = permissionDaoImpl.getPermission(per);
                        rolePermissionList.add(permission);
                        if (permission != null) {
                            List<PageData> perlist = baseDao.getPidData("PermissionMapper.getPermissionList", permission.get("id").toString(), true);
                            permission.put("children", perlist);
                        }
                    }
                    roleInfo.put("permissionList", rolePermissionList);
                }
            }
        } catch (Exception e) {
            LogUtils.error(this.getClass().getSimpleName(), "getUserList", ""+userId+json.toJSONString(pd), "传入一个用户id获取他的角色和权限信息失败", e);
        }
        pd.put("role", roleInfo);
    }

    /**
     * 用户登陆
     */
    public String login(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        String result = ParameterUtils.checkParam(pd, "username", "password");
        if (result != null) {
            return result;
        }

        try{
            // 检查用户是否存在
            PageData userPd = new PageData();
            userPd.put("username", pd.get("username"));
            userPd.put("requiredPassword", "requiredPassword");
            List<PageData> userList = userDaoImpl.getUserList(userPd);
            if (userList == null || userList.size() == 0) {
                return LogUtils.error(this.getClass().getSimpleName(), "login", param, "用户名或密码错误", null);
            }

            PageData user = userList.get(0);
            // 验证密码
            if (!user.get("password").toString().equals(ParameterUtils.md5(pd.get("password").toString().toUpperCase()).toUpperCase())) {
                return LogUtils.error(this.getClass().getSimpleName(), "login", param, "用户名或密码错误", null);
            }

            // jwt信息
            wspResult.setCode(WSPCode.SUCCESS);
            wspResult.setMsg("登录成功");

            Map<String, Object> data = new HashMap<>();

            //生成jwt
            String uuid = this.get32UUID();
            Long uid = Long.parseLong(user.get("id").toString());
            String jwt = null;
            try{
                jwt = jwtService.createJWT(user.get("username").toString(), uid, uuid);
            } catch (Exception e) {
                return LogUtils.error(this.getClass().getSimpleName(), "login", param, "登陆异常，请稍后再试", e);
            }

            jwtService.saveJwt(jwt, uid, uuid, "", 1, null);
            data.put("jwt", jwt);
            wspResult.setData(data);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "login", param, "登陆失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 修改密码
     */
    public String changePassword(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        String checkResult = ParameterUtils.checkParam(pd, "uid", "oldPassword", "newPassword");
        if (checkResult != null) {
            return checkResult;
        }

        try {
            // 验证当前密码是否正确
            String oldPassword = pd.get("oldPassword").toString().toUpperCase();
            String newPassword = pd.get("newPassword").toString().toUpperCase();

            PageData user = new PageData();
            user.put("id", pd.get("uid"));
            user.put("requiredPassword", "requiredPassword");
            PageData userInfo = userDaoImpl.getUser(user);
            if (userInfo == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "changePassword", param, "用户不存在", null);
            }

            // 验证旧密码
            if (!ParameterUtils.md5(oldPassword).toUpperCase().equals(userInfo.get("password"))) {
                return LogUtils.error(this.getClass().getSimpleName(), "changePassword", param, "密码错误", null);
            }

            user.put("password", ParameterUtils.md5(newPassword).toUpperCase());
            userDaoImpl.modifyUser(user);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "changePassword", param, "修改密码失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 用户注销
     */
    public String userLogout(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try{
            redisService.remove(Const.JWT_PREF+pd.get("uid"));
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "userLogout", param, "用户注销失败", e);
        }
        return json.toJSONString(wspResult);
    }
}
