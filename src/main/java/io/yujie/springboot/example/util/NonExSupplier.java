package io.yujie.springboot.example.util;

@FunctionalInterface
public interface NonExSupplier<R> {

    R exec() throws Exception;

    static <R> R exec(NonExSupplier<R> supplier) {
        try {
            return supplier.exec();
        } catch (Exception e) {
            //todo log
            return null;
        }
    }

}
