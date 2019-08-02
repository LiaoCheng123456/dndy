package com.dndy.service;
import com.dndy.dao.*;
import com.dndy.model.PageData;
import com.dndy.model.ResultPageModel;
import com.dndy.util.LogUtils;
import com.dndy.util.ParameterUtils;
import com.dndy.util.PropertiesUtil;
import com.wsp.core.WSPResult;
import com.wsp.utils.WSPDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "videoService")
public class VideoService extends BaseService {

    @Resource(name = "videoImpl")
    private IVideoDao videoDao;

    @Resource(name = "commonServiceHelper")
    private CommonServiceHelper commonServiceHelper;

    @Resource(name = "videoInTypeImpl")
    private IVideoInTypeDao videoInTypeDao;

    @Resource(name = "videoInCountryImpl")
    private IVideoInCountryDao videoInCountryDao;

    @Resource(name = "videoLinkImpl")
    private IVideoLinkDao videoLinkDao;

    @Resource(name = "clickImpl")
    private IClickDao clickDao;

    /**
     * 添加视频
     */
    @Transactional
    public String addVideo(String param, Map<String, MultipartFile> mediaMap) {
        WSPResult dataResult = new WSPResult();
        PageData paramCheck = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "addVideo", param, "添加视频信息");

        // 检查不为空的参数
        String s = ParameterUtils.checkParam(paramCheck, "name", "showTime", "scenario", "videoTime", "typeId", "countryId");
        if (s != null) {
            return s;
        }

        // 处理数据
        try {
            Long id = this.getLongID();
            paramCheck.put("id", id);

            // 国家处理
            if (paramCheck.get("countryId") != null) {
                if (commonServiceHelper.getCountryInfo(paramCheck.get("countryId")) == null) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return LogUtils.error(this.getClass().getSimpleName(), "addVideo", param, "国家不存在", null);
                } else {
                    // 添加类型和国家关联
                    PageData videoInCountry = new PageData();
                    videoInCountry.put("id", this.getLongID());
                    videoInCountry.put("videoId", id);
                    videoInCountry.put("countryId", paramCheck.get("countryId"));
                    videoInCountryDao.addVideoInCountry(videoInCountry);
                }
            }

            // 类型处理
            if (paramCheck.get("typeId") != null) {
                if (commonServiceHelper.getVideoTypeInfo(paramCheck.get("typeId")) == null) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return LogUtils.error(this.getClass().getSimpleName(), "addVideo", param, "类型不存在", null);
                } else {
                    // 添加类型和视频关联
                    PageData videoInType = new PageData();
                    videoInType.put("id", this.getLongID());
                    videoInType.put("videoId", id);
                    videoInType.put("typeId", paramCheck.get("typeId"));
                    videoInTypeDao.addVideoInType(videoInType);
                }
            }

            // 图片处理短视频处理
            commonServiceHelper.handlingFile(paramCheck, mediaMap, true);

            // 添加数据
            paramCheck.put("addTime", WSPDate.getCurrentTimestemp());
            paramCheck.put("updateTime", WSPDate.getCurrentTimestemp());
            videoDao.addVideo(paramCheck);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return LogUtils.error(this.getClass().getSimpleName(), "addVideo", param, "添加视频失败", e);
        }
        return json.toJSONString(dataResult);
    }


    /**
     * 编辑视频
     */
    @Transactional
    public String modifyVideo(String param, Map<String, MultipartFile> mediaMap) {
        WSPResult dataResult = new WSPResult();
        PageData paramCheck = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "modifyVideo", param, "编辑视频信息");

        // 检查不为空的参数
        String s = ParameterUtils.checkParam(paramCheck, "id");
        if (s != null) {
            return s;
        }

        try {
            // 检查视频是否存在
            PageData video = new PageData();
            video.put("id", paramCheck.get("id"));
            PageData videoInfo = videoDao.getVideo(video);
            if (videoInfo == null) {
                LogUtils.error(this.getClass().getSimpleName(), "modifyVideo", param, "视频信息不存在", null);
            }

            // 国家处理
            if (paramCheck.get("countryId") != null) {
                if (commonServiceHelper.getCountryInfo(paramCheck.get("countryId")) == null) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return LogUtils.error(this.getClass().getSimpleName(), "modifyVideo", param, "国家不存在", null);
                } else {
                    // 删除原有国家关联
                    PageData videoInCountry = new PageData();
                    videoInCountry.put("videoId", paramCheck.get("id"));
                    videoInCountryDao.deleteVideoInCountry(videoInCountry);

                    // 添加类型和国家关联
                    videoInCountry.put("id", this.getLongID());
                    videoInCountry.put("countryId", paramCheck.get("countryId"));
                    videoInCountryDao.addVideoInCountry(videoInCountry);
                }
            }

            // 类型处理
            if (paramCheck.get("typeId") != null) {
                if (commonServiceHelper.getVideoTypeInfo(paramCheck.get("typeId")) == null) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return LogUtils.error(this.getClass().getSimpleName(), "modifyVideo", param, "类型不存在", null);
                } else {
                    // 删除类型和视频关联
                    PageData videoInType = new PageData();
                    videoInType.put("videoId", paramCheck.get("id"));
                    videoInTypeDao.deleteVideoInType(videoInType);
                    // 添加类型和视频关联

                    videoInType.put("id", this.getLongID());
                    videoInType.put("typeId", paramCheck.get("typeId"));
                    videoInTypeDao.addVideoInType(videoInType);
                }
            }

            // 图片处理短视频处理
            commonServiceHelper.handlingFile(paramCheck, mediaMap, false);

            // 编辑数据
            paramCheck.put("addTime", WSPDate.getCurrentTimestemp());
            paramCheck.put("updateTime", WSPDate.getCurrentTimestemp());
            videoDao.modifyVideo(paramCheck);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return LogUtils.error(this.getClass().getSimpleName(), "modifyVideo", param, "编辑视频失败", e);
        }
        return json.toJSONString(dataResult);
    }

    /**
     * 删除图片
     * @param param
     * @return
     */
    @Transactional
    public String deleteImage(String param) {
        WSPResult dataResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "deleteImage", param, "删除图片信息");

        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "id");
        if (s != null) {
            return s;
        }

        try {
            // 查询视频下面有没有该图片, 检查视频是否和图片是否存在
            String id = commonServiceHelper.deleteImage(pd.get("id"));
            if (id != null) {
                return id;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return LogUtils.error(this.getClass().getSimpleName(), "deleteImage", param, "删除图片失败", e);
        }
        return json.toJSONString(dataResult);
    }

    /**
     * 获取视频详情
     * @param param
     * @return
     */
    @Transactional
    public String getVideo(String param) {
        WSPResult dataResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "getVideo", param, "获取视频详情信息");

        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "id");
        if (s != null) {
            return s;
        }

        try {
            PageData video = videoDao.getVideo(pd);
            if (video == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "getVideo", param, "视频信息不存在", null);
            }

            commonServiceHelper.parseVideoInfo(video);

            // 记录点击量
            PageData click = new PageData();
            click.put("id", this.getLongID());
            click.put("videoId", pd.get("id"));
            click.put("clickTime", WSPDate.getCurrentTimestemp());
            clickDao.addClick(click);

            dataResult.setData(video);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return LogUtils.error(this.getClass().getSimpleName(), "getVideo", param, "获取视频详情失败", e);
        }
        return json.toJSONString(dataResult);
    }


    /**
     * 获取视频列表
     * @param param
     * @return
     */
    public String getVideoList(String param) {
        WSPResult dataResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "getVideoList", param, "获取视频列表信息");

        try {
            // 添加分页信息
            ParameterUtils.addPageInfo(pd);
            List<PageData> videoList = videoDao.getVideoListByClickNumberDesc(pd);

            for (int i = 0; i < videoList.size(); i++) {
                commonServiceHelper.parseVideoInfo(videoList.get(i));
            }

            ParameterUtils.removePageInfo(pd);
            List<PageData> videoSize = videoDao.getVideoListByClickNumberDesc(pd);
            // 结果
            ResultPageModel re = new ResultPageModel();
            re.setTotalResult(videoList);
            re.setTotalSize(videoSize.size());
            dataResult.setData(re);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getVideoList", param, "获取视频列表失败", e);
        }
        return json.toJSONString(dataResult);
    }

    /**
     * 删除链接
     * @param param
     * @return
     */
    @Transactional
    public String deleteLink(String param) {
        WSPResult dataResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "deleteLink", param, "删除链接信息");

        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "id", "videoId");
        if (s != null) {
            return s;
        }

        try {
            // 查询链接是什么类型的
            PageData link = new PageData();
            link.put("id", pd.get("id"));
            PageData videoLink = videoLinkDao.getVideoLink(link);
            if (videoLink == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "deleteLink", param, "链接不存在", null);
            }

            // 删除视频下面的链接
            videoLinkDao.deleteVideoLink(pd);

            // 删除服务器上面的文件
            if (videoLink.get("type").toString().equals("2")) {
                commonServiceHelper.deleteFile(PropertiesUtil.GetValueByKey("paths.properties", "videoContentPath"),
                        commonServiceHelper.getImageName(videoLink.get("link").toString()));
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return LogUtils.error(this.getClass().getSimpleName(), "deleteLink", param, "删除链接失败", e);
        }
        return json.toJSONString(dataResult);
    }

    /**
     * 添加链接
     * @param param
     * @return
     */
    @Transactional
    public String addVideoLink(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "addVideoLink", param, "添加链接信息");
        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "link", "type", "videoId");
        if (s != null) {
            return s;
        }

        try {
            // 检查视频是否存在
            PageData video = new PageData();
            video.put("id", pd.get("videoId"));
            PageData videoInfo = videoDao.getVideo(video);
            if (videoInfo == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "addVideoLink", param, "视频信息不存在", null);
            }

            pd.put("id", this.getLongID());
            pd.put("addTime", WSPDate.getCurrentTimestemp());
            videoLinkDao.addVideoLink(pd);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return LogUtils.error(this.getClass().getSimpleName(), "addVideoLink", param, "添加链接失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 修改链接
     * @param param
     * @return
     */
    @Transactional
    public String modifyVideoLink(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "modifyVideoLink", param, "编辑链接信息");
        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "id", "videoId");
        if (s != null) {
            return s;
        }

        try {
            // 检查id是否存在
            PageData link = new PageData();
            link.put("id", pd.get("id"));
            PageData videoLink = videoLinkDao.getVideoLink(link);
            if (videoLink == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "modifyVideoLink", param, "链接不存在", null);
            }

            pd.put("addTime", WSPDate.getCurrentTimestemp());
            videoLinkDao.modifyVideoLink(pd);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return LogUtils.error(this.getClass().getSimpleName(), "modifyVideoLink", param, "编辑链接失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取视频信息
     * @param param
     * @return
     */
    public String getVideoLinkInfo(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "getVideoLinkInfo", param, "获取链接信息");
        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "id");
        if (s != null) {
            return s;
        }

        try {
            // 检查id是否存在
            PageData link = new PageData();
            link.put("id", pd.get("id"));
            PageData videoLink = videoLinkDao.getVideoLink(link);
            if (videoLink == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "getVideoLinkInfo", param, "链接不存在", null);
            }

            wspResult.setData(videoLink);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getVideoLinkInfo", param, "获取链接信息失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取视频信息列表
     * @param param
     * @return
     */
    public String getVideoLinkList(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "getVideoLinkList", param, "获取视频信息列表");
        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "videoId");
        if (s != null) {
            return s;
        }

        try {
            // 检查视频是否存在
            PageData video = new PageData();
            video.put("id", pd.get("videoId"));
            PageData videoInfo = videoDao.getVideo(video);
            if (videoInfo == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "modifyVideoLink", param, "视频信息不存在", null);
            }

            List<PageData> videoLinkList = videoLinkDao.getVideoLinkList(pd);
            wspResult.setData(videoLinkList);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getVideoLinkList", param, "获取视频信息列表失败", e);
        }
        return json.toJSONString(wspResult);
    }
}
