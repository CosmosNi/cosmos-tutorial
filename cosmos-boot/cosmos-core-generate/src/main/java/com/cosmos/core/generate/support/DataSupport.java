package com.cosmos.core.generate.support;

import com.cosmos.core.generate.strategy.NameStrategy;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ProjectName: cosmos-template.templates.strategy
 * @Package: com.cosmos.template.templates.strategy.support
 * @ClassName: DataSupport
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/3/14 13:18
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class DataSupport {
    /**
     * 作者
     */
    private String author;
    /**
     * 生成时间
     */
    private String now;
    /**
     * 模块
     */
    private String module;
    /**
     * entity父类
     */
    private String superEntity;
    /**
     * Repository父类
     */
    private String superRepository;
    /**
     * Service父类
     */
    private String superService;
    /**
     * ServiceImpl父类
     */
    private String superServiceImpl;
    /**
     * Controller父类
     */
    private String superController;
    /**
     * 自定义命名规范
     */
    private NameStrategy nameStrategy;
    /**
     * 基础包名
     */
    private String basePackage;


    /**
     * table的前缀
     */
    private String tablePrefix;
    /**
     * web基础包
     */
    private String webBasePackage;
    /**
     * 服务名
     */
    private String serviceProjectName;


    /**
     * dto包名
     */
    private String dtoPackage = "dto";

    /**
     * vo的包名
     */
    private String voPackage = "vo";
    /**
     * service包名
     */
    private String servicePackage = "service";
    /**
     * service.impl包名
     */
    private String serviceImplPackage = "service.impl";
    /**
     * controller包名
     */
    private String controllerPackage = "controller";
    /**
     * repository包名
     */
    private String repositoryPackage = "repository.ftl";

    /**
     * entity包名
     */
    private String entityPackage = "entity";
}
