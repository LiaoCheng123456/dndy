package com.dndy.service;

import com.dndy.dao.IActorDao;
import com.dndy.model.PageData;
import com.dndy.util.LogUtils;
import com.dndy.util.ParameterUtils;
import com.wsp.core.WSPResult;
import com.wsp.utils.WSPDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "actorService")
public class ActorService extends BaseService{

    @Resource(name = "actorImpl")
    private IActorDao actorDao;

    @Resource(name = "commonServiceHelper")
    private CommonServiceHelper commonServiceHelper;

    /**
     * 添加演员信息
     * @param param
     * @return
     */
    public String addActor(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "actorService", param, "添加演员信息");

        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "name", "countryId");
        if (s != null) {
            return s;
        }

        try {

            PageData countryId = commonServiceHelper.getCountryInfo(pd.get("countryId"));
            if (countryId == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "actorService", param, "国家不存在", null);
            }

            // 检查名字是否重复
            PageData actor = new PageData();
            actor.put("name", pd.get("name"));
            actor.put("countryId", pd.get("countryId"));
            PageData actorResult = actorDao.getActor(actor);
            if (actorResult != null) {
                return LogUtils.error(this.getClass().getSimpleName(), "actorService", param, "演员已存在", null);
            }

            pd.put("id", this.getLongID());
            pd.put("addTime", WSPDate.getCurrentTimestemp());
            actorDao.addActor(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "actorService", param, "添加演员失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 编辑演员信息
     * @param param
     * @return
     */
    public String modifyActor(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "modifyActor", param, "编辑演员信息");

        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "id");
        if (s != null) {
            return s;
        }

        try {

            // 检查名字是否重复
            PageData actor = new PageData();
            actor.put("id", pd.get("id"));
            PageData actorResult = actorDao.getActor(actor);
            if (actorResult == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "modifyActor", param, "演员不存在", null);
            }
            actorDao.modifyActor(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "modifyActor", param, "编辑演员信息失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取演员信息
     * @param param
     * @return
     */
    public String getActor(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "getActor", param, "获取演员信息");

        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "id");
        if (s != null) {
            return s;
        }

        try {
            PageData actor = new PageData();
            actor.put("id", pd.get("id"));
            PageData actorResult = actorDao.getActor(actor);
            if (actorResult == null) {
                return LogUtils.error(this.getClass().getSimpleName(), "getActor", param, "演员不存在", null);
            }
            wspResult.setData(actorResult);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getActor", param, "获取演员信息失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 获取演员列表
     * @param param
     * @return
     */
    public String getActorList(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "getActorList", param, "获取演员列表");

        try {
            List<PageData> actorResultList = actorDao.getActorList(pd);
            wspResult.setData(actorResultList);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "getActorList", param, "获取演员列表失败", e);
        }
        return json.toJSONString(wspResult);
    }

    /**
     * 删除演员
     * @param param
     * @return
     */
    public String deleteActor(String param) {
        WSPResult wspResult = new WSPResult();
        PageData pd = json.parseObject(param, PageData.class);
        LogUtils.info(this.getClass().getSimpleName(), "deleteActor", param, "删除演员");


        // 检查不为空的参数
        String s = ParameterUtils.checkParam(pd, "id");
        if (s != null) {
            return s;
        }

        try {
            actorDao.deleteActor(pd);
        } catch (Exception e) {
            return LogUtils.error(this.getClass().getSimpleName(), "deleteActor", param, "删除演员失败", e);
        }
        return json.toJSONString(wspResult);
    }
}
