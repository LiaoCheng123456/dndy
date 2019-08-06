package com.dndy.cotroller;

import com.dndy.interceptor.AuthJwt;
import com.dndy.model.PageData;
import com.dndy.service.ActorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "actor")
public class ActorController extends BaseController{

    @Resource(name = "actorService")
    private ActorService actorService;

    /**
     * Add the video a Actor
     */
    @PostMapping(value = "addActor", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String addActor(HttpServletRequest request, @RequestBody String param) {
        PageData pd = json.parseObject(param, PageData.class);
        pd.put("uid", request.getAttribute("uid"));
        return actorService.addActor(json.toJSONString(pd));
    }

    /**
     * Update the video a Actor
     */
    @PutMapping(value = "modifyActor", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String modifyActor(HttpServletRequest request, @RequestBody String param) {
        PageData pd = json.parseObject(param, PageData.class);
        return actorService.modifyActor(json.toJSONString(pd));
    }

    /**
     * Get the video a Actor
     */
    @GetMapping(value = "getActor", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getActor(HttpServletRequest request, @RequestParam(value = "id") Long id) {
        PageData pd = new PageData();
        pd.put("id", id);
        return actorService.getActor(json.toJSONString(pd));
    }

    /**
     * To obtain the video Actor list
     */
    @GetMapping(value = "getActorList", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getActorList(HttpServletRequest request, @RequestParam(value = "keyword", required = false) String keyword) {
        PageData pd = new PageData();
        pd.put("keyword", keyword);
        return actorService.getActorList(json.toJSONString(pd));
    }

    /**
     * Remove the video Actor
     */
    @DeleteMapping(value = "deleteActor", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String deleteActor(HttpServletRequest request, @RequestParam(value = "id") Long id) {
        PageData pd = new PageData();
        pd.put("id", id);
        return actorService.deleteActor(json.toJSONString(pd));
    }

    /**
     * modify the video actor
     */
    @PutMapping(value = "modifyVideoActor", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String modifyVideoActor(HttpServletRequest request, @RequestBody String param) {
        PageData pd = json.parseObject(param, PageData.class);
        return actorService.modifyVideoActor(json.toJSONString(pd));
    }
}
