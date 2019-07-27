package com.dndy.dao;


import com.dndy.model.PageData;
import com.dndy.util.ParameterUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component(value = "baseDao")
public class BaseDao {

    @Resource(name = "sqlSessionTemplate")
    protected SqlSessionTemplate sqlSessionTemplate;

    /**
     * 调用sql，获取同一个pid的下层对象 返回json格式
     */
    public List<PageData> getPidData(String mapper, String pid, boolean exclude) {
        PageData pd = new PageData();
        pd.put("pid", pid);
        if (exclude) {
            pd.put("exclude", 1);
        }
        List<PageData> resultList = this.sqlSessionTemplate.selectList(mapper, pd);
        if (resultList.size() > 0 && !ParameterUtils.getResult(pid)) {
            for (PageData result : resultList) {
                if (!ParameterUtils.getResult(result.get("id"))) {
                    List<PageData> results = this.getPidData(mapper, result.get("id").toString(), exclude);
                    if (results.size() > 0) {
                        result.put("children", results);
                    }
                }
            }
        }
        return resultList;
    }
}
