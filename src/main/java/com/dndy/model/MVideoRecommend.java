package com.dndy.model;

import java.io.Serializable;

/**
 * 推荐视频
 */
public class MVideoRecommend implements Serializable {

    private static final long serialVersionUID = 6357869213649815390L;

    // id
    private Long id;

    // 视频id
    private Long videoId;

    // 开始时间
    private Long startTime;

    // 结束时间
    private Long endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
