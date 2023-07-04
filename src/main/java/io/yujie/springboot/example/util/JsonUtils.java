package io.yujie.springboot.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonUtils {

    ObjectMapper objectMapper = new ObjectMapper();

    static String writeValueAsString(Object obj) {
        return NonExSupplier.exec(() -> objectMapper.writeValueAsString(obj));
    }

    static <T> T readValue(String text, Class<T> tClass) {
        return NonExSupplier.exec(() -> objectMapper.readValue(text, tClass));
    }
}
