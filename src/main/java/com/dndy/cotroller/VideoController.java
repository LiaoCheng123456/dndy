package com.dndy.cotroller;

import com.dndy.model.MVideo;
import com.dndy.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @PostMapping(value = "addVideo", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    public String addVideo(HttpServletRequest request, @RequestBody String requestParam) {
        MVideo video = json.parseObject(requestParam, MVideo.class);
        video.setAddBy(request.getParameter("uid") == null ? null : Long.valueOf(request.getParameter("uid")));
        return videoService.addVideo(json.toJSONString(video));
    }
}
