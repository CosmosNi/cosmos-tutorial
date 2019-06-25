package com.cosmos.core.generate.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


/**
 * @ProjectName: code-strategy
 * @Package: com.cosmos.strategy
 * @ClassName: DefaultNameStrategy
 * @Author: keda
 * @Description: 自定义命名规范
 * @Date: 2019/3/14 9:00
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class DefaultNameStrategy implements NameStrategy {
    private String tablePrefix;
    private String entitySuffix;
    private TypeMapping typeMapping;

    @Override
    public String entity(String tableName) {
        String name = StringUtils.lowerCase(tableName);
        String prefix = StringUtils.lowerCase(tablePrefix);
        if (StringUtils.isNotEmpty(prefix) && name.startsWith(prefix)) {
            name = StringUtils.substringAfterLast(name, prefix);
        }
        String[] array = StringUtils.split(name, "_");
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            stringBuffer.append(StringUtils.capitalize(array[i]));
        }
        if (entitySuffix != null) {
            stringBuffer.append(entitySuffix);
        }
        return stringBuffer.toString();
    }

    @Override
    public String column(String columnName) {
        String[] array = StringUtils.split(columnName, "_");
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                stringBuffer.append(array[i]);
            } else {
                stringBuffer.append(StringUtils.capitalize(array[i]));
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public String table(String table) {
        return StringUtils.lowerCase(table);
    }

    @Override
    public String type(String type) {
        return typeMapping.mapType(type);
    }

    @Override
    public String trimPackage(String full) {
        return StringUtils.substringAfterLast(full, ".");
    }
}
