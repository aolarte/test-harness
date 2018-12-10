package com.andresolarte.harness.gae.services;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;

public class CacheService {

    private final static Logger LOG= Logger.getLogger(CacheService.class.getName());

    private final Cache cache;
    public CacheService() throws CacheException {
        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
        } catch (CacheException e) {
            throw e;
        }
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }


    public Object get(String key) {
        Object o = cache.get(key);
        if( o== null) {
            LOG.log(Level.INFO, "Value not found for: " + key);
        }
        return o;
    }
}
