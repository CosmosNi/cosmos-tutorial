package com.cosmos.log.commmon.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @ProjectName: cosmos-server
 * @Package: com.cosmos.web.repository
 * @ClassName: BaseRepository
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/3/15 11:05
 * @Version: 1.0
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
