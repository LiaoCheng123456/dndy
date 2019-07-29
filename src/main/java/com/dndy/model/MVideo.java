package com.dndy.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

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
    private String showTime;

    // 剧情介绍
    private String scenario;

    // 电影时长
    private String videoTime;

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
    private int addTime;

    // 修改时间
    private int updateTime;

    // 添加人
    private Long addBy;

    // 类型
    private Long typeId;

    // 国家
    private Long countryId;

    // 视频相关信息
    private List<Object>  imageList;

    private MVideoLink videoLink;

    public MVideoLink getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(MVideoLink videoLink) {
        this.videoLink = videoLink;
    }

    public List<Object> getImageList() {
        return imageList;
    }

    public void setImageList(List<Object> imageList) {
        this.imageList = imageList;
    }

    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getAddBy() {
        return addBy;
    }

    public void setAddBy(Long addBy) {
        this.addBy = addBy;
    }

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

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
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

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }
}
