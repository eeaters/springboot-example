package io.yujie.springboot.example;

import io.yujie.springboot.example.enums.ResultCodeEnum;
import io.yujie.springboot.example.expand.enums.CodeEnumUtils;
import org.junit.jupiter.api.Test;

public class CommonTest {

    @Test
    public void codeEnumTest() {
        ResultCodeEnum resultCodeEnum = CodeEnumUtils.valueOf(ResultCodeEnum.class, 500);
        System.out.println("resultCodeEnum = " + resultCodeEnum);
    }
}
