package com.cosmos.core.generate.generate;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import com.cosmos.core.generate.support.DataSupport;
import com.cosmos.core.generate.support.Table;
import com.cosmos.core.generate.support.TableColumn;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ProjectName: code-strategy
 * @Package: com.cosmos.strategy
 * @ClassName: CodeGenerator
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/3/14 8:51
 * @Version: 1.0
 */
@Slf4j
@Data
@Accessors(chain = true)
public class CodeGenerator {
    private DataSupport dataSupport;
    private DataSource dataSource;
    private File rootDir = new File(".");
    private String entityTemplate = "entity.ftl";
    private String serviceTemplate = "service.ftl";
    private String controllerTemplate = "controller.ftl";
    private String repositoryTemplate = "repository.ftl";
    private String serviceImplTemplate = "serviceImpl.ftl";
    private boolean overwritten;
    private boolean generateEntity = true;
    private boolean generateService = true;
    private boolean generateRepository = true;
    private boolean generateServiceImpl = true;
    /**
     * 用来指定生成目录，默认项目根目录
     */
    private String subModel = "";
    private boolean generateController;
    /**
     * 用于过滤相关的column
     */
    private String[] escapeColumns = {"id", "created_by", "created_time", "updated_by", "updated_time", "version", "ID", "CREATED_BY", "CREATED_TIME", "UPDATED_BY", "UPDATED_TIME", "VERSION"};

    private Configuration cfg;
    private File parentFile;
    private ExecutorService executor;
    private String path;
    private ByteArrayOutputStream outputStream;
    private ZipOutputStream zip;


    /**
     * 初始化配置
     */
    public void init() {
        outputStream = new ByteArrayOutputStream();
        zip = new ZipOutputStream(outputStream);
        executor = Executors.newCachedThreadPool();
        cfg = new Configuration(new Version("2.3.28"));
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/generator");
        String path = dataSupport.getBasePackage();
        if (StringUtils.isNotEmpty(dataSupport.getModule())) {
            path = dataSupport.getBasePackage().concat(".").concat(dataSupport.getModule());
        }
        path = path.replace(".", "/");
        parentFile = new File(new File(rootDir, subModel + "/src/main/java"), path);
    }

    /**
     * 生成table
     *
     * @param tableNames
     */
    public void generator(String... tableNames) throws InterruptedException {
        init();
        //开始阀门
        final CountDownLatch startGet = new CountDownLatch(1);
        //结束阀门
        final CountDownLatch endGet = new CountDownLatch(tableNames.length);


        for (String tableName : tableNames) {
            executor.execute(() -> {
                try {
                    startGet.await();
                    Table table = load(tableName);
                    generateEntity(table, cfg, parentFile);
                    generateRepository(table, cfg, parentFile);
                    generateService(table, cfg, parentFile);

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TemplateException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endGet.countDown();
                }
            });
        }
        startGet.countDown();
        endGet.await();
        executor.shutdown();
        IoUtil.close(zip);
    }

    /**
     * 生成JPA repository
     *
     * @param table
     * @param cfg
     * @param parentFile
     * @throws IOException
     * @throws TemplateException
     */
    private void generateRepository(Table table, Configuration cfg, File parentFile) throws IOException, TemplateException {
        Template template = cfg.getTemplate(repositoryTemplate, "UTF-8");
        if (generateRepository) {
            File repositoryParent = new File(parentFile, dataSupport.getRepositoryPackage().replace(".", "/"));
            repositoryParent.mkdirs();
            File repositoryFile = new File(repositoryParent, dataSupport.getNameStrategy().entity(table.getName()).concat("Repository.java"));
            if (overwritten || !repositoryFile.exists()) {
                log.info("prepare to write repository file {}", repositoryFile.getAbsolutePath());

                FileWriter fileWriter = new FileWriter(repositoryFile);
                Map<String, Object> map = BeanUtil.beanToMap(dataSupport);
                map.put("table", table);
                template.process(map, fileWriter);

                StringWriter sw = new StringWriter();
                template.process(map, sw);
                zip.putNextEntry(new ZipEntry(repositoryFile.getName()));
                IOUtils.write(sw.toString(), zip, "UTF-8");

                zip.closeEntry();
                fileWriter.close();
            } else {
                log.info("file {} exist, skip to write repository", repositoryFile.getAbsolutePath());
            }
        }
    }

    /**
     * 生成entity
     *
     * @param table
     * @param cfg
     * @param parentFile
     * @throws IOException
     * @throws TemplateException
     */
    private void generateEntity(Table table, Configuration cfg, File parentFile) throws IOException, TemplateException {
        Template template = cfg.getTemplate(entityTemplate, "UTF-8");
        if (generateEntity) {
            File entityParent = new File(parentFile, dataSupport.getEntityPackage().replace(".", "/"));
            entityParent.mkdirs();
            File entityFile = new File(entityParent, dataSupport.getNameStrategy().entity(table.getName()).concat(".java"));
            if (overwritten || !entityFile.exists()) {
                log.info("prepare to write entity file {}", entityFile.getAbsolutePath());
                FileWriter fileWriter = new FileWriter(entityFile);
                Map<String, Object> map = BeanUtil.beanToMap(dataSupport);
                map.put("table", table);
                template.process(map, fileWriter);
                fileWriter.close();
            } else {
                log.info("file {} exist, skip to write entity", entityFile.getAbsolutePath());
            }
        }
    }

    /**
     * 生成service
     *
     * @param table
     * @param cfg
     * @param parentFile
     * @throws IOException
     * @throws TemplateException
     */
    private void generateService(Table table, Configuration cfg, File parentFile) throws IOException, TemplateException {
        Template template = cfg.getTemplate(serviceTemplate, "UTF-8");
        if (generateService) {
            if (StringUtils.isNotEmpty(dataSupport.getWebBasePackage())) {
                path = dataSupport.getWebBasePackage();
                if (StringUtils.isNotEmpty(dataSupport.getModule())) {
                    path = dataSupport.getWebBasePackage().concat(".").concat(dataSupport.getModule());
                }
                path = path.replace(".", "/");
            }
            if (StringUtils.isNotEmpty(dataSupport.getServiceProjectName())) {
                parentFile = new File(new File(rootDir, "../" + dataSupport.getServiceProjectName()), "/src/main/java".concat("/").concat(path));
                parentFile.mkdirs();
            }
            File serviceParent = new File(parentFile, dataSupport.getServicePackage().replace(".", "/"));
            serviceParent.mkdirs();
            File serviceFile = new File(serviceParent, dataSupport.getNameStrategy().entity(table.getName()).concat("Service.java"));
            if (overwritten || !serviceFile.exists()) {
                log.info("prepare to write service file {}", serviceFile.getAbsolutePath());

                FileWriter fileWriter = new FileWriter(serviceFile);
                Map<String, Object> map = BeanUtil.beanToMap(dataSupport);
                map.put("table", table);
                template.process(map, fileWriter);
                fileWriter.close();

            } else {
                log.info("file {} exist, skip to write service", serviceFile.getAbsolutePath());
            }
            genertateSericeImpl(table, cfg, parentFile);
        }
    }

    /**
     * 生成serviceImpl
     *
     * @param table
     * @param cfg
     * @param parentFile
     * @throws IOException
     * @throws TemplateException
     */
    private void genertateSericeImpl(Table table, Configuration cfg, File parentFile) throws IOException, TemplateException {
        Template template = cfg.getTemplate(serviceImplTemplate, "UTF-8");
        if (generateServiceImpl) {
            File serviceImplParent = new File(parentFile, dataSupport.getServiceImplPackage().replace(".", "/"));
            serviceImplParent.mkdirs();
            File serviceImplFile = new File(serviceImplParent, dataSupport.getNameStrategy().entity(table.getName()).concat("ServiceImpl.java"));
            if (overwritten || !serviceImplFile.exists()) {
                log.info("prepare to write service Impl file {}", serviceImplFile.getAbsolutePath());

                FileWriter fileWriter = new FileWriter(serviceImplFile);
                Map<String, Object> map = BeanUtil.beanToMap(dataSupport);
                map.put("table", table);
                template.process(map, fileWriter);
                fileWriter.close();

            } else {
                log.info("file {} exist, skip to write service Impl", serviceImplFile.getAbsolutePath());
            }
        }
    }


    public Table load(String tableName) throws SQLException {
        try (ResultSet resultSet = dataSource.getConnection().getMetaData().getTables(null, null, tableName, null)) {
            Table table = new Table();
            table.setName(tableName);
            if (resultSet.next()) {
                String remarks = resultSet.getString("REMARKS");
                table.setRemarks(StringUtils.trim(remarks));
                table.setExists(true);
                log.info("==>Scan Table {}", tableName);
                try (ResultSet colSet = dataSource.getConnection().getMetaData().getColumns(null, null, tableName, null)) {
                    while (colSet.next()) {
                        String col = colSet.getString("COLUMN_NAME");
                        if (!Arrays.asList(escapeColumns).contains(col)) {
                            String type = colSet.getString("TYPE_NAME");
                            String colRemarks = colSet.getString("REMARKS");
                            log.info("==>{}:{}", col, type);
                            table.getColumns().add(new TableColumn(col, type, StringUtils.trim(colRemarks)));
                        }
                    }
                }
            }
            return table;
        }


    }
}