package io.yujie.springboot.example.expand.cache;

import io.yujie.springboot.example.util.JsonUtils;

import java.util.Collection;
import java.util.Optional;

public abstract class StringCache<T> extends CacheBase {

    public Optional<T> getValue(CacheKey cacheKey, Class<T> tClass) {
        String cacheValue = getRedisTemplate()
                .opsForValue()
                .get(cacheKey.getKey());
        if (cacheKey != null) {
            if (EMPTY_OBJ.equals(cacheValue)) {
                return Optional.empty();
            }
            return Optional.of(JsonUtils.readValue(cacheValue, tClass));
        }
        T t = loadFromStorage(cacheKey);
        if (t != null) {
            getRedisTemplate().opsForValue().set(cacheKey.getKey(), JsonUtils.writeValueAsString(t), getExpireTime());
        }
        return Optional.of(t);
    }

    protected abstract T loadFromStorage(CacheKey cacheKey) ;

    protected abstract Collection<T> loadFromStorage(Collection<CacheKey> cacheKeys);


}
