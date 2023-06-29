package io.yujie.springboot.example.config.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebLogConfig implements WebMvcConfigurer {

    @Autowired
    private ProjectLogService logService;

    /**
     * 处理get请求入参打印
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {

            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                        && request.getMethod().equals(HttpMethod.GET.name())) {
                    logService.logRequest(request, null);
                }
                return true;
            }
        });
    }
}
