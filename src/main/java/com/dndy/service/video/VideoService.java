package com.dndy.service.video;

import com.dndy.dao.IVideoDao;
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

            MVideo video = json.parseObject(param, MVideo.class);

            // 图片处理

            // 短视频处理

            // 国家处理

            // 类型处理

            // 添加数据
            Long id = this.getLongID();
            video.setId(id);
            video.setAddTime(WSPDate.getCurrentTimestemp());
            video.setUpdateTime(WSPDate.getCurrentTimestemp());

            videoDao.addVideo(video);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "addVideo", param, "添加视频失败", e);
        }
        return json.toJSONString(dataResult);
    }
}
