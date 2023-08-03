package io.yujie.springboot.example.dynamic;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.junit.jupiter.api.Test;

public class GroovyTest {





    static String script = """
            def calculateSum(int a, int b) {
                return a + b
            }
            def number1 = 10
            def number2 = 20
                        
            calculateSum(number1, number2)
            """;

    @Test
    public void test() {
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);

        long l = System.currentTimeMillis();
        // 执行Groovy脚本字符串
        for (int i = 0; i < 10000000; i++) {
            Object result = shell.evaluate(script);
            if (i % 100 == 0) {
                System.out.println("result = " + (System.currentTimeMillis()- l));
                l = System.currentTimeMillis();
            }
        }

        System.out.println("( System.currentTimeMillis() -l) = " + (System.currentTimeMillis() - l));

    }

}
