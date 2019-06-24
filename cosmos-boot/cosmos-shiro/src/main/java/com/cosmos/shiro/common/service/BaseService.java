package com.cosmos.shiro.common.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @ProjectName: cosmos-server
 * @Package: com.cosmos.service
 * @ClassName: BaseService
 * @Author: keda
 * @Description: ${description}
 * @Date: 2019/3/22 14:47
 * @Version: 1.0
 */
public interface BaseService<T, K, H> {
    K findById(Long id);

    List<K> list();

    void saveDTO(H h);

    void save(T t);

    void delete(Long var1);

    Page<K> findAll(Pageable var1);

    Page<K> findAll(Example<T> var1, Pageable var2);

    Page<K> findAll(Specification<T> var1, Pageable var2);
}
