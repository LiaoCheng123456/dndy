package com.dndy.cotroller;

import com.dndy.model.MVideo;
import com.dndy.model.PageData;
import com.dndy.service.VideoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频信息控制器
 */
@RestController
@RequestMapping(value = "video")
public class VideoController extends BaseController {

    @Resource(name = "videoService")
    private VideoService videoService;

    /**
     * 添加视频
     */
    @PostMapping(value = "addVideo", headers = "Accept=*/*", produces = "multipart/form-data")
    public String addVideo(HttpServletRequest request,
                           // 封面图片
                           @RequestParam("coverPathBody") MultipartFile coverPath,
                           // 剧照海报图1
                           @RequestParam(value = "postersPath1Body", required = false) MultipartFile postersPath1,
                           // 剧照海报图2
                           @RequestParam(value = "postersPath2Body", required = false) MultipartFile postersPath2,
                           // 剧照海报图3
                           @RequestParam(value = "postersPath3Body", required = false) MultipartFile postersPath3,
                           // 剧照海报图4
                           @RequestParam(value = "postersPath4Body", required = false) MultipartFile postersPath4,
                           // 剧照海报图5
                           @RequestParam(value = "postersPath5Body", required = false) MultipartFile postersPath5,
                           // 介绍视频
                           @RequestParam(value = "videoContentPathBody", required = false) MultipartFile videoContentPath,
                           // 视频名称
                           @RequestParam("name") String name,
                           // 上映时间
                           @RequestParam("showTime") String showTime,
                           // 剧情介绍
                           @RequestParam("scenario") String scenario,
                           // 视频时长
                           @RequestParam("videoTime") String videoTime,
                           // 视频所属类型
                           @RequestParam("typeId") Long typeId,
                           // 视频所属国家
                           @RequestParam("countryId") Long countryId,
                           // 视频来源
                           @RequestParam(value = "source", required = false) String source,
                           // 视频清晰度
                           @RequestParam(value = "definition", required = false) String definition,
                           // 类型 0 种子链接，1 播放链接， 2 预告链接
                           @RequestParam(value = "type", required = false) Integer type,
                           // 链接地址
                           @RequestParam(value = "link", required = false) String link
                           ) {
        Map<String, MultipartFile> mediaMap = new HashMap<>();
        mediaMap.put("coverPathBody", coverPath);
        mediaMap.put("postersPath1Body", postersPath1);
        mediaMap.put("postersPath2Body", postersPath2);
        mediaMap.put("postersPath3Body", postersPath3);
        mediaMap.put("postersPath4Body", postersPath4);
        mediaMap.put("postersPath5Body", postersPath5);
        mediaMap.put("videoContentPathBody", videoContentPath);

        PageData video = new PageData();
        video.put("name", name);
        video.put("showTime", showTime);
        video.put("scenario", scenario);
        video.put("videoTime", videoTime);
        video.put("typeId", typeId);
        video.put("countryId", countryId);
        video.put("source", source);
        video.put("definition", definition);
        video.put("type", type);
        video.put("link", link);
        video.put("addBy", request.getParameter("uid"));
        video.put("mediaMap", mediaMap);
        return videoService.addVideo(json.toJSONString(video), mediaMap);
    }
}