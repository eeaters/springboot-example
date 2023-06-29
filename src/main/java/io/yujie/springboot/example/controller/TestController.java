package io.yujie.springboot.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/ping")
    public ResponseEntity<String> test(@RequestParam String ping) {
        return ResponseEntity.ok(ping);
    }


    @PostMapping("/pong")
    public ResponseEntity<Map<String, String>> post(@RequestBody Map<String, String> param) {
        return ResponseEntity.ok(HashMap.newHashMap(4));
    }
}
