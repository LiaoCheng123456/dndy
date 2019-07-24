package com.sdm.model;

import java.io.Serializable;

/**
 * 视频链接
 */
public class MVideoLink implements Serializable {

    private static final long serialVersionUID = 6357869213649815390L;
    // id
    private Long id;

    // 视频id
    private Long videoId;

    // 清晰度
    private String definition;

    // 来源
    private String source;

    // 类型 0 种子链接，1 播放链接， 2 预告链接'
    private Integer type;

    // 链接地址
    private String link;

    // 链接添加时间
    private Long addTime;

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

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
