package io.yujie.springboot.example.common;

import org.junit.jupiter.api.Test;

public class SwitchTest {

    @Test
    public void test() {
        method("abc");
        method(null);
    }

    public static void method(String ping) {
        switch (ping) {
            case "abc" -> System.out.print("456");
            case "def" -> System.out.print("456");
            case "ghi" -> System.out.print("456");
            default -> System.out.print("Null");
        }
    }
}
