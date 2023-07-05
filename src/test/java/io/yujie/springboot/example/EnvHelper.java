package io.yujie.springboot.example;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.List;

public class EnvHelper {


    public static ConfigurableEnvironment getEnv() throws IOException {
        ConfigurableEnvironment configurableEnvironment = new StandardEnvironment();
        YamlPropertySourceLoader propertySourceLoader = new YamlPropertySourceLoader();
        String name = "application";
        ClassPathResource classPathResource = new ClassPathResource("application.yml");
        EncodedResource encodedResource = new EncodedResource(classPathResource);
        List<PropertySource<?>> load = propertySourceLoader.load(name, encodedResource.getResource());

        for (PropertySource<?> propertySource : load) {
            configurableEnvironment.getPropertySources().addFirst(propertySource);
        }
        return configurableEnvironment;
    }

}
