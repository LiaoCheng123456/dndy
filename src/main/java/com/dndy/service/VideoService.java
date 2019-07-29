package com.dndy.service;

import com.dndy.dao.ICountryDao;
import com.dndy.dao.IVideoDao;
import com.dndy.dao.IVideoInCountryDao;
import com.dndy.dao.IVideoInTypeDao;
import com.dndy.model.DataResult;
import com.dndy.model.MVideo;
import com.dndy.model.PageData;
import com.dndy.service.BaseService;
import com.dndy.util.LogUtils;
import com.dndy.util.ParameterUtils;
import com.wsp.core.WSPResult;
import com.wsp.utils.WSPDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "videoService")
public class VideoService extends BaseService {

    @Resource(name = "videoImpl")
    private IVideoDao videoDao;

    @Resource(name = "commonServiceHelper")
    private CommonServiceHelper commonServiceHelper;

    @Resource(name = "videoInTypeImpl")
    private IVideoInTypeDao videoInTypeDao;

    @Resource(name = "videoInCountryImpl")
    private IVideoInCountryDao videoInCountryDao;

    /**
     * 添加视频
     */
    public String addVideo(String param) {
        WSPResult dataResult = new WSPResult();
        PageData paramCheck = json.parseObject(param, PageData.class);

        // 检查不为空的参数
        String s = ParameterUtils.checkParam(paramCheck, "name", "showTime", "scenario", "videoTime", "typeId", "countryId");
        if (s != null) {
            return s;
        }

        // 处理数据
        try {
            Long id = this.getLongID();

            // 图片处理

            // 短视频处理

            // 国家处理
            if (paramCheck.get("countryId") != null) {
                if (commonServiceHelper.getCountryInfo(paramCheck.get("countryId")) == null) {
                    return LogUtils.error(this.getClass().getSimpleName(), "addVideo", param, "国家不存在", null);
                } else {
                    // 添加类型和国家关联
                    PageData videoInCountry = new PageData();
                    videoInCountry.put("id", this.getLongID());
                    videoInCountry.put("videoId", id);
                    videoInCountry.put("countryId", paramCheck.get("countryId"));
                    videoInCountryDao.addVideoInCountry(videoInCountry);
                }
            }

            // 类型处理
            if (paramCheck.get("typeId") != null) {
                if (commonServiceHelper.getVideoTypeInfo(paramCheck.get("typeId")) == null) {
                    return LogUtils.error(this.getClass().getSimpleName(), "addVideo", param, "类型不存在", null);
                } else {
                    // 添加类型和视频关联
                    PageData videoInType = new PageData();
                    videoInType.put("id", this.getLongID());
                    videoInType.put("videoId", id);
                    videoInType.put("typeId", paramCheck.get("typeId"));
                    videoInTypeDao.addVideoInType(videoInType);
                }
            }

            // 添加数据
            paramCheck.put("id", id);
            paramCheck.put("addTime", WSPDate.getCurrentTimestemp());
            paramCheck.put("updateTime", WSPDate.getCurrentTimestemp());

            videoDao.addVideo(paramCheck);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "addVideo", param, "添加视频失败", e);
        }
        return json.toJSONString(dataResult);
    }
}
