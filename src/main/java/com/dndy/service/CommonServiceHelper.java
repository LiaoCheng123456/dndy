package com.dndy.service;

import com.dndy.dao.*;
import com.dndy.model.PageData;
import com.dndy.util.LogUtils;
import com.dndy.util.ParameterUtils;
import com.dndy.util.PropertiesUtil;
import com.wsp.utils.WSPDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Map;

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
     */
    public void handlingFile(PageData pd, Map<String, MultipartFile> mediaMap) throws IOException {
        if (pd == null) {
            return;
        }

        // 封面图片
        handlingCoverImage(pd, mediaMap);

        // 剧照海报图片
        handlingPostersImage(pd, mediaMap);

        // 视频介绍内容
        handlingVideoContent(pd, mediaMap);

        dataPersistence(pd);

    }

    /**
     * 统一将添加好的视频相关持久化链接存储到数据库
     */
    public void dataPersistence(PageData pd) {
        // 封面图片
        if (pd.get("coverPath") != null) {
            PageData cover = new PageData();
            cover.put("id", this.getLongID());
            cover.put("url", pd.get("coverPath"));
            cover.put("type", 0);
            imageDao.addImage(cover);
        }

        // 剧照海报图片1
        if (pd.get("postersPath1") != null) {
            PageData posters = new PageData();
            posters.put("id", this.getLongID());
            posters.put("url", pd.get("postersPath1"));
            posters.put("type", 1);
            imageDao.addImage(posters);
        }

        // 剧照海报图片2
        if (pd.get("postersPath2") != null) {
            PageData posters = new PageData();
            posters.put("id", this.getLongID());
            posters.put("url", pd.get("postersPath2"));
            posters.put("type", 1);
            imageDao.addImage(posters);
        }

        // 剧照海报图片3
        if (pd.get("postersPath3") != null) {
            PageData posters = new PageData();
            posters.put("id", this.getLongID());
            posters.put("url", pd.get("postersPath3"));
            posters.put("type", 1);
            imageDao.addImage(posters);
        }

        // 剧照海报图片4
        if (pd.get("postersPath4") != null) {
            PageData posters = new PageData();
            posters.put("id", this.getLongID());
            posters.put("url", pd.get("postersPath4"));
            posters.put("type", 1);
            imageDao.addImage(posters);
        }

        // 剧照海报图片5
        if (pd.get("postersPath5") != null) {
            PageData posters = new PageData();
            posters.put("id", this.getLongID());
            posters.put("url", pd.get("postersPath5"));
            posters.put("type", 1);
            imageDao.addImage(posters);
        }

        // 视频介绍内容,只有预告链接有这个
        if (pd.get("videoContentPath") != null) {
            PageData videoLink = new PageData();
            videoLink.put("id", this.getLongID());
            videoLink.put("videoId", pd.get("videoId"));
            videoLink.put("definition", pd.get("definition"));
            videoLink.put("source", pd.get("source"));
            videoLink.put("type", pd.get("type"));
            videoLink.put("link", pd.get("link"));
            videoLink.put("addTime", WSPDate.getCurrentTimestemp());
            videoLinkDao.addVideoLink(videoLink);
        }
    }

    /**
     * 处理视频内容
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
                    pd.put("videoContentPathBody", fileAllName);
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
}
