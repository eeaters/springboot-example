package io.yujie.springboot.example.expand.enums;

public interface CodeEnumUtils {

    static <E extends Enum<?> & CodeEnum> E valueOf(Class<E> codeEnum, Integer code) {
        for (E enumConstant : codeEnum.getEnumConstants()) {
            if (code.equals(enumConstant.getCode())) {
                return enumConstant;
            }
        }
        return null;
    }

}
