package com.dndy.cotroller;
import com.dndy.interceptor.AuthJwt;
import com.dndy.model.PageData;
import com.dndy.service.BaseService;
import com.dndy.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限管理
 */
@RestController
@RequestMapping("permission")
public class PermissionController extends BaseService {

    @Resource(name = "permissionService")
    private PermissionService permissionService;

    /**
     * 添加权限
     */
    @PostMapping(value = "addPermission", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String addPermission(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        pd.put("uid", request.getAttribute("uid"));
        return permissionService.addPermission(json.toJSONString(pd));
    }

    /**
     * 编辑权限
     */
    @PutMapping(value = "modifyPermission", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String modifyPermission(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        return permissionService.modifyPermission(json.toJSONString(pd));
    }

    /**
     * 删除权限
     */
    @DeleteMapping(value = "deletePermission", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String deletePermission(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        return permissionService.deletePermission(json.toJSONString(pd));
    }


    /**
     * 获取权限信息
     */
    @GetMapping(value = "getPermission", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getPermission(HttpServletRequest request, @RequestParam(value = "id") Long permissionId) {
        PageData pd = new PageData();
        pd.put("id", permissionId);
        return permissionService.getPermission(json.toJSONString(pd));
    }


    /**
     * 获取权限列表信息
     */
    @GetMapping(value = "getPermissionList", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getPermissionList(HttpServletRequest request,
                                    @RequestParam(value = "permissionId", required = false) Long permissionId,
                                    @RequestParam(value = "page", required = false) Long page,
                                    @RequestParam(value = "limit", required = false) Long limit,
                                    @RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "startTime", required = false) Long startTime,
                                    @RequestParam(value = "endTime", required = false) Long endTime
    ) {
        PageData pd = new PageData();
        pd.put("permissionId", permissionId);
        pd.put("page", page);
        pd.put("limit", limit);
        pd.put("keyword", keyword);
        pd.put("startTime", startTime);
        pd.put("endTime", endTime);
        return permissionService.getPermissionList(json.toJSONString(pd));
    }

}
