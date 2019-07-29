package com.dndy.cotroller;

import com.dndy.interceptor.AuthJwt;
import com.dndy.model.PageData;
import com.dndy.service.VideoTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Here's the Controller handling the video type
 */
@RestController
@RequestMapping(value = "type")
public class VideoTypeController extends BaseController{

    @Resource(name = "videoTypeService")
    private VideoTypeService typeService;

    /**
     * Add the video a type
     */
    @PostMapping(value = "addType", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String addType(HttpServletRequest request, @RequestBody String param) {
        PageData pd = json.parseObject(param, PageData.class);
        pd.put("uid", request.getAttribute("uid"));
        return typeService.addType(json.toJSONString(pd));
    }

    /**
     * Update the video a type
     */
    @PutMapping(value = "modifyType", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String modifyType(HttpServletRequest request, @RequestBody String param) {
        PageData pd = json.parseObject(param, PageData.class);
        return typeService.modifyType(json.toJSONString(pd));
    }

    /**
     * Get the video a type
     */
    @GetMapping(value = "getType", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getType(HttpServletRequest request, @RequestParam(value = "id") Long id) {
        PageData pd = new PageData();
        pd.put("id", id);
        return typeService.getType(json.toJSONString(pd));
    }

    /**
     * To obtain the video type list
     */
    @GetMapping(value = "getTypeList", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getTypeList(HttpServletRequest request) {
        PageData pd = new PageData();
        return typeService.getTypeList(json.toJSONString(pd));
    }

    /**
     * Remove the video type
     */
    @DeleteMapping(value = "deleteType", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String deleteType(HttpServletRequest request, @RequestParam(value = "id") Long id) {
        PageData pd = new PageData();
        pd.put("id", id);
        return typeService.deleteType(json.toJSONString(pd));
    }
}
