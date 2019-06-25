package com.cosmos.core.generate;

import com.alibaba.druid.pool.DruidDataSource;
import com.cosmos.core.generate.generate.CodeGenerator;
import com.cosmos.core.generate.strategy.DefaultNameStrategy;
import com.cosmos.core.generate.strategy.DefaultTypeMapping;
import com.cosmos.core.generate.strategy.NameStrategy;
import com.cosmos.core.generate.support.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.core.generate
 * @ClassName: Demo
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/6/24 23:24
 * @Version: 1.0
 */
public class Demo {
    /**
     * 生成案例
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        DruidDataSource basicDataSource = new DruidDataSource();
        basicDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/my_shiro?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root");
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setConnectionErrorRetryAttempts(0);
        basicDataSource.setBreakAfterAcquireFailure(true);
        basicDataSource.setInitialSize(10);
        basicDataSource.setMaxActive(20);
        basicDataSource.setMinIdle(10);

        NameStrategy nameStrategy = new DefaultNameStrategy("", "", new DefaultTypeMapping());
        DataSupport dataSupport = new DataSupport().setAuthor("nijiahui")
                .setNameStrategy(nameStrategy)
                .setBasePackage("com.cosmos.core")
                .setEntityPackage("entity")
                .setNow(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                .setSuperEntity("com.cosmos.entity.BaseEntity")
                .setRepositoryPackage("repository")
                .setSuperRepository("com.cosmos.repository.BaseRepository")
                .setServicePackage("service")
                .setSuperService("com.cosmos.service.BaseService")
                .setServiceImplPackage("service.impl")
                .setSuperServiceImpl("com.cosmos.service.impl.BaseServiceImpl");

        CodeGenerator codeGenerator = new CodeGenerator().setDataSource(basicDataSource)
                .setDataSupport(dataSupport)
                .setOverwritten(true)
                .setSubModel("cosmos-web");

        codeGenerator.generator(new String[]{"sys_menu", "sys_role", "sys_role_menu", "sys_user", "sys_user_role"});
    }
}
