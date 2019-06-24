package com.cosmos.shiro.common.enums;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.shiro.common.exception
 * @ClassName: CodeEnum
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/24 11:50
 * @Version: 1.0
 */

public enum CodeEnum {
    UNKNOWN("-1"),
    SUCCESS("0");

    private String value;

    private CodeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }
}
