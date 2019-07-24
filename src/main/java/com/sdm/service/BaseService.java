package com.sdm.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wsp.utils.WSPGetID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基础service类
 *
 * @author rex
 */
public class BaseService {

    protected Logger logger = LogManager.getLogger();

    private static final long serialVersionUID = 6357869213649815390L;

    /**
     * 得到long型ID
     *
     * @return
     */
    public long getLongID() {

        return WSPGetID.getLongID();
    }
    /**
     *
     * @return
     */
    public String get32UUID() {

        return WSPGetID.getUUID32();
    }
    /**
     * 得到超长long型ID
     *
     * @return
     */
    public long getBigLongID() {

        return WSPGetID.getBigLongID();
    }

    /**
     * 将long型时间转化String型
     *
     */

    public String longTimeToString( long time){

        return this.transferLongToDate("yyyy-MM-dd HH:mm:ss",time);
    }

    /**
     * 将String型时间转化long型
     *
     */

    public long stringTimeToLong( String time)throws ParseException {

        return this.transferDateToLong("yyyy-MM-dd",time);

    }

    /**
     2      * 把long 转换成 日期 再转换成String类型
     3      */

    public String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }


    /**
     * 把String转换成 date日期 再转换成Long类型
     */
    public Long transferDateToLong(String dateFormat, String time) throws ParseException {
        DateFormat format = new SimpleDateFormat(dateFormat);
        Date date =format.parse(time);
        long timeLong = date.getTime();
        return Long.valueOf(timeLong);

    }
    /**
     * @param fastjson
     */
    protected JSONObject json = new JSONObject();

    /**
     * fastjson JSONArray
     */
    protected JSONArray jsonArray = new JSONArray();

}
