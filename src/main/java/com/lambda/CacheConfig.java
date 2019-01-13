package com.lambda;




import org.osgi.service.component.annotations.Activate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CacheConfig {

    private static CacheManager cacheManager;
    private Cache cache;

/*

    static{
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
    }
*/

    @Activate
    public void activate(final Map<String, Object> properties) {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        cache = this.cache = new SelfPopulatingCache(
                createCache("propertiesCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                ResourcePoolsBuilder.heap(10)).withExpiry(Expirations.timeToLiveExpiration(Duration.of(20, TimeUnit.SECONDS))).build())
        );
    }

    public Cache createCache(String cacheName, CacheConfiguration configuration){
        return cacheManager.createCache(cacheName, configuration);
    }


}
