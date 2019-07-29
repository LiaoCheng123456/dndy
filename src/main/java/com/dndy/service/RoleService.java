package com.dndy.service;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dndy.dao.*;
import com.dndy.model.PageData;
import com.dndy.model.ResultPageModel;
import com.dndy.service.BaseService;
import com.dndy.util.LogUtils;
import com.dndy.util.ParameterUtils;
import com.wsp.core.WSPResult;
import com.wsp.utils.WSPDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class RoleService extends BaseService {
    @Resource(name = "roleDaoImpl")
    private IRoleDao roleDaoImpl;

    @Resource(name = "userDaoImpl")
    private IUserDao userDao;

    @Resource(name = "roleInPermissionDaoImpl")
    private IRoleInPermissionDao roleInPermissionDaoImpl;

    @Resource(name = "permissionDaoImpl")
    private IPermissionDao permissionDaoImpl;

    @Resource(name = "baseDao")
    private BaseDao baseDao;

    /**
     * 添加角色
     */
    public String addRole(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        // 请求参数验证
        String checkResult = ParameterUtils.checkParam(pd, "roleName", "isAdmin");

        if (checkResult != null) {
            return checkResult;
        }

        try {

            // 检查账户是否存在
            PageData roleName = new PageData();
            roleName.put("roleName", pd.get("roleName"));
            PageData userInfo = roleDaoImpl.getRole(roleName);
            if (userInfo != null) {
                return json.toJSONString(new WSPResult("角色名已存在"));
            }

            pd.put("id", getLongID());
            pd.put("addBy", pd.get("uid"));
            pd.put("addTime", WSPDate.getCurrentTimestemp());
            pd.put("updateTime", WSPDate.getCurrentTimestemp());
            roleDaoImpl.addRole(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "addRole", param, "添加角色失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 编辑角色
     */
    public String modifyRole(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        // 请求参数验证
        String checkResult = ParameterUtils.checkParam(pd, "id");

        if (checkResult != null) {
            return checkResult;
        }
        try {
            // 检查角色是否存在
            PageData role = new PageData();
            role.put("id", pd.get("id"));
            PageData roleInfo = roleDaoImpl.getRole(role);
            if (roleInfo == null) {
                return json.toJSONString(new WSPResult("角色不存在"));
            }
            pd.put("updateTime", WSPDate.getCurrentTimestemp());
            roleDaoImpl.modifyRole(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "modifyRole", param, "编辑角色失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 删除角色
     */
    @Transactional
    public String deleteRole(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        // 请求参数验证
        String checkResult = ParameterUtils.checkParam(pd, "id");

        if (checkResult != null) {
            return checkResult;
        }
        try {
            // 检查账户是否存在
            PageData role = new PageData();
            role.put("id", pd.get("id"));
            PageData roleInfo = roleDaoImpl.getRole(role);
            if (roleInfo == null) {
                return json.toJSONString(new WSPResult("角色不存在"));
            }

            // 检查角色是否有人使用
            PageData user = new PageData();
            user.put("roleId", pd.get("id"));
            List<PageData> userInfoList = userDao.getUserList(user);
            if (userInfoList != null && userInfoList.size() > 0) {
                return json.toJSONString(new WSPResult("角色使用中，无法删除"));
            }
            roleDaoImpl.deleteRole(pd);
            roleInPermissionDaoImpl.deleteRoleInPermission(user);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return LogUtils.error(this.getClass().getSimpleName(), "deleteRole", param, "删除角色失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取角色信息
     */
    public String getRole(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        try {
            // 检查账户是否存在
            PageData role = new PageData();
            role.put("id", pd.get("id"));
            PageData roleInfo = roleDaoImpl.getRole(role);
            if (roleInfo == null) {
                return json.toJSONString(new WSPResult("角色不存在"));
            }

            setRolePermission(role.get("id"), roleInfo);
            // 查询权限信息
            wspResult.setData(roleInfo);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getRole", param, "获取角色信息失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取角色列表
     */
    public String getRoleList(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try {
            // 添加分页信息
            ParameterUtils.addPageInfo(pd);
            List<PageData> pageDataList = roleDaoImpl.getRoleList(pd);

            ParameterUtils.removePageInfo(pd);
            List<PageData> totalSize = roleDaoImpl.getRoleList(pd);

            // 查询权限信息
            for (PageData item : pageDataList) {
                setPermission(item.get("id"), item, true);
            }
            // 结果
            ResultPageModel re = new ResultPageModel();
            re.setTotalResult(pageDataList);
            re.setTotalSize(totalSize.size());
            wspResult.setData(re);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getRoleList", param, "获取角色列表失败", e);
        }
        return json.toJSONString(wspResult, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 传入一个角色id 获取权限
     */
    public void setPermission(Object roleId, PageData pd, boolean requiredAllPermission) {
        // 获取权限信息
//        List<PageData> rolePermissionList = new ArrayList<>();
        List<PageData> permissionAllList = new ArrayList<>();
        PageData roleInPermission = new PageData();
        roleInPermission.put("roleId", roleId);
        try {
            List<PageData> rpList = roleInPermissionDaoImpl.getRoleInPermissionList(roleInPermission);
            for (PageData item : rpList) {
                PageData per = new PageData();
                per.put("id", item.get("permissionId"));
                PageData permission = permissionDaoImpl.getPermission(per);

                // 是否需要将所有的权限列表展到一起
                if (requiredAllPermission) {
                    PageData newPermission = new PageData();
                    newPermission.putAll(permission);
                    permissionAllList.add(newPermission);
                }
//                rolePermissionList.add(permission);
//                rolePermissionList.add(permission);
                List<PageData> perlist = baseDao.getPidData("PermissionMapper.getPermissionList", permission.get("id").toString(), false);

                // 是否需要将所有的权限列表展到一起
                if (requiredAllPermission) {
                    addPermission(perlist, permissionAllList);
                }

                permission.put("children", perlist);
            }
        } catch (Exception e) {
            LogUtils.error(this.getClass().getSimpleName(), "getUserList", "" + roleId + json.toJSONString(pd), "传入一个用户id获取他的角色和权限信息失败", e);
        }
        if (requiredAllPermission) {
            pd.put("permissionAllList", permissionAllList);
        }
//        pd.put("permissionList", rolePermissionList);
    }


    /**
     * 设置角色拥有的权限
     */
    public void setRolePermission(Object roleId, PageData pd) {
        List<PageData> permissionAllList = new ArrayList<>();
        PageData roleInPermission = new PageData();
        roleInPermission.put("roleId", roleId);
        try {
            List<PageData> rpList = roleInPermissionDaoImpl.getRoleInPermissionList(roleInPermission);
            for (PageData item : rpList) {
                PageData per = new PageData();
                per.put("id", item.get("permissionId"));
                PageData permission = permissionDaoImpl.getPermission(per);
                permissionAllList.add(permission);
            }
        } catch (Exception e) {
            LogUtils.error(this.getClass().getSimpleName(), "getUserList", "" + roleId + json.toJSONString(pd), "传入一个用户id获取他的角色和权限信息失败", e);
        }
        pd.put("permissionAllList", permissionAllList);
    }

    /**
     * 将新的权限数据放到一个列表里面
     * @param perlist
     * @param permissionAllList
     */
    private void addPermission(List<PageData> perlist, List<PageData> permissionAllList) {
        for (int i = 0; i < perlist.size(); i++) {
            PageData newPermission = new PageData();
            newPermission.putAll(perlist.get(i));
            permissionAllList.add(newPermission);
            if (newPermission.get("children") != null) {
                addPermission(json.parseArray(json.toJSONString(newPermission.get("children")), PageData.class), permissionAllList);
            }
        }
    }

    /**
     * 角色添加权限信息
     */
    @Transactional
    public String roleAddPermission(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        String result = ParameterUtils.checkParam(pd, "roleId", "permissionIdList");
        if (result != null) {
            return result;
        }

        try {
            // 删除角色原对应的权限
            PageData rolePd = new PageData();
            rolePd.put("roleId", pd.get("roleId"));
            roleInPermissionDaoImpl.deleteRoleInPermission(rolePd);

            // 添加新的权限
            List<Long> permissionIdList = json.parseArray(json.toJSONString(pd.get("permissionIdList")), Long.class);
            for (Long item : permissionIdList) {
                PageData pdItem = new PageData();
                pdItem.put("roleId", pd.get("roleId"));
                pdItem.put("permissionId", item);
                roleInPermissionDaoImpl.addRoleInPermission(pdItem);

                // 检查权限是否是子级权限,如果是，就自动将父级权限加上，并且要验证传过来的权限里面没有该父级权限的id
                PageData permissionModil = new PageData();
                permissionModil.put("id", item);
                PageData permission = permissionDaoImpl.getPermission(permissionModil);
                if (permission != null
                        && !permission.get("pid").toString().equals("0")
                        && !permissionIdList.contains(Long.parseLong(permission.get("pid").toString()))) {
                    PageData superModel = new PageData();
                    superModel.put("roleId", pd.get("roleId"));
                    superModel.put("permissionId", permission.get("pid"));
                    List<PageData> roleInPermissionList = roleInPermissionDaoImpl.getRoleInPermissionList(superModel);
                    if (roleInPermissionList == null || roleInPermissionList.size() == 0) {
                        roleInPermissionDaoImpl.addRoleInPermission(superModel);
                    }
                }
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return LogUtils.error(this.getClass().getSimpleName(), "roleAddPermission", param, "角色添加权限信息失败", e);
        }
        return json.toJSONString(wspResult);
    }
}
