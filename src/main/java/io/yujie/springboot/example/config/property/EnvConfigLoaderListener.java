package io.yujie.springboot.example.config.property;

import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.List;


public class EnvConfigLoaderListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String property = environment.getProperty("spring.profile.actives", "dev");
        String folder = "classpath:config/" + property + "/*.yaml";
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(folder);

        YamlPropertySourceLoader propertySourceLoader = new YamlPropertySourceLoader();
        for (Resource resource : resources) {
            List<PropertySource<?>> load = propertySourceLoader.load(resource.getFilename(), resource);
            load.forEach(propertySource -> environment.getPropertySources().addFirst(propertySource));
        }
    }
}
