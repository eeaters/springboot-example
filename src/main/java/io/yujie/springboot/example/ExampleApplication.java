package io.yujie.springboot.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "io.yujie.springboot.example.mapper", basePackageClasses = BaseMapper.class)
@OpenAPIDefinition(
		info = @Info(title = "样例项目", version = "2", description = "这是一个样例项目"),
		servers = {@Server(url = "http://localhost:8080"), @Server(url = "http://localhost:8080")}
)
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

}
