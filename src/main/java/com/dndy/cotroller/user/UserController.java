package com.dndy.cotroller.user;

import com.dndy.cotroller.BaseController;
import com.dndy.interceptor.AuthJwt;
import com.dndy.model.PageData;
import com.dndy.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Resource(name = "userService")
    private UserService userservice;

    /**
     * 添加用户
     */
    @PostMapping(value = "addUser", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String addUser(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        pd.put("uid", request.getAttribute("uid"));
        return userservice.addUser(json.toJSONString(pd));
    }

    /**
     * 编辑用户
     */
    @PutMapping(value = "modifyUser", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String modifyUser(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        return userservice.modifyUser(json.toJSONString(pd));
    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "deleteUser", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String deleteUser(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        pd.put("uid", request.getAttribute("uid"));
        return userservice.deleteUser(json.toJSONString(pd));
    }


    /**
     * 获取用户信息
     */
    @GetMapping(value = "getUser", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getUser(HttpServletRequest request, @RequestParam(value = "id") Long userId) {
        PageData pd = new PageData();
        pd.put("id", userId);
        return userservice.getUser(json.toJSONString(pd));
    }

    /**
     * 获取当前登陆用户信息
     */
    @GetMapping(value = "getLoginUserInfo", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getLoginUserInfo(HttpServletRequest request) {
        PageData pd = new PageData();
        pd.put("id", request.getAttribute("uid"));
        return userservice.getLoginUserInfo(json.toJSONString(pd));
    }


    /**
     * 获取用户列表信息
     */
    @GetMapping(value = "getUserList", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getUserList(HttpServletRequest request,
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
        return userservice.getUserList(json.toJSONString(pd));
    }

    /**
     * 用户登陆
     */
    @PostMapping(value = "login", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    public String login(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        return userservice.login(json.toJSONString(pd));
    }

    /**
     * 修改密码
     */
    @PutMapping(value = "changePassword", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String changePassword(HttpServletRequest request, @RequestBody String requestParam) {
        PageData pd = json.parseObject(requestParam, PageData.class);
        pd.put("uid", request.getAttribute("uid"));
        return userservice.changePassword(json.toJSONString(pd));
    }

    /**
     * 用户注销
     */
    @PutMapping(value = "logout", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String userLogout(HttpServletRequest request) {
        PageData pd = new PageData();
        pd.put("uid", request.getAttribute("uid"));
        return userservice.userLogout(json.toJSONString(pd));
    }
}
