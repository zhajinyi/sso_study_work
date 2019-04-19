package org.bureaumanager.bureaumanagerpro.sysConfig.filter;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;
import org.bureaumanager.bureaumanagerpro.sysConfig.utils.CacheUtils;

/**
 * @Author: BaoLing
 * @Date: 2019/1/7 16:51
 * @Description: PageCachingFilter 页面缓存过滤器
 * @Version: 1.0
 */
public class PageCachingFilter extends SimplePageCachingFilter {
    @Override
    protected CacheManager getCacheManager() {
        return CacheUtils.getCacheManager();
    }

}
