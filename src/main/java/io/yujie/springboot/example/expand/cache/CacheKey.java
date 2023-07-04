package io.yujie.springboot.example.expand.cache;


public interface CacheKey<C> {

    String DEFAULT_SPLIT = "#";

    String cakeyPrefix();

    C getTarget();

    String getKey();

    default boolean equal(CacheKey obj) {

        if (this.getKey().equals(obj.getKey())) {
            return true;
        }
        return false;
    }

}
