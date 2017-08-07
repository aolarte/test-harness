package com.andresolarte.harness.lang.lambda;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumStreamTest {
    public enum MyEnum {
        ONE(1), TWO(2), THREE(3);
        private Integer value;

        MyEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public static Map<Integer, MyEnum> map = Arrays.stream(MyEnum.values())
                .collect(Collectors.toMap(MyEnum::getValue, Function.identity()));
    }

    public static void main(String... args) {
        MyEnum myEnum = MyEnum.map.get(1);
        System.out.println("My Enum: " + myEnum);
    }
}
