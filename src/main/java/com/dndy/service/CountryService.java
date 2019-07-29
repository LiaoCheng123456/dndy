package com.dndy.service;

import com.dndy.dao.ICountryDao;
import com.dndy.model.PageData;
import com.dndy.util.LogUtils;
import com.dndy.util.ParameterUtils;
import com.wsp.core.WSPResult;
import com.wsp.utils.WSPDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "countryService")
public class CountryService extends BaseService{

    @Resource(name = "countryImpl")
    private ICountryDao countryImpl;

    /**
     * 添加国家
     */
    public String addCountry(String param) {
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
            PageData CountryBySchool = countryImpl.getCountry(nameExists);
            if (CountryBySchool != null) {
                return LogUtils.error(this.getClass().getSimpleName(), "addCountry", param, "国家名重复", null);
            }

            pd.put("id", getLongID());
            pd.put("addBy", pd.get("uid"));
            pd.put("updateTime", WSPDate.getCurrentTimestemp());
            pd.put("addTime", WSPDate.getCurrentTimestemp());
            countryImpl.addCountry(pd);
            wspResult.setData(pd.get("id"));
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "addCountry", param, "添加国家失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 修改国家
     */
    public String modifyCountry(String param) {
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
            PageData CountryInfo = countryImpl.getCountry(sc);
            if (CountryInfo == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "modifyCountry", param, "国家不存在", null);
            }

            // 检查名称是否重复
            PageData nameExists = new PageData();
            nameExists.put("name", pd.get("name"));
            PageData CountryBySchool = countryImpl.getCountry(nameExists);
            if (CountryBySchool != null && !CountryBySchool.get("id").equals(pd.get("id"))) {
                return LogUtils.error(this.getClass().getSimpleName(), "modifyCountry", param, "国家名重复", null);
            }

            pd.put("updateTime", WSPDate.getCurrentTimestemp());
            countryImpl.modifyCountry(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "modifyCountry", param, "修改国家失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取国家详情
     */
    public String getCountry(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try{
            String result = ParameterUtils.checkParam(pd, "id");
            if (result != null) {
                return result;
            }
            PageData Country = countryImpl.getCountry(pd);
            wspResult.setData(Country);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getCountry", param, "获取国家详情失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取国家列表
     */
    public String getCountryList(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try{
//            String result = ParameterUtils.checkParam(pd, "schoolId");
//            if (result != null) {
//                return result;
//            }
//
//            PageData school = new PageData();
//            school.put("schoolId", pd.get("schoolId"));
            List<PageData> CountryBySchool = countryImpl.getCountryList(pd);
            wspResult.setData(CountryBySchool);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getCountryList", param, "获取国家列表失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 删除国家
     */
    public String deleteCountry(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        try{
            String result = ParameterUtils.checkParam(pd, "id");
            if (result != null) {
                return result;
            }
            pd.put("idDelete", 1);
            countryImpl.modifyCountry(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "deleteCountry", param, "删除国家失败", e);
        }
        return json.toJSONString(wspResult);
    }

}
