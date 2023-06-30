package io.yujie.springboot.example.controller;

import io.yujie.springboot.example.config.exception.LogicException;
import io.yujie.springboot.example.entity.base.BaseResponse;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/ping")
    public BaseResponse<String> test(@RequestParam String ping) {
        if ("5".equals(ping)) {
            throw new LogicException("此处有异常");
        }
        return BaseResponse.success(ping);
    }


    @PostMapping("/pong")
    public BaseResponse<Map<String, String>> post(@RequestBody Map<String, String> param) {
        return BaseResponse.success(HashMap.newHashMap(4));
    }

    @PostMapping("/user")
    public BaseResponse<Map<String, String>> post(@RequestBody @Valid User user) {
        return BaseResponse.success(HashMap.newHashMap(4));
    }





    @Data
    public static class User{
        @NotBlank
        private String userName;
        @NotBlank
        private String password;
    }


}
