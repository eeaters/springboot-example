package io.yujie.springboot.example.feigh;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.ClientHttpRequestFactorySupplier;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class RestTemplateTest {

    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestFactory clientHttpRequestFactory = new ClientHttpRequestFactorySupplier().get();
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(clientHttpRequestFactory));
        restTemplate.getInterceptors().add(new LoggingInterceptor());

        String forObject = restTemplate.getForObject("http://www.baidu.com", String.class);
        System.out.println("forObject = " + forObject);
    }

    public class LoggingInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            ClientHttpResponse execute = execution.execute(request, body);
            System.out.println("new String(reusableClientHttpResponse.getBody().readAllBytes()) = " + new String(execute.getBody().readAllBytes()));
            System.out.println("new String(reusableClientHttpResponse.getBody().readAllBytes()) = " + new String(execute.getBody().readAllBytes()));
            return execute;
        }
    }

}

