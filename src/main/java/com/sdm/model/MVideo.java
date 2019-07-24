package com.sdm.model;

import java.io.Serializable;

/**
 * 基础bese
 */
public class MVideo implements Serializable {

    private static final long serialVersionUID = 6357869213649815390L;
    // id
    private Long id;

    // 电影名
    private String name;

    // 英文名
    private String nameEng;

    // 上映时间
    private Long showTime;

    // 剧情介绍
    private String scenario;

    // 电影时长
    private Long videoTime;

    // 电影封面图片
    private Long videoCoverId;

    // 是否删除
    private Integer isDelete;

    // 是否已经更新到网站上面
    private Integer isUpdate;

    // 点击数
    private Long clickNumber;

    // 是否取得版权
    private Integer isGetCopyright;

    // 电影上架时间
    private Long addTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public Long getShowTime() {
        return showTime;
    }

    public void setShowTime(Long showTime) {
        this.showTime = showTime;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public Long getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Long videoTime) {
        this.videoTime = videoTime;
    }

    public Long getVideoCoverId() {
        return videoCoverId;
    }

    public void setVideoCoverId(Long videoCoverId) {
        this.videoCoverId = videoCoverId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Long getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Long clickNumber) {
        this.clickNumber = clickNumber;
    }

    public Integer getIsGetCopyright() {
        return isGetCopyright;
    }

    public void setIsGetCopyright(Integer isGetCopyright) {
        this.isGetCopyright = isGetCopyright;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
