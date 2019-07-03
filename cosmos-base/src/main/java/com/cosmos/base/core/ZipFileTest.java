package com.cosmos.base.core;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.base.core
 * @ClassName: ZipFileTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/2 10:51
 * @Version: 1.0
 */
public class ZipFileTest {

    public static void write() throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("d://test.zip"));

        for (int i = 0; i < 10; i++) {
            StringWriter sw = new StringWriter();
            sw.write("测试" + i);
            ZipEntry zipEntry = new ZipEntry("test" + i + ".txt");
            zos.putNextEntry(zipEntry);
            IOUtils.write(sw.toString(), zos, "UTF-8");
        }


        zos.closeEntry();
    }

    public static void main(String[] args) throws IOException {
        ZipFileTest.write();

    }

}
