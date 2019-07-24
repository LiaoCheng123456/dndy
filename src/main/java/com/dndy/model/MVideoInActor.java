package com.dndy.model;

import java.io.Serializable;

public class MVideoInActor implements Serializable {

    private static final long serialVersionUID = 6357869213649815390L;
    private Long id;

    private Long videoId;

    private Long actorId;

    private Long sort;

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

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}
