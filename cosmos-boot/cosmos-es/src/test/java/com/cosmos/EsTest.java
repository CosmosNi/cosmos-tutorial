package com.cosmos;

import com.cosmos.es.Application;
import com.cosmos.es.repository.DeptRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos
 * @ClassName: EsTest
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/4 16:30
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EsTest {

    @Autowired
    private DeptRepository deptRepository;

//    @Test
//    public void add() {
//        for (int i = 0; i < 100; i++) {
//            Dept dept = Dept.builder()
//                    .id((long) i)
//                    .deptName("名称" + i)
//                    .deptSource("来源" + i)
//                    .deptType("类型" + i).build();
//            deptRepository.save(dept);
//        }
//    }

    @Test
    public void search() {
        System.out.println(deptRepository.findById(19L).get().toString());

    }
}
