package io.yujie.springboot.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "测试类")
public class TestController {


    @GetMapping("/ping")
    public BaseResponse<String> test(@Parameter(example = "123", description = "test ping") @RequestParam String ping) {
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
    @Operation(summary = "新增个用户" , description = "随便一个描述")
    public BaseResponse<User> post(@RequestBody @Valid User user) {
        return BaseResponse.success(user);
    }


    @Data
    public static class User {
        @NotBlank
        @Schema(title = "用户名", description = "这是一个用户名字段", example = "admin")
        private String userName;
        @NotBlank
        @Schema(title = "密码")
        private String password;
    }


}
