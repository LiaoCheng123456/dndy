package com.dndy.service;

import com.dndy.dao.ITypeDao;
import com.dndy.model.PageData;
import com.dndy.util.LogUtils;
import com.dndy.util.ParameterUtils;
import com.wsp.core.WSPResult;
import com.wsp.utils.WSPDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "videoTypeService")
public class VideoTypeService extends BaseService{

    @Resource(name = "typeImpl")
    private ITypeDao typeDao;

    /**
     * 添加视频类型
     */
    public String addType(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try{
            String result = ParameterUtils.checkParam(pd, "name");
            if (result != null) {
                return result;
            }

            // 检查名称是否重复
            PageData nameExists = new PageData();
            nameExists.put("name", pd.get("name"));
            PageData TypeBySchool = typeDao.getType(nameExists);
            if (TypeBySchool != null) {
                return LogUtils.error(this.getClass().getSimpleName(), "addType", param, "视频类型名重复", null);
            }

            pd.put("id", getLongID());
            pd.put("addBy", pd.get("uid"));
            pd.put("updateTime", WSPDate.getCurrentTimestemp());
            pd.put("addTime", WSPDate.getCurrentTimestemp());
            typeDao.addType(pd);
            wspResult.setData(pd.get("id"));
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "addType", param, "添加视频类型失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 修改视频类型
     */
    public String modifyType(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try{
            String result = ParameterUtils.checkParam(pd, "id", "name");
            if (result != null) {
                return result;
            }

            // 检查类型是否存在
            PageData sc = new PageData();
            sc.put("id", pd.get("id"));
            PageData TypeInfo = typeDao.getType(sc);
            if (TypeInfo == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "modifyType", param, "视频类型不存在", null);
            }

            // 检查名称是否重复
            PageData nameExists = new PageData();
            nameExists.put("name", pd.get("name"));
            PageData TypeBySchool = typeDao.getType(nameExists);
            if (TypeBySchool != null && !TypeBySchool.get("id").equals(pd.get("id"))) {
                return LogUtils.error(this.getClass().getSimpleName(), "modifyType", param, "视频类型名重复", null);
            }

            pd.put("updateTime", WSPDate.getCurrentTimestemp());
            typeDao.modifyType(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "modifyType", param, "修改视频类型失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取视频类型详情
     */
    public String getType(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try{
            String result = ParameterUtils.checkParam(pd, "id");
            if (result != null) {
                return result;
            }
            PageData Type = typeDao.getType(pd);
            wspResult.setData(Type);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getType", param, "获取视频类型详情失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取视频类型列表
     */
    public String getTypeList(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try{
            List<PageData> TypeBySchool = typeDao.getTypeList(pd);
            wspResult.setData(TypeBySchool);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getTypeList", param, "获取视频类型列表失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 删除视频类型
     */
    public String deleteType(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try{
            String result = ParameterUtils.checkParam(pd, "id");
            if (result != null) {
                return result;
            }
            pd.put("idDelete", 1);
            typeDao.modifyType(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "deleteType", param, "删除视频类型失败", e);
        }
        return json.toJSONString(wspResult);
    }

}
