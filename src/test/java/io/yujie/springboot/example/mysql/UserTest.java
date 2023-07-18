package io.yujie.springboot.example.mysql;


import io.yujie.springboot.example.EnvHelper;
import io.yujie.springboot.example.entity.model.UserModel;
import io.yujie.springboot.example.mapper.UserMapper;
import io.yujie.springboot.example.util.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;
import java.util.List;

@MapperScan(basePackages = "io.yujie.springboot.example.mapper", basePackageClasses = BaseMapper.class)
public class UserTest {


    @Test
    public void test() throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setEnvironment(EnvHelper.getEnv());
        context.register(this.getClass(), DataSourceAutoConfiguration.class, MapperAutoConfiguration.class);
        context.refresh();

        UserMapper bean = context.getBean(UserMapper.class);
        List<UserModel> userModels = bean.selectAll();
        System.out.println("userModels = " + JsonUtils.writeValueAsString(userModels));

        context.close();
    }




}
