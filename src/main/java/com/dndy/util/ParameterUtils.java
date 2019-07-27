package com.dndy.util;

import com.alibaba.fastjson.JSONObject;
import com.dndy.model.PageData;
import com.wsp.core.WSPCode;
import com.wsp.core.WSPResult;
import com.wsp.utils.WSPDate;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterUtils {

    /**
     * @param fastjson
     */
    private static JSONObject json = new JSONObject();

    /**
     * 验证多个参数是否为空，并返回为空参数
     * @param pageData
     * @param args
     * @return
     */
    public static String checkParam(PageData pageData, String... args) {
        WSPResult wspResult = new WSPResult();
        if (pageData == null) {
            wspResult.setCode(WSPCode.SUCESS_OPERATE);
            wspResult.setMsg(WSPCode.SUCESS_OPERATE_STR);
            return json.toJSONString(wspResult);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("参数丢失");
        for (int i = 0; args != null && i < args.length; i++) {
            if (pageData.get(args[i]) == null) {
                sb.append(String.format("%s,", args[i]));
            }
        }

        if (sb.toString().length() == 4) {
            return null;
        } else {
            wspResult.setCode(WSPCode.SUCESS_OPERATE);
            wspResult.setMsg(sb.toString());
            return json.toJSONString(wspResult);
        }
    }

    /**
     * 分页转换
     * @param start
     * 				起始页
     * @param limit
     * 				结束位置
     * @return
     * 			{@code page[0] 起始页}
     * 			{@code page[1] 结束位置}
     *
     */
    public static Integer[] parsePage(Object start, Object limit) {
        Integer[] page = new Integer[2];

        // 如果页码有一方为null，那么都置为null，因为不能确定取值范围
        if ((start == null || "".equals(start)) || (limit == null || "".equals(limit))) {
            page[0] = null;
            page[1] = null;
            return page;
        }
        Integer s = Integer.parseInt(start.toString());
        Integer e = Integer.parseInt(limit.toString());
        page[0] = s <= 0 ? 0 : (s - 1) * e;
        page[1] = e;
        return page;
    }


    /**
     * 给pageData添加分页信息
     * @return
     * @throws Exception
     */
    public static void addPageInfo(PageData pd) {
        Integer[] pages = parsePage(pd.get("page"), pd.get("limit"));
        pd.put("start", pages[0]);
        pd.put("limit", pages[1]);
    }

    /**
     * 给pageData删除分页信息
     * @return
     * @throws Exception
     */
    public static void removePageInfo(PageData pd) {
        pd.put("start", null);
        pd.put("limit", null);
    }


    /**
     *
     * @param result
     *  调用服务返回值
     * @param serviceName
     * 服务名
     * @param requestParam
     * 请求参数
     * @return
     * 服务调用返回值信息
     * @throws Exception
     * 服务调用异常信息
     */
    public static PageData requestResultHandle(String result, String serviceName, String requestParam) throws Exception{
        if (result == null) {
            throw new Exception(String.format("%s 服务返回值为 %s, 请求参数为 %s",serviceName, result, requestParam));
        }

        // 转换成HashMap
        PageData pageData = json.parseObject(result, PageData.class);
        if (pageData != null && String.valueOf(pageData.get("code")).equals(WSPCode.SUCCESS)) {
            return pageData;
        } else {
            throw new Exception(String.format("%s 服务调用失败，返回值为 %s, 请求参数为 %s", serviceName, result, requestParam));
        }
    }

    /**
     * hash MD5
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (string.isEmpty()) {
            return "";
        }
        StringBuffer buf = null;
        MessageDigest md5 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    public static boolean getResult(Object...objects) {
        for(int i=0;objects!=null&&i<objects.length;i++) {
            if(objects[i] == null ||(objects[i].toString().isEmpty())){
                return true;
            }
        }
        return false;
    }

    /**
     * 生成AppID
     */
    public static String generatorAPPID(String value) {
        String key = value + WSPDate.getCurrentTimestemp();
        return md5(key).toUpperCase();
    }

    /**
     * 生成AppSecret
     */
    public static String generatorAPPSECRET(String value) {
        Random random = new Random();
        int randint =(int)Math.floor(random.nextDouble() * 9999);
        String temp = value + randint + WSPDate.getCurrentTimestemp();
        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
        return new String(encoder.encode(md5(temp).getBytes()));
    }

    /**
     * base64
     */
    public static String parseBase64(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    /**
     * 验证字符串是否包含特殊字符
     * @param param
     */
    public static String checkSpecialParam(PageData pd, String... param) {
        WSPResult wspResult = new WSPResult();
        StringBuilder sb = new StringBuilder();
        sb.append("不能包含特殊字符");
        for (int i = 0; i < param.length; i++) {
            String regEx="[`~!@#$%^&*()+=| {}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern p=Pattern.compile(regEx);
            Matcher m=p.matcher(pd.get(param[i]).toString());
            if (m.find()) {
                sb.append(param[i]).append(" ");
            }
        }

        if (sb.toString().length() == 8) {
            return null;
        } else {
            wspResult.setCode(WSPCode.SUCESS_OPERATE);
            wspResult.setMsg(sb.toString());
            return json.toJSONString(wspResult);
        }
    }
}
