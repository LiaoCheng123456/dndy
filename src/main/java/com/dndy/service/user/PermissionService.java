package com.dndy.service.user;

import com.dndy.dao.IPermissionDao;
import com.dndy.model.PageData;
import com.dndy.model.ResultPageModel;
import com.dndy.service.BaseService;
import com.dndy.util.LogUtils;
import com.dndy.util.ParameterUtils;
import com.wsp.core.WSPResult;
import com.wsp.utils.WSPDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "permissionService")
public class PermissionService extends BaseService {
    @Resource(name = "permissionDaoImpl")
    private IPermissionDao permissionDaoImpl;

    /**
     * 添加权限
     */
    public String addPermission(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        // 请求参数验证
        String checkResult = ParameterUtils.checkParam(pd, "name", "value", "pid");

        if (checkResult != null) {
            return checkResult;
        }

        try {

            // 检查权限名是否存在
            PageData permissionName = new PageData();
            permissionName.put("name", pd.get("name"));
            PageData userInfo = permissionDaoImpl.getPermission(permissionName);
            if (userInfo != null) {
                return json.toJSONString(new WSPResult("权限名已存在"));
            }

            pd.put("id", getLongID());
            pd.put("addBy", pd.get("uid"));
            pd.put("addTime", WSPDate.getCurrentTimestemp());
            pd.put("updateTime", WSPDate.getCurrentTimestemp());
            permissionDaoImpl.addPermission(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "addPermission", param, "添加权限失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 编辑权限
     */
    public String modifyPermission(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        // 请求参数验证
        String checkResult = ParameterUtils.checkParam(pd, "id");

        if (checkResult != null) {
            return checkResult;
        }
        try {
            // 检查权限是否存在
            PageData permission = new PageData();
            permission.put("id", pd.get("id"));
            PageData permissionInfo = permissionDaoImpl.getPermission(permission);
            if (permissionInfo == null) {
                return json.toJSONString(new WSPResult("权限不存在"));
            }
            pd.put("updateTime", WSPDate.getCurrentTimestemp());
            permissionDaoImpl.modifyPermission(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "modifyPermission", param, "编辑权限失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 删除权限
     */
    public String deletePermission(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        // 请求参数验证
        String checkResult = ParameterUtils.checkParam(pd, "id");

        if (checkResult != null) {
            return checkResult;
        }
        try {
            // 检查账户是否存在
            PageData permission = new PageData();
            permission.put("id", pd.get("id"));
            PageData permissionInfo = permissionDaoImpl.getPermission(permission);
            if (permissionInfo == null) {
                return json.toJSONString(new WSPResult("权限不存在"));
            }

            permissionDaoImpl.deletePermission(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "deletepermission", param, "删除权限失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取权限信息
     */
    public String getPermission(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);

        try {
            // 检查账户是否存在
            PageData permission = new PageData();
            permission.put("id", pd.get("id"));
            PageData permissionInfo = permissionDaoImpl.getPermission(permission);
            if (permissionInfo == null) {
                return json.toJSONString(new WSPResult("权限不存在"));
            }
            wspResult.setData(permissionInfo);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getpermission", param, "获取权限信息失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取权限列表
     */
    public String getPermissionList(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try {
            // 添加分页信息
            ParameterUtils.addPageInfo(pd);
            List<PageData> pageDataList = permissionDaoImpl.getPermissionList(pd);

            ParameterUtils.removePageInfo(pd);
            List<PageData> totalSize = permissionDaoImpl.getPermissionList(pd);
            ResultPageModel re = new ResultPageModel();
            re.setTotalResult(pageDataList);
            re.setTotalSize(totalSize.size());
            wspResult.setData(re);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getPermissionList", param, "获取权限列表失败", e);
        }
        return json.toJSONString(wspResult);
    }

}
