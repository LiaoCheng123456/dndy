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


    /**
     * 编辑视频
     */
    @PostMapping(value = "modifyVideo", headers = "Accept=*/*", produces = "multipart/form-data")
    public String modifyVideo(HttpServletRequest request,
                           // 封面图片
                           @RequestParam("id") Long id,
                           // 封面图片
                           @RequestParam(value = "coverPathBody", required = false) MultipartFile coverPath,
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
                           @RequestParam(value = "name", required = false) String name,
                           // 上映时间
                           @RequestParam(value = "showTime", required = false) String showTime,
                           // 剧情介绍
                           @RequestParam(value = "scenario", required = false) String scenario,
                           // 视频时长
                           @RequestParam(value = "videoTime", required = false) String videoTime,
                           // 视频所属类型
                           @RequestParam(value = "typeId", required = false) Long typeId,
                           // 视频所属国家
                           @RequestParam(value = "countryId", required = false) Long countryId,
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
        video.put("id", id);
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
        return videoService.modifyVideo(json.toJSONString(video), mediaMap);
    }

    /**
     * 删除图片
     */
    @DeleteMapping(value = "deleteImage", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    public String deleteImage(HttpServletRequest request, @RequestBody String param) {
        PageData video = json.parseObject(param, PageData.class);
        return videoService.deleteImage(json.toJSONString(video));
    }

    /**
     * 获取视频详情
     */
    @GetMapping(value = "getVideo/{id}", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    public String getVideo(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        PageData video = new PageData();
        video.put("id", id);
        return videoService.getVideo(json.toJSONString(video));
    }

    /**
     * 获取视频列表
     */
    @GetMapping(value = "getVideoList", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    public String getVideo(HttpServletRequest request,
                           @RequestParam(value = "page", required = false) Long page,
                           @RequestParam(value = "limit", required = false) Long limit,
                           @RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "startTime", required = false) Long startTime,
                           @RequestParam(value = "endTime", required = false) Long endTime) {
        PageData video = new PageData();
        video.put("page", page);
        video.put("limit", limit);
        video.put("keyword", keyword);
        video.put("startTime", startTime);
        video.put("endTime", endTime);
        return videoService.getVideoList(json.toJSONString(video));
    }

    /**
     * 删除链接
     */
    @DeleteMapping(value = "deleteLink", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    public String deleteLink(HttpServletRequest request, @RequestBody String param) {
        PageData video = json.parseObject(param, PageData.class);
        return videoService.deleteLink(json.toJSONString(video));
    }

    /**
     * 添加链接
     */
    @PostMapping(value = "addVideoLink", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    public String addVideoLink(HttpServletRequest request, @RequestBody String param) {
        PageData video = json.parseObject(param, PageData.class);
        return videoService.addVideoLink(json.toJSONString(video));
    }


    /**
     * 编辑链接
     */
    @PutMapping(value = "modifyVideoLink", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    public String modifyVideoLink(HttpServletRequest request, @RequestBody String param) {
        PageData video = json.parseObject(param, PageData.class);
        return videoService.modifyVideoLink(json.toJSONString(video));
    }

    /**
     * 获取链接信息
     */
    @GetMapping(value = "getVideoLinkInfo", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    public String getVideoLink(HttpServletRequest request, @RequestParam(value = "id") Long id) {
        PageData video = new PageData();
        video.put("id", id);
        return videoService.getVideoLinkInfo(json.toJSONString(video));
    }

    /**
     * 获取列表信息
     */
    @GetMapping(value = "getVideoLinkList", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    public String getVideoLinkList(HttpServletRequest request, @RequestParam(value = "videoId") Long videoId) {
        PageData video = new PageData();
        video.put("videoId", videoId);
        return videoService.getVideoLinkList(json.toJSONString(video));
    }
}
