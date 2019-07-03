package com.cosmos.mycat.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
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
public interface BaseRepository<T> extends CrudRepository<T, Long>, JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
