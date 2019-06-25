package com.cosmos.core.generate.strategy;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: code-strategy
 * @Package: com.cosmos.strategy
 * @ClassName: DefaultTypeMapping
 * @Author: keda
 * @Description: 类型映射表
 * @Date: 2019/3/14 9:00
 * @Version: 1.0
 */
public class DefaultTypeMapping implements TypeMapping {
    private static final String TYPE_INTEGER = "Integer";
    private static final String TYPE_LONG = "Long";
    private static final String TYPE_FLOAT = "Float";
    private static final String TYPE_DOUBLE = "Double";
    private static final String TYPE_DATE = "Date";
    private static final String TYPE_STRING = "String";
    private static final String TYPE_BIGDECIMAL = "BigDecimal";
    private static final String TYPE_BOOLEAN = "Boolean";
    protected Map<String, String> map = new HashMap();

    public DefaultTypeMapping() {
        init();
    }

    protected void init() {
        map.put("TINYINT", TYPE_INTEGER);
        map.put("SMALLINT", TYPE_INTEGER);
        map.put("INT", TYPE_INTEGER);
        map.put("BIGINT", TYPE_LONG);
        map.put("FLOAT", TYPE_FLOAT);
        map.put("DATE", TYPE_DATE);
        map.put("TIME", TYPE_DATE);
        map.put("DATETIME", TYPE_DATE);
        map.put("TIMESTAMP", TYPE_DATE);
        map.put("DOUBLE", TYPE_DOUBLE);
        map.put("VARCHAR", TYPE_STRING);
        map.put("CHAR", TYPE_STRING);
        map.put("TEXT", TYPE_STRING);
        map.put("TINYTEXT", TYPE_STRING);
        map.put("MEDIUMTEXT", TYPE_STRING);
        map.put("LONGTEXT", TYPE_STRING);
        map.put("DECIMAL", TYPE_BIGDECIMAL);
        map.put("BIT", TYPE_BOOLEAN);
    }

    @Override
    public String mapType(String type) {
        return map.get(StringUtils.upperCase(type));
    }
}
