package com.cosmos.base.lambda.supplier;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.lambda.supplier
 * @ClassName: SupplierTest
 * @Author: keda
 * @Description: Supplier主要是用来创建对象的。可以将耗时操作放在get里，在程序中，传递是Supplier对象，只有
 * 真正调用get方法时才执行运算，这就是所谓的惰性求值。
 * @Date: 2019/6/26 20:36
 * @Version: 1.0
 */
public class SupplierTest {
    public SupplierTest() {
        System.out.println("调用构造函数");
    }


    public static void main(String[] args) {
        //创建Supplier容器
        Supplier<SupplierTest> supplier = SupplierTest::new;
        //获取真正的对象，即空参函数
        supplier.get();

        String a = "123";
        String b = "123";
        BooleanSupplier booleanSupplier = () -> a.equals(b);
        System.out.println(booleanSupplier.getAsBoolean());

    }
}
