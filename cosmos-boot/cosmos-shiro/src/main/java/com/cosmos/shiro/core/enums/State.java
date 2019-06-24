package com.cosmos.shiro.core.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.Getter;
import lombok.Setter;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.shiro.core.enums
 * @ClassName: State
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/24 16:30
 * @Version: 1.0
 */
@JSONType(serializeEnumAsJavaBean = true)
public enum State {
    NORMAL("NORMAL", "启用"),
    OUT("OUT", "停用");

    @Getter
    @Setter
    private String value;

    @Getter
    @Setter
    private String label;

    State(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static State getByLabel(String label) {
        for (State originTypeEnum : State.values()) {
            if (originTypeEnum.getLabel().equals(label)) {
                return originTypeEnum;
            }
        }
        return null;
    }

    public static String getStatesType() {
        return JSON.toJSONString(State.values());
    }
}
