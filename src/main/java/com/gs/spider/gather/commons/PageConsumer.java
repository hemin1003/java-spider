package com.gs.spider.gather.commons;

import com.gs.spider.model.async.Task;
import com.gs.spider.model.commons.SpiderInfo;
import us.codecraft.webmagic.Page;

/**
 * PageConsumer
 */
@FunctionalInterface
public interface PageConsumer {
    void accept(Page page, SpiderInfo info, Task task);
}
