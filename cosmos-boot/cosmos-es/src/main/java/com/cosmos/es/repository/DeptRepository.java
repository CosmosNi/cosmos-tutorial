package com.cosmos.es.repository;

import com.cosmos.es.entity.Dept;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @ProjectName: cosmos-tutorial
 * @Package: com.cosmos.es.repository
 * @ClassName: DeptRepository
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/7/4 16:27
 * @Version: 1.0
 */
public interface DeptRepository extends ElasticsearchRepository<Dept, Long>, CrudRepository<Dept, Long> {
}
