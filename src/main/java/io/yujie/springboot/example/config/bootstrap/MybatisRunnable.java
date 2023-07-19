package io.yujie.springboot.example.config.bootstrap;

import io.yujie.springboot.example.entity.model.UserModel;
import io.yujie.springboot.example.mapper.UserMapper;
import io.yujie.springboot.example.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

//@Component
public class MybatisRunnable {


    @Autowired
    UserMapper userMapper;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Bean
    public CommandLineRunner test() {
        return args -> {
            transactionTemplate.execute(status -> {
                UserModel userModel = new UserModel();
                userModel.setUserName("eeaters");
                userModel.setAge(30);
                userModel.setPassword("12345678");

                userMapper.insertSelective(userModel);

                List<UserModel> userModels = userMapper.selectAll();
                System.out.println("userModels = " + JsonUtils.writeValueAsString(userModels));
//                throw new RuntimeException("eee , 我就是要报错");
                return null;
            });
        };
    }
}
