package com.gs.spider.controller.commons.spiderinfo;

import com.google.gson.Gson;
import com.gs.spider.model.commons.SpiderInfo;
import com.gs.spider.model.commons.Webpage;
import com.gs.spider.model.utils.ResultBundle;
import com.gs.spider.model.utils.ResultListBundle;
import com.gs.spider.service.commons.spiderinfo.SpiderInfoService;
import com.gs.spider.service.commons.webpage.CommonWebpageService;
import com.gs.spider.utils.TablePage;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * SpiderInfoController
 */
@RequestMapping("/commons/spiderinfo")
@RestController
public class SpiderInfoController {
	
    private final static Logger logger = LogManager.getLogger(SpiderInfoController.class);
    
    @Autowired
    private SpiderInfoService spiderInfoService;
    @Autowired
    private CommonWebpageService commonWebpageService;
    
    private Gson gson = new Gson();

    /**
     * 列出库中所有爬虫模板
     *
     * @param size 页面容量
     * @param page 页码
     * @return 爬虫模板列表
     */
    @RequestMapping(value = "listAll", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResultListBundle<SpiderInfo> listAll(@RequestParam(value = "size", required = false, defaultValue = "10") int size, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        return spiderInfoService.listAll(size, page);
    }

    /**
     * 根据domain获取结果
     *
     * @param domain 网站域名
     * @param size   每页数量
     * @param page   页码
     * @return 爬虫模板
     */
    @RequestMapping(value = "getByDomain", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResultListBundle<SpiderInfo> getByDomain(String domain, @RequestParam(value = "size", required = false, defaultValue = "10") int size, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        return spiderInfoService.getByDomain(domain, size, page);
    }
    
    /**
     * 已抓取的网页列表
     *
     * @param query  查询词
     * @param domain 域名
     * @param page   页码
     * @return
     */
    @RequestMapping(value = {"list", ""}, method = RequestMethod.GET)
    public ResultBundle<Pair<List<Webpage>, Long>> list(@RequestParam(required = false) String query, @RequestParam(required = false) String domain, @RequestParam(defaultValue = "1", required = false) int page) {
        StringBuilder sbf = new StringBuilder();
        sbf.append("&query=");
        if (StringUtils.isNotBlank(query)) {
            query = query.trim();
            sbf.append(query);
        }
        sbf.append("&domain=");
        if (StringUtils.isNotBlank(domain)) {
            domain = domain.trim();
            sbf.append(domain);
        }
        page = page < 1 ? 1 : page;
        ResultBundle<Pair<List<Webpage>, Long>> resultBundle = commonWebpageService.getWebPageByKeywordAndDomain(query, domain, 10, page);
        return resultBundle;
    }

    /**
     * 根据网站domain删除数据
     *
     * @param domain 网站域名
     * @return 是否全部数据删除成功
     */
    @RequestMapping(value = "deleteByDomain", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResultBundle<Boolean> deleteByDomain(String domain) {
        return spiderInfoService.deleteByDomain(domain);
    }

    /**
     * 根据id删除网页模板
     *
     * @param id 网页模板id
     * @return 是否删除
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResultBundle<Boolean> deleteById(String id) {
        return spiderInfoService.deleteById(id);
    }

    /**
     * 存储模板
     *
     * @param spiderInfoJson 使用json格式进行序列化的spiderinfo
     * @return 模板id
     */
    @RequestMapping(value = "save", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
    @ResponseBody
    public ResultBundle<String> save(String spiderInfoJson) {
        return spiderInfoService.index(gson.fromJson(spiderInfoJson, SpiderInfo.class));
    }

}
