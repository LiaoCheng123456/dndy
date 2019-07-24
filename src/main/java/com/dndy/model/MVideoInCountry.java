package com.dndy.model;

import java.io.Serializable;

/**
 * 电影所属国家
 */
public class MVideoInCountry implements Serializable {

    private static final long serialVersionUID = 6357869213649815390L;
    // id
    private Long id;

    // 电影id
    private Long videoId;

    // 国家id
    private Long countryId;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
