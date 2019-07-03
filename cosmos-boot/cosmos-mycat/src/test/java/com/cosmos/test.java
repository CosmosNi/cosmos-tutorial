package com.cosmos;

import com.cosmos.mycat.Application;
import com.cosmos.mycat.domain.SysMenu;
import com.cosmos.mycat.domain.SysRole;
import com.cosmos.mycat.repository.SysMenuRepository;
import com.cosmos.mycat.repository.SysRoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos
 * @ClassName: test
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/2 16:13
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("dev")
@Transactional
public class test {
    @Autowired
    private SysMenuRepository sysMenuRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setName("menu" + i);
            sysMenu.setPerms("perm" + i);
            sysMenuRepository.save(sysMenu);
        }

        for (int i = 0; i < 10000; i++) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("role" + i);
            sysRoleRepository.save(sysRole);
        }
    }
}
