package com.cosmos.core.generate.strategy;

/**
 * @ProjectName: code-strategy
 * @Package: com.cosmos.strategy
 * @ClassName: NameStrategy
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/3/14 9:01
 * @Version: 1.0
 */
public interface NameStrategy {
    /**
     * 转换type
     * @param type
     * @return
     */
    String type(String type);

    /**
     * 转换table
     * @param table
     * @return
     */
    String table(String table);
    /**
     * 转换column
     * @param column
     * @return
     */
    String column(String column);
    /**
     * 转换entity
     * @param table
     * @return
     */
    String entity(String table);

    /**
     * 去除包名
     * @param fullName
     * @return
     */
    String trimPackage(String fullName);
}
