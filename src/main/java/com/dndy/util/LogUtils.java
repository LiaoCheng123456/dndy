package com.dndy.util;

import com.alibaba.fastjson.JSON;
import com.dndy.model.DataResult;
import com.wsp.core.WSPResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日志处理
 */
public class LogUtils {


    private static Logger logger = LogManager.getLogger();

    /**
     * 记录info日志
     * @param className
     * 类名
     * @param methodName
     * 方法名
     * @param params
     * 请求参数
     * @param remark
     * 备注
     */
    public static void info(String className, String methodName, String params, String remark) {
        logger.info(String.format("类名：%s ，方法名：%s，请求参数：%s，备注：%s", className, methodName, params, remark));
    }


    /**
     * 记录error日志
     * @param className
     * 类名
     * @param methodName
     * 方法名
     * @param params
     * 请求参数
     * @param remark
     * 备注
     * @param errorMsg
     * 错误信息
     */
    public static String error(String className, String methodName, String params, String remark, Exception errorMsg) {
        if (errorMsg != null) {
            errorMsg.printStackTrace();
        }
        logger.error(String.format("类名：%s ，方法名：%s，请求参数：%s，备注：%s，异常信息为：%s", className, methodName, params, remark, errorMsg));
        return JSON.toJSONString(new WSPResult(remark));
    }
}
