package com.sdm.service.cloud.interceptor;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.sdm.common.util.api.jwt.IJwtService;
import com.sdm.service.cloud.model.PageData;
import com.wsp.core.WSPCode;
import com.wsp.core.WSPResult;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    WSPResult wspResult = new WSPResult();
    JSONObject json = new JSONObject();

    @Reference(version = "1.0.0", check = false)
    IJwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Access注解
        AuthJwt access = method.getAnnotation(AuthJwt.class);
        if (access == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        String[] values = access.value();
        String jwt = httpServletRequest.getHeader("Authorization");
        if (jwt != null && !jwt.equals("")) {
            String reslut = jwtService.authJwt(jwt);
            if (reslut != null && !reslut.equals("")) {
                PageData result = json.parseObject(reslut, PageData.class);
                if (result.get("uid") != null && !(result.get("uid").toString()).equals("")) {
                    httpServletRequest.setAttribute("uid", result.get("uid"));
                    httpServletRequest.setAttribute("reData", result);
                    return true;
                }
            }
        }
        // 拦截之后应该返回公共结果, 这里没做处理
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        wspResult.setCode(WSPCode.FAIL_AUTH);
        wspResult.setMsg("没有权限");
        writer.print(json.toJSONString(wspResult));
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
