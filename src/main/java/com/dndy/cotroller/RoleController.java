package com.dndy.cotroller;
import com.dndy.interceptor.AuthJwt;
import com.dndy.model.PageData;
import com.dndy.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 角色管理
 */
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Resource(name = "roleService")
    private RoleService roleService;

    /**
     * 添加角色
     */
    @PostMapping(value = "addRole", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String addRole(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        pd.put("uid", request.getAttribute("uid"));
        return roleService.addRole(json.toJSONString(pd));
    }

    /**
     * 编辑角色
     */
    @PutMapping(value = "modifyRole", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String modifyRole(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        return roleService.modifyRole(json.toJSONString(pd));
    }

    /**
     * 删除角色
     */
    @DeleteMapping(value = "deleteRole", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String deleteRole(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        return roleService.deleteRole(json.toJSONString(pd));
    }


    /**
     * 获取角色信息
     */
    @GetMapping(value = "getRole", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getRole(HttpServletRequest request, @RequestParam(value = "id") Long RoleId) {
        PageData pd = new PageData();
        pd.put("id", RoleId);
        return roleService.getRole(json.toJSONString(pd));
    }


    /**
     * 获取角色列表信息
     */
    @GetMapping(value = "getRoleList", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getRoleList(HttpServletRequest request,
                              @RequestParam(value = "roleId", required = false) Long roleId,
                              @RequestParam(value = "page", required = false) Long page,
                              @RequestParam(value = "limit", required = false) Long limit,
                              @RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(value = "startTime", required = false) Long startTime,
                              @RequestParam(value = "endTime", required = false) Long endTime
    ) {
        PageData pd = new PageData();
        pd.put("roleId", roleId);
        pd.put("page", page);
        pd.put("limit", limit);
        pd.put("keyword", keyword);
        pd.put("startTime", startTime);
        pd.put("endTime", endTime);
        return roleService.getRoleList(json.toJSONString(pd));
    }


    /**
     * 角色添加权限信息
     */
    @PostMapping(value = "roleAddPermission", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String roleAddPermission(HttpServletRequest request,
                                    @RequestBody String requestBody) {
        PageData pd = json.parseObject(requestBody, PageData.class);
        return roleService.roleAddPermission(json.toJSONString(pd));
    }

}
