package com.dndy.service;

import com.dndy.dao.*;
import com.dndy.model.PageData;
import com.dndy.util.LogUtils;
import com.dndy.util.ParameterUtils;
import com.dndy.util.PropertiesUtil;
import com.wsp.core.WSPResult;
import com.wsp.utils.WSPDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(value = "commonServiceHelper")
public class CommonServiceHelper extends BaseService {

    @Resource(name = "userDaoImpl")
    private IUserDao userDao;

    @Resource(name = "roleDaoImpl")
    private IRoleDao roleDaoImpl;

    @Resource(name = "countryImpl")
    private ICountryDao countryDao;

    @Resource(name = "typeImpl")
    private ITypeDao typeDao;

    @Resource(name = "videoLinkImpl")
    private IVideoLinkDao videoLinkDao;

    @Resource(name = "imageImpl")
    private IImageDao imageDao;

    @Resource(name = "videoInCountryImpl")
    private IVideoInCountryDao videoInCountryDao;

    @Resource(name = "videoInTypeImpl")
    private IVideoInTypeDao videoInTypeDao;

    /**
     * 传入用户id，验证是否是超级管理员
     */
    public boolean checkIsAdmin(Object uid) {
        if (uid == null) {
            throw new NullPointerException("uid不能为空");
        }
        PageData user = new PageData();
        user.put("id", uid);
        PageData userInfo = userDao.getUser(user);
        if (userInfo == null) {
            LogUtils.error(this.getClass().getSimpleName(), "checkIsAdmin", uid.toString(), "用户不存在", null);
            throw new NullPointerException("用户不存在");
        }
        PageData roleId = getRoleInfo(userInfo.get("roleId"));
        if (roleId != null) {
            return roleId.get("isAdmin") != null && roleId.get("isAdmin").toString().equals("1");
        } else {
            return false;
        }
    }

    /**
     * 检查角色是否存在
     */
    public PageData getRoleInfo(Object roleId) {
        PageData role = new PageData();
        role.put("id", roleId);
        return roleDaoImpl.getRole(role);
    }

    /**
     * 获取国家信息
     */
    public PageData getCountryInfo(Object countryId) {
        PageData country = new PageData();
        country.put("id", countryId);
        return countryDao.getCountry(country);
    }

    /**
     * 获取类型信息
     */
    public PageData getVideoTypeInfo(Object countryId) {
        PageData typeInfo = new PageData();
        typeInfo.put("id", countryId);
        return typeDao.getType(typeInfo);
    }

    /**
     * 处理pd里面的文件
     * updateOrInsert 新增还是修改 true 新增 false 修改
     */
    public void handlingFile(PageData pd, Map<String, MultipartFile> mediaMap, boolean updateOrInsert) throws IOException {
        if (pd == null) {
            return;
        }

        // 封面图片
        handlingCoverImage(pd, mediaMap);

        // 剧照海报图片
        handlingPostersImage(pd, mediaMap);

        // 视频介绍内容
        handlingVideoContent(pd, mediaMap);

        // 数据持久化
        if (updateOrInsert) {
            insertDataPersistence(pd);
        } else {
            updateDataPersistence(pd);
        }

    }

    /**
     * 编辑数据库中的数据
     */
    private void updateDataPersistence(PageData pd) {
        String serverPath = PropertiesUtil.GetValueByKey("paths.properties", "serverPath");
        // 封面图片
        if (pd.get("coverPath") != null) {
            // 查询原有的图片
            PageData cover = new PageData();
            cover.put("videoId", pd.get("id"));
            cover.put("type", 0);
            PageData image = imageDao.getImage(cover);
            if (image != null) {
                // 删除服务器上原有的图片
                deleteFile(PropertiesUtil.GetValueByKey("paths.properties", "coverPath"), getImageName(image.get("url").toString()));
            }

            // 删除数据库中的文件
            imageDao.deleteImage(cover);

            // 新增新的图片
            cover.put("id", this.getLongID());
            cover.put("url", serverPath + "cover/" + pd.get("coverPath"));
            cover.put("addTime", WSPDate.getCurrentTimestemp());

            pd.put("videoCoverId", cover.get("id"));
            imageDao.addImage(cover);
        }

        // 剧照海报图片1
        if (pd.get("postersPath1") != null) {
            // 查询原有的图片
            PageData posters = new PageData();
            posters.put("videoId", pd.get("id"));
            posters.put("type", 1);
            posters.put("sort", 1);
            PageData image = imageDao.getImage(posters);
            if (image != null) {
                // 删除服务器上原有的图片
                deleteFile(PropertiesUtil.GetValueByKey("paths.properties", "postersPath"), getImageName(image.get("url").toString()));
            }

            // 删除数据库中的文件
            imageDao.deleteImage(posters);

            // 新增新的图片
            posters.put("id", this.getLongID());
            posters.put("url", serverPath + "posters/" + pd.get("postersPath1"));
            posters.put("addTime", WSPDate.getCurrentTimestemp());
            imageDao.addImage(posters);
        }

        // 剧照海报图片2
        if (pd.get("postersPath2") != null) {
            // 查询原有的图片
            PageData posters = new PageData();
            posters.put("videoId", pd.get("id"));
            posters.put("type", 1);
            posters.put("sort", 2);
            PageData image = imageDao.getImage(posters);
            if (image != null) {
                // 删除服务器上原有的图片
                deleteFile(PropertiesUtil.GetValueByKey("paths.properties", "postersPath"), getImageName(image.get("url").toString()));
            }

            // 删除数据库中的文件
            imageDao.deleteImage(posters);

            // 新增新的图片
            posters.put("id", this.getLongID());
            posters.put("url", serverPath + "posters/" + pd.get("postersPath2"));
            posters.put("addTime", WSPDate.getCurrentTimestemp());
            imageDao.addImage(posters);
        }

        // 剧照海报图片3
        if (pd.get("postersPath3") != null) {
            // 查询原有的图片
            PageData posters = new PageData();
            posters.put("videoId", pd.get("id"));
            posters.put("type", 1);
            posters.put("sort", 3);
            PageData image = imageDao.getImage(posters);
            if (image != null) {
                // 删除服务器上原有的图片
                deleteFile(PropertiesUtil.GetValueByKey("paths.properties", "postersPath"), getImageName(image.get("url").toString()));
            }

            // 删除数据库中的文件
            imageDao.deleteImage(posters);

            // 新增新的图片
            posters.put("id", this.getLongID());
            posters.put("url", serverPath + "posters/" + pd.get("postersPath3"));
            posters.put("addTime", WSPDate.getCurrentTimestemp());
            imageDao.addImage(posters);
        }

        // 剧照海报图片4
        if (pd.get("postersPath4") != null) {
            // 查询原有的图片
            PageData posters = new PageData();
            posters.put("videoId", pd.get("id"));
            posters.put("type", 1);
            posters.put("sort", 4);
            PageData image = imageDao.getImage(posters);
            if (image != null) {
                // 删除服务器上原有的图片
                deleteFile(PropertiesUtil.GetValueByKey("paths.properties", "postersPath"), getImageName(image.get("url").toString()));
            }

            // 删除数据库中的文件
            imageDao.deleteImage(posters);

            // 新增新的图片
            posters.put("id", this.getLongID());
            posters.put("url", serverPath + "posters/" + pd.get("postersPath4"));
            posters.put("addTime", WSPDate.getCurrentTimestemp());
            imageDao.addImage(posters);
        }

        // 剧照海报图片5
        if (pd.get("postersPath5") != null) {
            // 查询原有的图片
            PageData posters = new PageData();
            posters.put("videoId", pd.get("id"));
            posters.put("type", 1);
            posters.put("sort", 5);
            PageData image = imageDao.getImage(posters);
            if (image != null) {
                // 删除服务器上原有的图片
                deleteFile(PropertiesUtil.GetValueByKey("paths.properties", "postersPath"), getImageName(image.get("url").toString()));
            }

            // 删除数据库中的文件
            imageDao.deleteImage(posters);

            // 新增新的图片
            posters.put("id", this.getLongID());
            posters.put("url", serverPath + "posters/" + pd.get("postersPath5"));
            posters.put("addTime", WSPDate.getCurrentTimestemp());
            imageDao.addImage(posters);
        }

        // 视频介绍内容,只有预告链接有这个
        if (pd.get("videoContentPath") != null) {
            PageData videoLink = new PageData();
            videoLink.put("id", this.getLongID());
            videoLink.put("videoId", pd.get("id"));
            videoLink.put("definition", pd.get("definition"));
            videoLink.put("source", pd.get("source"));
            videoLink.put("type", pd.get("type"));
            videoLink.put("link", serverPath + "videoContent/" + pd.get("videoContentPath"));
            videoLink.put("addTime", WSPDate.getCurrentTimestemp());
            videoLinkDao.addVideoLink(videoLink);
        }
    }

    /**
     * 获取链接中图片的名字
     *
     * @param param
     * @return
     */
    public String getImageName(String param) {
        String[] split = param.split("/");
        return split[split.length - 1];
    }

    public static void main(String[] ar) {
        System.out.println(new CommonServiceHelper().getImageName("http://www.dndy.com/dndystatic/posters/770DB5D40DBF2C07DF20F394B2D1C9F4.jpg"));
    }

    /**
     * 添加到数据库
     */
    private void insertDataPersistence(PageData pd) {
        String serverPath = PropertiesUtil.GetValueByKey("paths.properties", "serverPath");
        // 封面图片
        if (pd.get("coverPath") != null) {
            PageData cover = new PageData();
            cover.put("id", this.getLongID());
            cover.put("videoId", pd.get("id"));
            cover.put("url", serverPath + "cover/" + pd.get("coverPath"));
            cover.put("type", 0);
            cover.put("addTime", WSPDate.getCurrentTimestemp());

            // 设置视频封面id
            pd.put("videoCoverId", cover.get("id"));
            imageDao.addImage(cover);
        }

        // 剧照海报图片1
        if (pd.get("postersPath1") != null) {
            PageData posters = new PageData();
            posters.put("id", this.getLongID());
            posters.put("videoId", pd.get("id"));
            posters.put("url", serverPath + "posters/" + pd.get("postersPath1"));
            posters.put("type", 1);
            posters.put("addTime", WSPDate.getCurrentTimestemp());
            posters.put("sort", 1);
            imageDao.addImage(posters);
        }

        // 剧照海报图片2
        if (pd.get("postersPath2") != null) {
            PageData posters = new PageData();
            posters.put("id", this.getLongID());
            posters.put("videoId", pd.get("id"));
            posters.put("url", serverPath + "posters/" + pd.get("postersPath2"));
            posters.put("type", 1);
            posters.put("addTime", WSPDate.getCurrentTimestemp());
            posters.put("sort", 2);
            imageDao.addImage(posters);
        }

        // 剧照海报图片3
        if (pd.get("postersPath3") != null) {
            PageData posters = new PageData();
            posters.put("id", this.getLongID());
            posters.put("videoId", pd.get("id"));
            posters.put("url", serverPath + "posters/" + pd.get("postersPath3"));
            posters.put("type", 1);
            posters.put("addTime", WSPDate.getCurrentTimestemp());
            posters.put("sort", 3);
            imageDao.addImage(posters);
        }

        // 剧照海报图片4
        if (pd.get("postersPath4") != null) {
            PageData posters = new PageData();
            posters.put("id", this.getLongID());
            posters.put("videoId", pd.get("id"));
            posters.put("url", serverPath + "posters/" + pd.get("postersPath4"));
            posters.put("type", 1);
            posters.put("addTime", WSPDate.getCurrentTimestemp());
            posters.put("sort", 4);
            imageDao.addImage(posters);
        }

        // 剧照海报图片5
        if (pd.get("postersPath5") != null) {
            PageData posters = new PageData();
            posters.put("id", this.getLongID());
            posters.put("videoId", pd.get("id"));
            posters.put("url", serverPath + "posters/" + pd.get("postersPath5"));
            posters.put("type", 1);
            posters.put("addTime", WSPDate.getCurrentTimestemp());
            posters.put("sort", 5);
            imageDao.addImage(posters);
        }

        // 视频介绍内容,只有预告链接有这个
        if (pd.get("videoContentPath") != null) {
            PageData videoLink = new PageData();
            videoLink.put("id", this.getLongID());
            videoLink.put("videoId", pd.get("id"));
            videoLink.put("definition", pd.get("definition"));
            videoLink.put("source", pd.get("source"));
            videoLink.put("type", pd.get("type"));
            videoLink.put("link", serverPath + "videoContent/" + pd.get("videoContentPath"));
            videoLink.put("addTime", WSPDate.getCurrentTimestemp());
            videoLinkDao.addVideoLink(videoLink);
        }
    }

    /**
     * 处理视频内容
     * 只有预告片会存入，其他链接不存入
     *
     * @param pd
     */
    private void handlingVideoContent(PageData pd, Map<String, MultipartFile> mediaMap) throws IOException {
        // 视频介绍内容
        if (mediaMap.get("videoContentPathBody") != null) {
            // 如果是播放链接
            if (pd.get("type") != null && pd.get("type").toString().equals("2")) {
                String coverPath = PropertiesUtil.GetValueByKey("paths.properties", "videoContentPath");
                MultipartFile multipartFile = mediaMap.get("videoContentPathBody");
                InputStream inputStream = multipartFile.getInputStream();
                String fileName = ParameterUtils.generatorAPPID(multipartFile.getOriginalFilename().split("\\.")[0]);
                String fileAllName = fileName + "." + multipartFile.getOriginalFilename().split("\\.")[1];
                if (uploadFile(inputStream, coverPath, fileAllName)) {
                    pd.put("videoContentPath", fileAllName);
                }
            }
        }
    }

    /**
     * 处理剧照海报图片
     *
     * @param pd
     * @throws IOException
     */
    private void handlingPostersImage(PageData pd, Map<String, MultipartFile> mediaMap) throws IOException {

        // 剧照海报图片1
        if (mediaMap.get("postersPath1Body") != null) {
            String coverPath = PropertiesUtil.GetValueByKey("paths.properties", "postersPath");
            MultipartFile multipartFile = mediaMap.get("postersPath1Body");
            InputStream inputStream = multipartFile.getInputStream();
            String fileName = ParameterUtils.generatorAPPID(multipartFile.getOriginalFilename().split("\\.")[0]);
            String fileAllName = fileName + "." + multipartFile.getOriginalFilename().split("\\.")[1];
            if (uploadFile(inputStream, coverPath, fileAllName)) {
                pd.put("postersPath1", fileAllName);
            }
        }

        // 剧照海报图片2
        if (mediaMap.get("postersPath2Body") != null) {
            String coverPath = PropertiesUtil.GetValueByKey("paths.properties", "postersPath");
            MultipartFile multipartFile = mediaMap.get("postersPath2Body");
            InputStream inputStream = multipartFile.getInputStream();
            String fileName = ParameterUtils.generatorAPPID(multipartFile.getOriginalFilename().split("\\.")[0]);
            String fileAllName = fileName + "." + multipartFile.getOriginalFilename().split("\\.")[1];
            if (uploadFile(inputStream, coverPath, fileAllName)) {
                pd.put("postersPath2", fileAllName);
            }
        }

        // 剧照海报图片3
        if (mediaMap.get("postersPath3Body") != null) {
            String coverPath = PropertiesUtil.GetValueByKey("paths.properties", "postersPath");
            MultipartFile multipartFile = mediaMap.get("postersPath3Body");
            InputStream inputStream = multipartFile.getInputStream();
            String fileName = ParameterUtils.generatorAPPID(multipartFile.getOriginalFilename().split("\\.")[0]);
            String fileAllName = fileName + "." + multipartFile.getOriginalFilename().split("\\.")[1];
            if (uploadFile(inputStream, coverPath, fileAllName)) {
                pd.put("postersPath3", fileAllName);
            }
        }

        // 剧照海报图片4
        if (mediaMap.get("postersPath4Body") != null) {
            String coverPath = PropertiesUtil.GetValueByKey("paths.properties", "postersPath");
            MultipartFile multipartFile = mediaMap.get("postersPath4Body");
            InputStream inputStream = multipartFile.getInputStream();
            String fileName = ParameterUtils.generatorAPPID(multipartFile.getOriginalFilename().split("\\.")[0]);
            String fileAllName = fileName + "." + multipartFile.getOriginalFilename().split("\\.")[1];
            if (uploadFile(inputStream, coverPath, fileAllName)) {
                pd.put("postersPath4", fileAllName);
            }
        }

        // 剧照海报图片5
        if (mediaMap.get("postersPath5Body") != null) {
            String coverPath = PropertiesUtil.GetValueByKey("paths.properties", "postersPath");
            MultipartFile multipartFile = mediaMap.get("postersPath5Body");
            InputStream inputStream = multipartFile.getInputStream();
            String fileName = ParameterUtils.generatorAPPID(multipartFile.getOriginalFilename().split("\\.")[0]);
            String fileAllName = fileName + "." + multipartFile.getOriginalFilename().split("\\.")[1];
            if (uploadFile(inputStream, coverPath, fileAllName)) {
                pd.put("postersPath5", fileAllName);
            }
        }
    }

    /**
     * 处理封面图片
     *
     * @param mediaMap
     * @throws IOException
     */
    private void handlingCoverImage(PageData pd, Map<String, MultipartFile> mediaMap) throws IOException {
        // 封面图片
        if (mediaMap.get("coverPathBody") != null) {
            String coverPath = PropertiesUtil.GetValueByKey("paths.properties", "coverPath");
            MultipartFile multipartFile = mediaMap.get("coverPathBody");
            InputStream inputStream = multipartFile.getInputStream();
            String fileName = ParameterUtils.generatorAPPID(multipartFile.getOriginalFilename().split("\\.")[0]);
            String fileAllName = fileName + "." + multipartFile.getOriginalFilename().split("\\.")[1];
            if (uploadFile(inputStream, coverPath, fileAllName)) {
                pd.put("coverPath", fileAllName);
            }
        }
    }

    /**
     * 服务器上传文件
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public boolean uploadFile(InputStream file, String filePath, String fileName) {
        boolean flag = true;
        //new一个文件对象实例
        File targetFile = new File(filePath);
        //如果当前文件目录不存在就自动创建该文件或者目录
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            //通过文件流实现文件上传
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + fileName);
            byte[] b = new byte[file.available()];
            int n;
            while ((n = file.read(b)) != -1) {
                fileOutputStream.write(b, 0, n);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            flag = false;
        } catch (IOException ioException) {
            flag = false;
        }
        return flag;
    }

    /**
     * 服务器删除文件
     */
    public static boolean deleteFile(String filePath, String fileName) {
        boolean delete_flag = false;
        File file = new File(filePath + fileName);
        if (file.exists() && file.isFile() && file.delete()) {
            delete_flag = true;
        } else {
            delete_flag = false;
        }
        return delete_flag;
    }

    /**
     * 删除图片
     */
    public String deleteImage(Object imageId) {
        if (imageId == null) {
            return json.toJSONString(new WSPResult("图片不能id为空"));
        }

        // 获取图片信息
        PageData img = new PageData();
        img.put("id", imageId);
        PageData image = imageDao.getImage(img);
        if (image == null) {
            return json.toJSONString(new WSPResult("图片不存在" + imageId));
        } else {
            // 获取类型
            String type = image.get("type").toString();
            if (type.equals("0")) {
                // 删除服务器上面的封面图片
                deleteFile(PropertiesUtil.GetValueByKey("paths.properties", "coverPath"), getImageName(image.get("url").toString()));
            }

            if (type.equals("1")) {
                deleteFile(PropertiesUtil.GetValueByKey("paths.properties", "postersPath"), getImageName(image.get("url").toString()));
            }

            // 删除数据库数据
            imageDao.deleteImage(img);
        }
        return null;
    }

    /**
     * 转换视频详情信息
     *
     * @param video
     */
    public void parseVideoInfo(PageData video) {
        // 转换封面图片信息
        setImageInfo(video);

        // 转换剧照图片信息
        setPostersInfo(video);

        // 转换国家
        setCountryInfo(video);

        // 转换类型
        setTypeInfo(video);

        // 视频链接
        setVideoLink(video);
    }

    /**
     * 获取封面图片信息
     */
    private void setImageInfo(PageData video) {
        PageData videoCover = new PageData();
        videoCover.put("id", video.get("videoCoverId"));
        PageData image = imageDao.getImage(videoCover);
        if (image != null) {
            video.put("videoCover", image);
        }
    }

    /**
     * 获取剧照图片信息
     */
    private void setPostersInfo(PageData video) {
        Long videoId = Long.parseLong(video.get("id").toString());

        // 获取关联数据为剧照类型的图片
        PageData posters = new PageData();
        posters.put("videoId", videoId);
        posters.put("type", 1);
        List<PageData> imageList = imageDao.getImageList(posters);
        video.put("postersList", imageList);
    }

    /**
     * 查询国家信息
     */
    private void setCountryInfo(PageData video) {
        Long videoId = Long.parseLong(video.get("id").toString());
        PageData country = new PageData();
        country.put("videoId", videoId);
        PageData videoInCountry = videoInCountryDao.getVideoInCountry(country);
        if (videoInCountry != null) {
            // 获取国家信息
            PageData countryInfo = new PageData();
            countryInfo.put("id", videoInCountry.get("countryId"));
            video.put("country", countryDao.getCountry(countryInfo));
        }
    }

    /**
     * 查询所属类型
     */
    private void setTypeInfo(PageData video) {
        Long videoId = Long.parseLong(video.get("id").toString());
        PageData type = new PageData();
        type.put("videoId", videoId);
        List<PageData> videoTypeList = new ArrayList<>();
        List<PageData> videoInTypeList = videoInTypeDao.getVideoInTypeList(type);
        for (PageData item : videoInTypeList) {
            PageData typeInfo = new PageData();
            typeInfo.put("id", item.get("typeId"));
            videoTypeList.add(typeDao.getType(typeInfo));
        }
        video.put("videoTypeList", videoTypeList);
    }

    /**
     * 视频链接
     */
    private void setVideoLink(PageData video) {
        Long videoId = Long.parseLong(video.get("id").toString());
        PageData type = new PageData();
        type.put("videoId", videoId);

        HashMap map = new HashMap();

        // 种子列表
        List<PageData> seedLink = new ArrayList<>();

        // 在线播放链接
        List<PageData> onLinePlayLink = new ArrayList<>();

        // 预告链接
        List<PageData> foreshowLink = new ArrayList<>();
        List<PageData> videoLinkList = videoLinkDao.getVideoLinkList(type);
        for (int i = 0; i < videoLinkList.size(); i++) {
            // 按照不同的类型放在不同的列表里面

            // 种子
            if (videoLinkList.get(i).get("type").toString().equals("0")) {
                seedLink.add(videoLinkList.get(i));
                continue;
            }

            // 在线播放
            if (videoLinkList.get(i).get("type").toString().equals("1")) {
                onLinePlayLink.add(videoLinkList.get(i));
                continue;
            }

            // 预告
            if (videoLinkList.get(i).get("type").toString().equals("2")) {
                foreshowLink.add(videoLinkList.get(i));
            }
        }

        map.put("seedLink", seedLink);
        map.put("onLinePlayLink", onLinePlayLink);
        map.put("foreshowLink", foreshowLink);
        video.put("linkList", map);
    }
}