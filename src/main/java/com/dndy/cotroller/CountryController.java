package com.dndy.cotroller;

import com.dndy.interceptor.AuthJwt;
import com.dndy.model.PageData;
import com.dndy.service.CountryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Here's the Controller handling the Country
 */
@RestController
@RequestMapping(value = "country")
public class CountryController extends BaseController{

    @Resource(name = "countryService")
    private CountryService countryService;

    /**
     * Add the Country
     */
    @PostMapping(value = "addCountry", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String addCountry(HttpServletRequest request, @RequestBody String param) {
        PageData pd = json.parseObject(param, PageData.class);
        pd.put("uid", request.getAttribute("uid"));
        return countryService.addCountry(json.toJSONString(pd));
    }

    /**
     * Update the Country
     */
    @PutMapping(value = "modifyCountry", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String modifyCountry(HttpServletRequest request, @RequestBody String param) {
        PageData pd = json.parseObject(param, PageData.class);
        return countryService.modifyCountry(json.toJSONString(pd));
    }

    /**
     * Get the Country
     */
    @GetMapping(value = "getCountry", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getCountry(HttpServletRequest request, @RequestParam(value = "id") Long id) {
        PageData pd = new PageData();
        pd.put("id", id);
        return countryService.getCountry(json.toJSONString(pd));
    }

    /**
     * To obtain the Country list
     */
    @GetMapping(value = "getCountryList", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String getCountryList(HttpServletRequest request, @RequestParam(value = "name", required = false) String name) {
        PageData pd = new PageData();
        pd.put("name", name);
        return countryService.getCountryList(json.toJSONString(pd));
    }

    /**
     * Remove the Country
     */
    @DeleteMapping(value = "deleteCountry", headers = "Accept=*/*", produces = "application/json;charset=UTF-8")
    @AuthJwt
    public String deleteCountry(HttpServletRequest request, @RequestParam(value = "id") Long id) {
        PageData pd = new PageData();
        pd.put("id", id);
        return countryService.deleteCountry(json.toJSONString(pd));
    }
}
